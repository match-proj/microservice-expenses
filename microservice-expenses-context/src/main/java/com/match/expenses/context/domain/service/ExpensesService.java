package com.match.expenses.context.domain.service;

import com.match.common.PageResult;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;

import java.math.BigDecimal;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:04
 * @Version v1.0
 */
public interface ExpensesService {


    PageResult<ExpenseDTO> listByGroupId(String groupId, int page, int size);


    void addExpense(String userId,ExpenseDTO expenseDTO);


    String createGroupMember(String userId,String name);

    void addGroupMember(String userId,String groupId);


    void updateGroupName(String userId,String groupId,String name,String remark);


    void setDefaultGroup(String userId,String groupId);


    void payCostByGroupId(String userId, String groupId, BigDecimal amount);


    SimpleGroupDTO getDefaultGroup(String userId);

}
