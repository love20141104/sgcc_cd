package com.sgcc;

import com.sgcc.Service.QuestionService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        QuestionService questionService = contextRefreshedEvent.getApplicationContext().getBean(QuestionService.class);
        questionService.initCategory();
        questionService.initQuestion();
        System.out.println("Redis初始化成功");
    }
}
