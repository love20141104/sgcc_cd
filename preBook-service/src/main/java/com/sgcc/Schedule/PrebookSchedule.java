package com.sgcc.Schedule;

import com.example.Utils;
import com.sgcc.service.PrebooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PrebookSchedule {

    @Autowired
    private PrebooksService prebooksService;


    @Scheduled(cron = "0 0 0 * * ? ")
    public void processAddBlackList(){
        prebooksService.addBlackList();
        System.out.println("存取当天没有爽约的用户");
    }


    @Scheduled(cron = "0 0 8-15 * * ? ")
//    @Scheduled(cron = "0/30 * * * * ? ")
    public void processSendMessage1(){
        prebooksService.advanceSendMessage(Utils.GetTime(new Date()));
        System.out.println("存取当天没有爽约的用户");
    }






}
