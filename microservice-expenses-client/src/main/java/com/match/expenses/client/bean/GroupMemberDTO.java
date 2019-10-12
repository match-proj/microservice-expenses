package com.match.expenses.client.bean;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author zhangchao
 * @Date 2019/9/6 16:04
 * @Version v1.0
 */
@Getter
@Setter
@ApiModel
public class GroupMemberDTO {
    private String userId;
    private String username;
    private String icon;

}
