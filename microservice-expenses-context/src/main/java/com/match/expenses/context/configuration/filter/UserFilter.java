package com.match.expenses.context.configuration.filter;

import com.match.common.Microservice;
import com.match.common.context.User;
import com.match.common.context.UserContext;
import com.match.user.client.UserClient;
import com.match.user.client.bean.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhangchao
 * @Date 2019/5/22 11:39
 * @Version v1.0
 */
@Component
public class UserFilter implements Filter {

    @Autowired
    UserClient userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String authorization = req.getHeader(Microservice.Headers.HTTP_HEADER_AUTHORIZATION);
        if(StringUtils.isNotEmpty(authorization) && userService != null){
            String userId = userService.getUserIdByAccessToken(authorization);
            if(StringUtils.isNotEmpty(userId)){
                UserInfoDTO user = userService.info(userId);
                User user1 = new User();
                user1.setId(user.getId());
                user1.setPhone(user.getPhone());
                user1.setUsername(user.getNickName());
                user1.setEncodedPrincipal(user.getEncodedPrincipal());
                UserContext.setUser(user1);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}