//package com.sgcc.scheduler;
//
//import com.sgcc.job.AddBlackListJob;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AddBlackListScheduler {
//
//    private static final String CRON = "0 50 23 * * ? ";
////    private static final String CRON = "0/2 * * * * ? ";
//
//    @Bean
//    public JobDetail blackListJobDetail(){
//        return JobBuilder
//                .newJob(AddBlackListJob.class)
//                .withIdentity("addBlackList","blackList")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public CronTrigger blackListCronTrigger(){
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(blackListJobDetail())
//                .withIdentity("addBlackList","blackList")
//                .withSchedule(CronScheduleBuilder
//                        .cronSchedule(CRON)
//                        .withMisfireHandlingInstructionDoNothing())
//                .startNow()
//                .build();
//    }
//
//
//
//
//}
