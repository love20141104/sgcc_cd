package com.sgcc.job;

import com.sgcc.service.PrebooksService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class AddBlackListJob extends QuartzJobBean {

    @Autowired
    private PrebooksService prebooksService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        prebooksService.addBlackList(new Date());
        System.out.println("存取当天没有爽约的用户");
    }
}
