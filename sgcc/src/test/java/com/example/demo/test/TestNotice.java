package com.example.demo.test;

import com.sgcc.DemoApplication;

import com.sgcc.dao.*;
import com.sgcc.entity.JobEntity;
import com.sgcc.entity.RepairProgressEntity;
import com.sgcc.entity.event.HouseholdEventEntity;
import com.sgcc.entity.query.HouseholdQueryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestNotice.class, DemoApplication.class})
public class TestNotice {
    @Autowired
    private HouseholdEventEntity householdEventEntity;
    @Autowired
    private HouseholdQueryEntity householdQueryEntity;


    @Autowired
    private JobEntity jobEntity;
    @Autowired
    private RepairProgressEntity repairProgressEntity;
    @Test
    public void test0(){

    }
    @Test
    public void test1(){
        String userOpenId="qwwe";
        String uuid = UUID.randomUUID().toString();
        HouseholdInfoDao householdInfoDao=new HouseholdInfoDao(
                uuid
                ,"lxx"
                ,"111"
                ,"tfwj"
                ,true
                ,"居民"
                ,"123"
                ,true
        );
        householdEventEntity.saveHousehold(userOpenId,householdInfoDao);
    }
    @Test
    public void test2(){
        householdEventEntity.unavailableUserHouseHold("qwwe");
    }

    @Test
    public void test3(){
        List<UserSubscribeDao> userSubscribeList = householdQueryEntity.getUserSubscribeList(false);
        userSubscribeList.size();
    }

    @Test
    public void test4(){
        JobDao jobDao = new JobDao(
                UUID.randomUUID().toString()
                , UUID.randomUUID().toString()
                , "1234useropenid"
                , "1001"

                , "未完成"
                , "zhangsan"
                , "leiji"
                , new Date());
        jobEntity.saveJob(jobDao);
    }
    @Test
    public void test5(){
        RepairProgressDao repairProgressDao = new RepairProgressDao(
                UUID.randomUUID().toString()
                ,"1234useropenid"
                ,"bc295cc0-27ee-4e18-9cc4-1be3e7dd3829"
                , "抢修中"
                ,new Date()
                ,"11"
                ,"21"
                ,"31"
        );
//        repairProgressEntity.saveRepairProgress(repairProgressDao);
    }
    @Test
    public void test6(){
        RepairProgressDao repairProgressDao = new RepairProgressDao(
                UUID.randomUUID().toString()
                ,"1234useropenid"
                ,"bc295cc0-27ee-4e18-9cc4-1be3e7dd3829"
                , "已完成"
                ,new Date()
                ,""
                ,""
                ,""
        );
//        repairProgressEntity.saveRepairProgress(repairProgressDao);
    }
    @Test
    public void test7(){
////        List<RepairProgressDao> repairProgressDaos = repairProgressEntity.selectRepairProgressListByNoticeId("1001");
//        repairProgressDaos.forEach(progressDao -> {
//            System.out.println(progressDao.getProgressStatus());
//        });
    }
    @Test
    public void test8(){
        List<NoticeAndJobDao> noticeAndJobDaos = jobEntity.selectNoticeAndJob();
        noticeAndJobDaos.forEach(noticeAndJobDao -> {
            System.out.println(noticeAndJobDao.getId());
        });
    }
}
