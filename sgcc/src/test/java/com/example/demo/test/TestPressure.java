package com.example.demo.test;

import com.example.Utils;
import com.example.demo.PressureTestUtil;
import com.sgcc.DemoApplication;
import com.sgcc.dto.BusinessCategoryDto;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class TestPressure {


    @Test
    public void testBusinessGuideInterface() throws JSONException {


//        String url ="https://119.23.22.176:18624/businessGuide/businessGuides?cid=2";




//        Map<String,Object> map = new LinkedHashMap<>();
//        map.put("district","district");
//        map.put("range","range");
//        map.put("time","time");
//        map.put("typeName","typeName");
//
//        PressureTestUtil.sendPost("https://119.23.22.176:18624/notice/noticeInfo",map,"新增停电公告");


        Thread01 thread01 = new Thread01();
        Thread02 thread02 = new Thread02();
        Thread03 thread03 = new Thread03();
        System.out.println("----------------接口测试开始-------------------");
        long t1 = System.currentTimeMillis();
        System.out.println("测试开始时间："+ Utils.GetCurrentTime());
        new Thread(thread01,"thread01").start();
        new Thread(thread02,"thread02").start();
        new Thread(thread03,"thread03").start();
        long t2 = System.currentTimeMillis();
        System.out.println("测试结束时间："+Utils.GetCurrentTime());
        System.out.println("总耗时："+(t2-t1));
        System.out.println("----------------接口测试结束-------------------");
    }



    class Thread01 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                String url = "https://119.23.22.176:18624/businessGuide/categorys";
                try {
                    System.out.println("线程名："+Thread.currentThread().getName());
                    PressureTestUtil.sendGet(url,"查询业务指南列表分类");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Thread02 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                String url = "https://119.23.22.176:18624/businessGuide/categorys";
                try {
                    System.out.println("线程名："+Thread.currentThread().getName());
                    PressureTestUtil.sendGet(url,"查询业务指南列表分类");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Thread03 implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                String url = "https://119.23.22.176:18624/businessGuide/categorys";
                try {
                    System.out.println("线程名："+Thread.currentThread().getName());
                    PressureTestUtil.sendGet(url,"查询业务指南列表分类");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
