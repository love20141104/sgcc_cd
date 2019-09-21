package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.constant.PrebookStartTimeConstants;
import com.example.constant.WechatURLConstants;
import com.sgcc.DemoApplication;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import com.sgcc.repository.PrebookRedisRepository;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

////
////@RunWith(SpringRunner.class)
////@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
//
//    @Autowired
//    PrebookRedisRepository prebookRedisRepository;
//
//
////    @Test
////    public void sss(){
////        System.out.println(prebookRedisRepository.findAll());
////    }

    public static void main(String[] args) {


            MyThread myThread2 = new MyThread();
            MyThread myThread = new MyThread();
            myThread.start();
            myThread2.start();
    }

     public static class MyThread extends Thread{
        //RestTemplate restTemplate = new RestTemplate();
        @Override
        public void run(){

            Random rand = new Random();

            for(int i = 1;i<100;i++) {
                int j = rand.nextInt(5);
                String Url = "http://localhost:18624/ServiceHall/PrebookInfos/user/RIHAM"+j;
                String json = "{\n" +
                        "  \"contact\": \"联系人\",\n" +
                        "  \"contactTel\": \"联系电话\",\n" +
                        "  \"prebookCode\": \"\",\n" +
                        "  \"prebookDate\":"+" \"2019-09-2"+rand.nextInt(10)+"\",\n" +
                        "  \"prebookStartTime\": \t \""+ PrebookStartTimeConstants.TIME_LIST.get(rand.nextInt(5))+"\",\n" +
                        "  \"serviceHallId\": \"营业厅id2\",\n" +
                        "  \"submitDate\":  \"2019-09-19T14:41:58.928Z\",\n" +
                        "  \"userId\": \"RIHAM"+i+"\"\n" +
                        "}";
                System.out.println(
                        HttpRequest.sendPost(Url
                                ,json) +"    userid : RIHAM"+j

                );

            }

        }
    }
}
