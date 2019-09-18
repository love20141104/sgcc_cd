package com.example.demo;

import com.example.constant.WechatURLConstants;
import com.sgcc.DemoApplication;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
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
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {


    public static void main(String[] args) {
        Date date = new Date();
        DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.print(timestamp);

//        for(int i = 0;i<10;i++){
//            MyThread myThread1 = new MyThread();
//            MyThread myThread2 = new MyThread();
////            myThread1.start();
//            myThread2.start();
//        }


    }
//    @Test
//    public  void contextLoads() {
//        Date date = new Date();
//
//        Timestamp timestamp = new Timestamp(date.getTime());
//        System.out.print(timestamp);
//    }

//     public static class MyThread extends Thread{
//        //RestTemplate restTemplate = new RestTemplate();
//        @Override
//        public void run(){
//            for(int i = 0;i<100;i++) {
//
//                HttpRequest.sendGet("http://localhost:18624/ServiceHall/PrebookInfos", null);
//            }
//            //restTemplate.getForObject("http://localhost:18624/ServiceHall/PrebookInfos",Result.class);
//        }
//
//    }
}
