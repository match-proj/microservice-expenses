package com.match.expenses.client;

import com.match.common.PageResult;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.client.bean.GroupMemberDTO;
import com.match.expenses.client.bean.SimpleGroupDTO;
import com.match.expenses.client.configuration.FeignSupportConfig;
import com.match.expenses.client.fallback.FallbackExpensesClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/8/2 10:09
 * @Version v1.0
 */
@FeignClient(name = "microservice-expenses", configuration = FeignSupportConfig.class, fallback = FallbackExpensesClient.class)
public interface ExpensesClient {


    @GetMapping("/expenses/listByGroupId")
    PageResult<ExpenseDTO> listByGroupId(@RequestParam("groupId") String groupId,
                                         @RequestParam("month") String month,
                                         @RequestParam("page") int page,
                                         @RequestParam("size") int size);

    @PutMapping("/expenses/addExpense")
    void addExpense(@RequestParam("userId") String userId,
                    @RequestBody ExpenseDTO expenseDTO);


    @PutMapping("/expenses/group/{groupId}/input/{userId}")
    void addGroupMember(@RequestParam("userId") String userId,
                        @PathVariable("groupId") String groupId);


    @PutMapping("/expenses/group/{userId}/{groupId}/update")
    void updateGroupName(@PathVariable("userId") String userId,
                         @PathVariable("groupId") String groupId,
                         @RequestParam("name") String name,
                         @RequestParam("remark") String remark);


    @PutMapping("/expenses/group/{userId}/{groupId}/default")
    void setDefaultGroup(@PathVariable("userId") String userId,
                         @PathVariable("groupId") String groupId);


    @PostMapping("/expenses/{groupId}/{userId}/add")
    void payCostByGroupId(@PathVariable("userId") String userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam("amount") BigDecimal amount);

    @PutMapping("/expenses/group/{userId}/default")
    SimpleGroupDTO getDefaultGroup(@PathVariable("userId") String userId);


    @GetMapping("/expenses/group/{groupId}")
    SimpleGroupDTO getGroup(@PathVariable("groupId") String groupId);


    @GetMapping("/expenses/group/{userId}/user")
    List<SimpleGroupDTO> getGroupList(@PathVariable("userId") String userId);

    @PostMapping("/expenses/group")
    int createGroup(@RequestParam("userId") String userId, @RequestParam("name") String name);

    @GetMapping("/expenses/group/{groupId}/member")
    List<GroupMemberDTO> getGroupMember(@PathVariable("groupId") String groupId);


}
