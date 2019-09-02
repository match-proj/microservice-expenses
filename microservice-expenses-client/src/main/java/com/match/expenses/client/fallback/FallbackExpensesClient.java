package com.match.expenses.client.fallback;

import com.match.common.PageResult;
import com.match.expenses.client.ExpensesClient;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author zhangchao
 * @Date 2019/8/2 17:36
 * @Version v1.0
 */
@Slf4j
@Component
public class FallbackExpensesClient implements ExpensesClient {
    @Override
    public PageResult<ExpenseDTO> listByGroupId(String groupId, int page, int size) {
        return new PageResult<>();
    }

    @Override
    public void addExpense(String userId, ExpenseDTO expenseDTO) {

    }

    @Override
    public void addGroupMember(String userId, String groupId) {

    }

    @Override
    public void updateGroupName(String userId, String groupId, String name, String remark) {

    }

    @Override
    public void setDefaultGroup(String userId, String groupId) {

    }

    @Override
    public void payCostByGroupId(String userId, String groupId, BigDecimal amount) {

    }

    @Override
    public SimpleGroupDTO getDefaultGroup(String userId) {
        return null;
    }
}
