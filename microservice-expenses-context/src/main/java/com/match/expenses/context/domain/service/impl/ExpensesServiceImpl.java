package com.match.expenses.context.domain.service.impl;

import com.match.common.PageResult;
import com.match.common.exception.BusinessException;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import com.match.expenses.context.domain.entity.Expense;
import com.match.expenses.context.domain.entity.GroupCluster;
import com.match.expenses.context.domain.entity.GroupMember;
import com.match.expenses.context.domain.repostory.ExpensesRepository;
import com.match.expenses.context.domain.repostory.GroupMemberRepository;
import com.match.expenses.context.domain.repostory.GroupRepository;
import com.match.expenses.context.domain.service.ExpensesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:04
 * @Version v1.0
 */
@Service
public class ExpensesServiceImpl implements ExpensesService {


    @Autowired
    ExpensesRepository expensesRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    GroupRepository groupRepository;

//
//    @Autowired(required = false)
//    UserClient userClient;


    @Override
    public PageResult<ExpenseDTO> listByGroupId(String groupId, int page, int size) {

        Page<Expense> page1 = expensesRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> pr = new ArrayList<>();
                pr.add(cb.equal(root.get("group_id").as(String.class), groupId));
                return cb.and(pr.toArray(new Predicate[pr.size()]));
            }
        }, PageRequest.of(page, size));

        List<ExpenseDTO> collect = page1.getContent().stream().map(item -> {
            ExpenseDTO expenseDTO = new ExpenseDTO();
            expenseDTO.setId(item.getId());
            expenseDTO.setCreateTime(item.getCreateTime());
            expenseDTO.setConsumTime(item.getConsumTime());
            expenseDTO.setUserId(item.getUserId());
            expenseDTO.setGroupId(item.getGroupId());
            expenseDTO.setExpenseType(item.getExpenseType());
            expenseDTO.setAmount(item.getAmount());
            expenseDTO.setRemark(item.getRemark());
            expenseDTO.setTag(item.getTag());
            expenseDTO.setTagIcon(item.getTagIcon());
            expenseDTO.setTagId(item.getTagId());
            return expenseDTO;
        }).collect(Collectors.toList());

        return new PageResult<ExpenseDTO>(page1.getTotalElements(), collect);
    }

    @Override
    public void addExpense(String userId, ExpenseDTO item) {

        if (StringUtils.isEmpty(item.getGroupId())) {
            throw new BusinessException("组ID不能为空");
        }
        if (item.getExpenseType() == null) {
            throw new BusinessException("消费类型不能为空");
        }
        if (item.getAmount() == null) {
            throw new BusinessException("金额不能为空");
        }
        if (item.getAmount().compareTo(BigDecimal.ZERO) < 1) {
            throw new BusinessException("金额错误");
        }


        Expense expense = new Expense();
        expense.setId(item.getId());
        expense.setCreateTime(item.getCreateTime());
        expense.setConsumTime(item.getConsumTime());
        expense.setUserId(userId);
        expense.setGroupId(item.getGroupId());
        expense.setExpenseType(item.getExpenseType());
        expense.setAmount(item.getAmount());
        expense.setRemark(item.getRemark());
        expense.setTag(item.getTag());
        expense.setTagIcon(item.getTagIcon());
        expense.setTagId(item.getTagId());
        expensesRepository.saveAndFlush(expense);


        //更新小组金额
        GroupCluster one = groupRepository.getOne(item.getGroupId());
        one.setMonthUsedAmount(one.getMonthUsedAmount().add(item.getAmount()));
        groupRepository.saveAndFlush(one);


    }

    @Override
    public void payCostByGroupId(String userId, String groupId, BigDecimal amount) {
        GroupCluster one = groupRepository.getOne(groupId);
        if (!StringUtils.equals(userId, one.getUserId())) {
            throw new BusinessException("权限不足");
        }


        one.setTotalAmount(one.getTotalAmount().add(amount));
        one.setMonthTotalAmount(one.getMonthUsedAmount().add(amount));
        groupRepository.saveAndFlush(one);
    }

    @Override
    public String createGroupMember(String userId, String name) {

        GroupCluster groupCluster = new GroupCluster();
        groupCluster.setName(name);
        groupCluster.setRemark("");
        groupCluster.setUserId(userId);
        groupCluster.setMonthTotalAmount(new BigDecimal("0"));
        groupCluster.setMonthUsedAmount(new BigDecimal("0"));
        groupCluster.setTotalAmount(new BigDecimal("0"));

        groupRepository.saveAndFlush(groupCluster);


        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(groupCluster.getId());
        groupMember.setDefaultGroup(1);
        groupMember.setUserId(groupCluster.getUserId());
        groupMemberRepository.saveAndFlush(groupMember);



        return groupCluster.getId();
    }

    @Override
    public void addGroupMember(String userId, String groupId) {
        if (!groupMemberRepository.existsByGroupIdAndUserId(groupId, userId)) {
            GroupMember groupMember = new GroupMember();
            groupMember.setGroupId(groupId);
            groupMember.setDefaultGroup(0);
            groupMember.setUserId(userId);
            groupMemberRepository.saveAndFlush(groupMember);
        }
    }

    @Override
    public void updateGroupName(String userId, String groupId, String name, String remark) {
        GroupCluster one = groupRepository.getOne(groupId);
        if (StringUtils.isNotEmpty(name)) {
            one.setName(name);
        }
        if (StringUtils.isNotEmpty(remark)) {
            one.setRemark(remark);
        }
        groupRepository.saveAndFlush(one);
    }

    @Override
    public void setDefaultGroup(String userId, String groupId) {
        List<GroupMember> groupMemberList = groupMemberRepository.findFirstByUserId(userId);
        for (GroupMember groupMember : groupMemberList) {
            if (StringUtils.equals(groupMember.getGroupId(), groupId)) {
                groupMember.setDefaultGroup(1);
            } else {
                groupMember.setDefaultGroup(0);
            }
            groupMemberRepository.saveAndFlush(groupMember);
        }
    }

    @Override
    public SimpleGroupDTO getDefaultGroup(String userId) {
        GroupMember firstByUserIdAndDefaultGroup = groupMemberRepository.findFirstByUserIdAndDefaultGroup(userId, 1);
        GroupCluster one = groupRepository.getOne(firstByUserIdAndDefaultGroup.getGroupId());
        return Optional.ofNullable(one).map(item -> {
            SimpleGroupDTO dto = new SimpleGroupDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setRemark(item.getRemark());
            dto.setUserId(item.getUserId());
            dto.setMonthTotalAmount(item.getMonthTotalAmount());
            dto.setMonthUsedAmount(item.getMonthUsedAmount());
            dto.setTotalAmount(item.getTotalAmount());
            return dto;
        }).get();
    }
}
