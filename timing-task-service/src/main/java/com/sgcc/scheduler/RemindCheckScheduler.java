//package com.sgcc.scheduler;
//
//import com.sgcc.job.RemindCheckJob;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RemindCheckScheduler {
//
//    private static final String CRON = "0 0 9 * * 1/5";
////    private static final String CRON = "0/2 * * * * ? ";
//
//    @Bean
//    public JobDetail remindCheckJobDetail(){
//        return JobBuilder
//                .newJob(RemindCheckJob.class)
//                .withIdentity("remindCheck","check")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public CronTrigger remindCheckCronTrigger(){
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(remindCheckJobDetail())
//                .withIdentity("remindCheck","check")
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
