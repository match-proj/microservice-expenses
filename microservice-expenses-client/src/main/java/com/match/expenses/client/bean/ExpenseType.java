package com.match.expenses.client.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author zhangchao
 * @Date 2019/8/28 17:00
 * @Version v1.0
 */
@ApiModel
public enum ExpenseType {
    /**
     * 支出
     */
    @ApiModelProperty(value = "支出",name = "SPENDING")
    SPENDING,
    /**
     * 收入
     */
    @ApiModelProperty(value = "收入",name = "INCOME")
    INCOME
}
