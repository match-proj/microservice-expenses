package com.match.expenses.context.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 账单类型的图标
 * @Author zhangchao
 * @Date 2019/8/28 17:05
 * @Version v1.0
 */
@Getter
@Setter
@Entity
public class GroupMember {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column
    private String groupId;

    @Column
    private Integer defaultGroup;//默认组

    @Column
    private String userId;

}
