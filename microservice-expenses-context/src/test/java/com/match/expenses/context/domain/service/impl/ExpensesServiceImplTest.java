package com.match.expenses.context.domain.service.impl;


import com.match.common.PageResult;
import com.match.common.utils.JsonUtils;
import com.match.expenses.client.bean.ExpenseDTO;
import com.match.expenses.context.Application;
import com.match.expenses.context.domain.service.ExpensesService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ExpensesServiceImplTest {


    @Autowired
    private ExpensesService expensesService;


    @org.junit.Test
    public void listByGroupId() {
        PageResult<ExpenseDTO> pageResult = expensesService.listByGroupId("5c28197a-6142-4094-a5cf-818d5db6a2e1", "2019-09", 1, 10);
        System.out.println(JsonUtils.obj2json(pageResult));


    }
}
