package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.example.result.Result;
import com.sgcc.DemoApplication;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.entity.query.PrebookInfoQueryEntity;
import com.sgcc.service.HouseholdService;
import com.sgcc.sgccenum.SubscribeCateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestAddBlacklist.class, DemoApplication.class})
public class TestAddBlacklist {
    @Autowired
    private PrebookInfoQueryEntity prebookInfoQueryEntity;

    @Test
    public void testAddBlacklist(){
        List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getNotTakeTicketList();
        System.out.println(prebookInfoDaos.size());
    }

}
