package com.match.expenses.client.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("流水")
public class ExpenseDTO implements Serializable {

    @ApiModelProperty(value = "id",name = "id")
    private String id;

    @ApiModelProperty(value = "创建时间",name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "消费时间",name = "consumTime")
    private Date consumTime;

    @ApiModelProperty(value = "用户ID",name = "userId")
    private String userId;

    @ApiModelProperty(value = "组ID",name = "groupId")
    private String groupId;

    @ApiModelProperty(value = "消费类型",name = "expenseType")
    private ExpenseType expenseType;

    @ApiModelProperty(value = "金额",name = "amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "描述",name = "remark")
    private String remark;

    @ApiModelProperty(value = "标签",name = "tag")
    private String tag;

    @ApiModelProperty(value = "标签图标",name = "tagIcon")
    private String tagIcon;

    @ApiModelProperty(value = "标签ID",name = "tagId")
    private String tagId;

}
