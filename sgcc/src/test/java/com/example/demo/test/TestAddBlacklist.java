package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ThreeTypeOneApi.SM3Util;
import com.example.ThreeTypeOneApi.SM4Util;
import com.example.result.Result;
import com.sgcc.DemoApplication;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.*;
import com.sgcc.entity.LineUpEntity;
import com.sgcc.entity.query.PrebookInfoQueryEntity;
import com.sgcc.service.HouseholdService;
import com.sgcc.service.LineUpService;
import com.sgcc.sgccenum.SubscribeCateEnum;
import com.sgcc.utils.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestAddBlacklist.class, DemoApplication.class})
public class TestAddBlacklist {
    @Autowired
    private PrebookInfoQueryEntity prebookInfoQueryEntity;
    @Autowired
    private LineUpService lineUpService;

    @Autowired
    private LineUpEntity lineUpEntity;


    @Test
    public void testOrgGetData() throws Exception {
        Map<String,String> data = new LinkedHashMap<>();
        data.put("hallId","123456");
        data.put("busiId","11111");
//        data.put("phone","13094494386");

        InputDataDTO inputDataDTO = new InputDataDTO();
        inputDataDTO.setServiceCode("lineUpQuery");
        inputDataDTO.setAppId("axlz9zv2p02v03t4ks");
        inputDataDTO.setDeviceId("6722d35aa124a82d");
        inputDataDTO.setData(data);

        BasicInputDTO basicInputDTO = new BasicInputDTO();
        basicInputDTO.setData(inputDataDTO);
        System.out.println(JSONObject.toJSONString(basicInputDTO));

        LineUpInfoOutDTO JSONObject = lineUpEntity.lineUpQuery(basicInputDTO);
        System.out.println(JSONObject.toString());
    }


    @Test
    public void testlineUp() throws Exception {
        Map<String,String> data = new LinkedHashMap<>();
        data.put("hallId","123456");
        data.put("busiId","11111");
        data.put("phone","13094494386");

        InputDataDTO inputDataDTO = new InputDataDTO();
        inputDataDTO.setServiceCode("onlineQueuing");
        inputDataDTO.setAppId("axlz9zv2p02v03t4ks");
        inputDataDTO.setDeviceId("6722d35aa124a82d");
        inputDataDTO.setData(data);

        BasicInputDTO basicInputDTO = new BasicInputDTO();
        basicInputDTO.setData(inputDataDTO);
        System.out.println(JSONObject.toJSONString(basicInputDTO));

//        String JSONObject = lineUpEntity.test(basicInputDTO);
//        System.out.println(JSONObject);
    }


    @Test
    public void testHeartBeat() throws Exception {
//        InputDataDTO inputDataDTO = new InputDataDTO();
//        inputDataDTO.setServiceCode("heartbeat");
//        inputDataDTO.setAppId("axlz9zv2p02v03t4ks");
//        inputDataDTO.setDeviceId("6722d35aa124a82d");
//
//        BasicInputDTO basicInputDTO = new BasicInputDTO();
//        basicInputDTO.setData(inputDataDTO);
//        System.out.println(JSONObject.toJSONString(basicInputDTO));
//
//        ReturnResultDTO JSONObject = lineUpEntity.test(basicInputDTO);
//        System.out.println(JSONObject.toString());
    }


    @Test
    public void testHeartBeant(){
        System.out.println(lineUpService.heartBeat());
    }


    @Test
    public void testOnlineQueuing(){
        OnlineQueuingInputDTO onlineQueuingInputDTO = new OnlineQueuingInputDTO(
                "123123",
                "张三年",
                "18582106276"
        );
        System.out.println(lineUpService.onlineQueuing(onlineQueuingInputDTO));
    }

    @Test
    public void testLineUpQuery(){
        System.out.println(lineUpService.lineUpQuery(new LineUpQueryInputDTO("WA003")));
    }


}
