package com.match.expenses.client.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class SimpleGroupDTO {

    @ApiModelProperty(value = "id",name = "id")
    private String id;
    @ApiModelProperty(value = "名称",name = "name")
    private String name;
    @ApiModelProperty(value = "描述",name = "remark")
    private String remark;
    @ApiModelProperty(value = "用户iD",name = "userId")
    private String userId;
    @ApiModelProperty(value = "拥有者",name = "owner")
    private Boolean owner;
    @ApiModelProperty(value = "成员数量",name = "memberCount")
    private Integer memberCount;
    @ApiModelProperty(value = "当月总金额",name = "monthTotalAmount")
    private BigDecimal monthTotalAmount;//当月总金额
    @ApiModelProperty(value = "当月已用金额",name = "monthUsedAmount")
    private BigDecimal monthUsedAmount;//当月已用金额
    @ApiModelProperty(value = "总金额",name = "totalAmount")
    private BigDecimal totalAmount;//总金额

}
