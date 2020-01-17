//package com.sgcc.scheduler;
//
//import com.sgcc.job.RemindTakeTicketJob;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RemindTakeTicketScheduler {
//
//    private static final String HOUR_CRON = "0 30 9-14 * * ?";
//    private static final String HALF_HOUR_CRON = "0 0 10-15 * * ?";
//
////    private static final String HOUR_CRON = "0/2 * * * * ?";
////    private static final String HALF_HOUR_CRON = "0/3 * * * * ?";
//
//    @Bean
//    public JobDetail remindHalfHourJobDetail(){
//        return JobBuilder
//                .newJob(RemindTakeTicketJob.class)
//                .withIdentity("remind_half_hour","half_hour")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public CronTrigger remindHalfHourCronTrigger(){
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(remindHalfHourJobDetail())
//                .withIdentity("remind_half_hour","remind")
//                .withSchedule(CronScheduleBuilder
//                        .cronSchedule(HALF_HOUR_CRON)
//                        .withMisfireHandlingInstructionDoNothing())
//                .startNow()
//                .build();
//    }
//
//
//
//    @Bean
//    public JobDetail remindHourJobDetail(){
//        return JobBuilder
//                .newJob(RemindTakeTicketJob.class)
//                .withIdentity("remind_hour","hour")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public CronTrigger remindHourCronTrigger(){
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(remindHourJobDetail())
//                .withIdentity("remind_hour","remind")
//                .withSchedule(CronScheduleBuilder
//                        .cronSchedule(HOUR_CRON)
//                        .withMisfireHandlingInstructionDoNothing())
//                .startNow()
//                .build();
//    }
//
//
//
//
//}
