package com.match.expenses.client.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author zhangchao
 * @Date 2019/9/2 16:25
 * @Version v1.0
 */
@Getter
@Setter
public class SimpleGroupDTO {

    private String id;
    private String name;
    private String remark;
    private String userId;
    private BigDecimal monthTotalAmount;//当月总金额
    private BigDecimal monthUsedAmount;//当月已用金额
    private BigDecimal totalAmount;//总金额

}
