//package com.sgcc.Scheduler;
//
//import com.example.Utils;
//import com.sgcc.service.PrebooksService;
//import com.sgcc.utils.DateUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class PrebookSchedule {
//
//    @Autowired
//    private PrebooksService prebooksService;
//
//
//    @Scheduled(cron = "0 50 23 * * ? ")
//    public void processAddBlackList(){
//        prebooksService.addBlackList(new Date());
//        System.out.println("存取当天没有爽约的用户");
//    }
//
//
//    @Scheduled(cron = "0 30 9-14 * * ? ")
//    public void processSendMessage1(){
//        String startDate = DateUtils.addDate(Utils.GetTime(new Date()),1);
//        prebooksService.advanceSendMessage(startDate);
//        System.out.println("提前最后一小时提醒用户取票");
//    }
//
//    @Scheduled(cron = "0 0 10-15 * * ? ")
//    public void processSendMessage2(){
//        long currentTime = System.currentTimeMillis() ;
//        currentTime +=30*60*1000;
//        Date date = new Date(currentTime);
//        String startDate = Utils.GetTime(date);
//        prebooksService.advanceSendMessage(startDate);
//        System.out.println("提前半小时提醒用户取票");
//    }
//
////    @Scheduled(cron = "0 0 9 * * 1/5 ")
////    public void processSendMessage3(){
////        prebooksService.advanceSendMessageToChecker();
////        System.out.println("提醒审核人审核");
////    }
//
//
//}
