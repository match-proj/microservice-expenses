package com.match.expenses.client;

import com.match.common.PageResult;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import com.match.expenses.client.configuration.FeignSupportConfig;
import com.match.expenses.client.fallback.FallbackExpensesClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author zhangchao
 * @Date 2019/8/2 10:09
 * @Version v1.0
 */
@FeignClient(name = "microservice-expenses",configuration = FeignSupportConfig.class, fallback = FallbackExpensesClient.class)
public interface ExpensesClient {



    @GetMapping("/expenses/listByGroupId")
    public PageResult<ExpenseDTO> listByGroupId(@RequestParam("groupId") String groupId,
                                                @RequestParam("page") int page,
                                                @RequestParam("size") int size);


    @PutMapping("/expenses/addExpense")
    public void addExpense(@RequestParam("userId") String userId,
                           @RequestBody ExpenseDTO expenseDTO);


    @PutMapping("/expenses/addGroupMember")
    public void addGroupMember(@RequestParam("userId") String userId,
                               @RequestParam("groupId") String groupId);


    @PutMapping("/expenses/updateGroupName")
    public void updateGroupName(@RequestParam("userId") String userId,
                                @RequestParam("groupId") String groupId,
                                @RequestParam("name") String name,
                                @RequestParam("remark") String remark);


    @PutMapping("/expenses/setDefaultGroup")
    public void setDefaultGroup(@RequestParam("userId") String userId,
                                @RequestParam("groupId") String groupId);


    @PutMapping("/expenses/payCostByGroupId")
    public void payCostByGroupId(@RequestParam("userId") String userId,
                                 @RequestParam("groupId") String groupId,
                                 @RequestParam("amount") BigDecimal amount);

    @PutMapping("/expenses/getDefaultGroup")
    public SimpleGroupDTO getDefaultGroup(@RequestParam("userId") String userId);

}
