package com.sgcc.job;

import com.sgcc.service.PrebooksService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RemindCheckJob extends QuartzJobBean {

    @Autowired
    private PrebooksService prebooksService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        prebooksService.advanceSendMessageToChecker();
        System.out.println("提醒审核人审核");
    }
}
