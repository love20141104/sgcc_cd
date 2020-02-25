package com.sgcc.job;

import com.example.Utils;
import com.sgcc.service.PrebooksService;
import com.sgcc.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class RemindTakeTicketJob extends QuartzJobBean {

    @Autowired
    private PrebooksService prebooksService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        if (jobKey.getName().equals("remind_half_hour") && jobKey.getGroup().equals("half_hour")){
            long currentTime = System.currentTimeMillis() ;
            currentTime +=30*60*1000;
            Date date = new Date(currentTime);
            String startDate = Utils.GetTime(date);
            prebooksService.advanceSendMessage(startDate);
            System.out.println("提前半小时提醒用户取票");
        }else {
            String startDate = DateUtils.addDate(Utils.GetTime(new Date()),1);
            prebooksService.advanceSendMessage(startDate);
            System.out.println("提前最后一小时提醒用户取票");
        }
    }
}
