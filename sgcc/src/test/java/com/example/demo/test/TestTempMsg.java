package com.example.demo.test;

import com.sgcc.DemoApplication;
import com.sgcc.service.WeChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestTempMsg {

    @Autowired
    private WeChatService weChatService;

    @Test
    public void testMsg() throws Exception {
//        System.out.println(weChatService.sendTempMsg("o7sDrso9Jk1F_lhoItpSY2xTqEmY").toString());

    }


}
