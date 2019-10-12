package com.match.expenses.client.fallback;

import com.match.common.PageResult;
import com.match.common.utils.JsonUtils;
import com.match.expenses.client.ExpensesClient;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.GroupMemberDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/8/2 17:36
 * @Version v1.0
 */
@Slf4j
@Component
public class FallbackExpensesClient implements ExpensesClient {

    @Override
    public PageResult<ExpenseDTO> listByGroupId(String groupId,  String month,int page, int size) {
        return new PageResult<>();
    }

    @Override
    public void addExpense(String userId, ExpenseDTO expenseDTO) {
        log.error("addExpense:{},{}",userId, JsonUtils.obj2json(expenseDTO));
    }

    @Override
    public void addGroupMember(String userId, String groupId) {
        log.error("addGroupMember:{},{}",userId, groupId);
    }

    @Override
    public void updateGroupName(String userId, String groupId, String name, String remark) {
        log.error("updateGroupName:{},{},{},{}",userId, groupId,name,remark);
    }

    @Override
    public void setDefaultGroup(String userId, String groupId) {
        log.error("setDefaultGroup:{},{}",userId, groupId);
    }

    @Override
    public void payCostByGroupId(String userId, String groupId, BigDecimal amount) {
        log.error("payCostByGroupId:{},{},{}",userId, groupId,amount);
    }

    @Override
    public SimpleGroupDTO getDefaultGroup(String userId) {
        log.error("getDefaultGroup:{}",userId);
        return null;
    }
    @Override
    public SimpleGroupDTO getGroup(String groupId) {
        return null;
    }

    @Override
    public List<SimpleGroupDTO> getGroupList(String userId) {
        log.error("getGroupList:{}",userId);
        return new ArrayList<>();
    }

    @Override
    public int createGroup(String userId, String name) {
        return 0;
    }

    @Override
    public List<GroupMemberDTO> getGroupMember(String groupId) {
        return new ArrayList<>();
    }
}
