package com.match.expenses.context.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhangchao
 * @Date 2019/9/2 17:19
 * @Version v1.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cloud")
public class CloudConfigProperties {


    private final Redis redis = new Redis();


    @Getter
    @Setter
    public static class Redis{
        private String host;
        private String port;
        private int maxIdle;
        private int maxActive;
        private int maxWait;
        private Boolean testOnBorrow;
    }





}
