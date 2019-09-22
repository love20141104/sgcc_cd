package com.sgcc.config;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ActivemqConfig {
    @Value("${spring.activemq.broker-url}")
    private String BROKER_URL;
    //信任的包
    private static final String TRUST_PACKAGE = "com.sgcc,com.sgcc.amqp,java.util.Map,java.util.List,com.example";

    /**
     * 将包内的类设置为可信任的
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
        factory.setTrustedPackages(Arrays.asList(TRUST_PACKAGE.split(",")));
        factory.setDispatchAsync(true);
        factory.setTrustAllPackages(true);
        return factory;
    }
}

