package com.match.expenses.context.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 账单类型的图标
 * @Author zhangchao
 * @Date 2019/8/28 17:05
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class GroupCluster {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String name;

    @Column
    private String remark;

    @Column
    private String userId;

    @Column
    private BigDecimal monthTotalAmount;//当月总金额

    @Column
    private BigDecimal monthUsedAmount;//当月已用金额

    @Column
    private BigDecimal totalAmount;//总金额


}
