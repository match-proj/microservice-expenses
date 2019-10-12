package com.match.expenses.context.domain.entity;

import com.match.expenses.client.bean.ExpenseType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单
 * @Author zhangchao
 * @Date 2019/5/24 15:15
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class Expense {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private Date createTime;

    @Column
    @Temporal(TemporalType.DATE)
    private Date consumTime;

    @Column
    private String userId;

    @Column
    private String groupId;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ExpenseType expenseType;

    @Column
    private BigDecimal amount;

    @Column
    private String remark;

    @Column
    private String tag;

    @Column
    private String tagIcon;

    @Column
    private String tagId;

}
