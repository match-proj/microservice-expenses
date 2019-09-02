package com.match.expenses.context.controller;

import com.match.common.PageResult;
import com.match.expenses.client.ExpensesClient;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import com.match.expenses.context.domain.service.ExpensesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:05
 * @Version v1.0
 */
@RestController
@RequestMapping
@Slf4j
public class ExpensesController implements ExpensesClient {


    @Autowired
    ExpensesService expensesService;


    @Override
    public PageResult<ExpenseDTO> listByGroupId(@RequestParam("groupId") String groupId,
                                                @RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        return expensesService.listByGroupId(groupId,page,size);
    }

    @Override
    public void addExpense(@RequestParam("userId") String userId,
                           @RequestBody ExpenseDTO expenseDTO) {
        expensesService.addExpense(userId,expenseDTO);
    }

    @Override
    public void addGroupMember(@RequestParam("userId") String userId,
                               @RequestParam("groupId") String groupId) {
        expensesService.addGroupMember(userId,groupId);
    }

    @Override
    public void updateGroupName(@RequestParam("userId") String userId,
                                @RequestParam("groupId") String groupId,
                                @RequestParam("name") String name,
                                @RequestParam("remark") String remark) {
        expensesService.updateGroupName(userId,groupId,name,remark);
    }

    @Override
    public void setDefaultGroup(@RequestParam("userId") String userId,
                                @RequestParam("groupId") String groupId) {
        expensesService.setDefaultGroup(userId,groupId);
    }

    @Override
    public void payCostByGroupId(@RequestParam("userId") String userId,
                                 @RequestParam("groupId") String groupId,
                                 @RequestParam("amount") BigDecimal amount) {
        expensesService.payCostByGroupId(userId,groupId,amount);
    }

    @Override
    public SimpleGroupDTO getDefaultGroup(@RequestParam("userId") String userId) {
        return expensesService.getDefaultGroup(userId);
    }
}
