package com.sgcc;

import com.sgcc.service.ServiceHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.sgcc"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
        System.out.println("DemoApplication start!");
    }

}
