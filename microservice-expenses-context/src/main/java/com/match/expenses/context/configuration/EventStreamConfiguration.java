package com.match.expenses.context.configuration;

import com.github.middleware.channel.ChannelProvider;
import com.github.middleware.config.EventConfigItem;
import com.github.middleware.redis.RedisChannelConfig;
import com.github.middleware.redis.RedisChannelProvider;
import com.match.user.event.EventUserCreateDTO;
import com.match.user.event.EventUserModifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class EventStreamConfiguration {

    @Autowired
    CloudConfigProperties cloudConfigProperties;

    @Bean
    public RedisChannelConfig getRedisChannelConfig(){
        Properties properties = new Properties();
        CloudConfigProperties.Redis redis = cloudConfigProperties.getRedis();
        System.out.println("redis:"+redis.getHost());
        System.out.println("redis:"+redis.getPort());
        String host = redis.getHost();
        String port = redis.getPort();
        properties.setProperty("host",host+":"+port);
        return new RedisChannelConfig(properties);
    }

    @Bean
    public ChannelProvider getChannelProvider(){
        RedisChannelProvider redisChannelProvider = new RedisChannelProvider(getRedisChannelConfig());
        redisChannelProvider.init();
        return redisChannelProvider;
    }

    @Bean
    public EventConfigItem getEventUserModifyEventConfigItem(){
        EventConfigItem b = new EventConfigItem();
        b.setChannelProvider(getChannelProvider());
        b.setEventName(EventUserModifyDTO.EVENT_NAME);
        return b;
    }

    @Bean
    public EventConfigItem getEventUserCreateEventConfigItem(){
        EventConfigItem b = new EventConfigItem();
        b.setChannelProvider(getChannelProvider());
        b.setEventName(EventUserCreateDTO.EVENT_NAME);
        return b;
    }

}
