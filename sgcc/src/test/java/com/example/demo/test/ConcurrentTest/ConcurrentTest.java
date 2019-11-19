package com.example .demo.test.ConcurrentTest;

import com.sgcc.DemoApplication;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class ConcurrentTest {


    @Test
    public void testRenameAndTransferInterfaceForInhabitant() {
        ApiTest apiTest = new ApiTest("https://119.23.22.176:18624/BusinessService/renameAndTransfer/inhabitant",
                "更名过户-查询",6500);
        apiTest.init();
    }


    @Test
    public void testBusinessGuideInterfaceForFrontEndBusinessGuides() {
        String cid = "2";
        ApiTest apiTest = new ApiTest("https://119.23.22.176:18624/businessGuide/backstageBusinessGuides?'"+cid+"'",
                "查询业务指南列表",20000);
        apiTest.init();

    }


    @Test
    public void testBusinessGuideInterfaceForBackstageBusinessGuides() {

        ApiTest apiTest = new ApiTest("https://119.23.22.176:18624/businessGuide/backstageBusinessGuides",
                "后台查询业务指南列表",100);
        apiTest.init();

    }





}
