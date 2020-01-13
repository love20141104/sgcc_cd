package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ThreeTypeOneApi.SM3Util;
import com.example.ThreeTypeOneApi.SM4Util;
import com.example.result.Result;
import com.sgcc.DemoApplication;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.BasicInputDTO;
import com.sgcc.dto.InputDataDTO;
import com.sgcc.dto.LineUpQueryInputDTO;
import com.sgcc.dto.OnlineQueuingInputDTO;
import com.sgcc.entity.LineUpEntity;
import com.sgcc.entity.query.PrebookInfoQueryEntity;
import com.sgcc.service.HouseholdService;
import com.sgcc.service.LineUpService;
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
    @Autowired
    private LineUpService lineUpService;

    @Test
    public void testAddBlacklist() throws Exception {
        InputDataDTO inputDataDTO = new InputDataDTO();
        inputDataDTO.setServiceCode("464646");
        inputDataDTO.setAppId("123123");
        inputDataDTO.setDeviceId("44644");

        BasicInputDTO basicInputDTO = new BasicInputDTO();
//        basicInputDTO.setAppId("123123");
        basicInputDTO.setData(inputDataDTO);
//        basicInputDTO.setSignature("dfqd");

        System.out.println(JSONObject.toJSONString(basicInputDTO));

        String json = JSONObject.toJSONString(basicInputDTO);
        String cipher = SM4Util.encryptEcb("009322d3d8d62e018ff688c7226f9719eeade1371b95a00825676c1822d370a4ab", json);
        String s = SM3Util.encrypt(json);
        System.out.println("sm3加密===>"+SM3Util.encrypt(json));
        System.out.println("sm3解密===>"+SM3Util.verify(json,s));

//        List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getNotTakeTicketList();
//        System.out.println(prebookInfoDaos.size());
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
