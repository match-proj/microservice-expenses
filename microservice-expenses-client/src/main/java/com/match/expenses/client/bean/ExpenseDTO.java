package com.match.expenses.client.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangchao
 * @Date 2019/9/2 16:02
 * @Version v1.0
 */
@Getter
@Setter
public class ExpenseDTO implements Serializable {

    private String id;
    private Date createTime;
    private Date consumTime;
    private String userId;
    private String groupId;
    private ExpenseType expenseType;
    private BigDecimal amount;
    private String remark;
    private String tag;
    private String tagIcon;
    private String tagId;

}
