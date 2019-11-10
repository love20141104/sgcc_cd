package com.sgcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.sgcc")
public class ManagementTool {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ManagementTool.class);
        springApplication.run(args);
        System.out.println("ManagementTool start!");
    }

}
