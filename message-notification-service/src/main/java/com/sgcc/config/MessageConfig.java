//package com.sgcc.config;
//
//import com.esms.PostMsg;
//import com.esms.common.entity.Account;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessageConfig {
//        @Value("${spring.message.cm-host}")
//        private String cmHost;
//        @Value("${spring.message.cm-host-port}")
//        private int cmHostPort;
//        @Value("${spring.message.ws-host}")
//        private String wsHost;
//        @Value("${spring.message.ws-host-port}")
//        private int wsHostPort;
//        @Value("${spring.message.account.username}")
//        private String username;
//        @Value("${spring.message.account.password}")
//        private String password;
//
//        @Bean
//        public PostMsg postMsg(){
//            PostMsg pm = new PostMsg(false, 7000, 1) ;
//            pm.getCmHost().setHost(cmHost, cmHostPort); //设置网关的IP和port，用于发送信息
//            pm.getWsHost().setHost(wsHost, wsHostPort); //设置网关的IP和port，用于获取账号信息、上行、状态报告等等
//            return pm;
//        }
//
//        @Bean
//        public Account account(){
//            return new Account(username, password);
//        }
//
//}
