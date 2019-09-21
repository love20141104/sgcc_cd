package com.example.demo.test;

import com.example.constant.PrebookStartTimeConstants;
import com.example.demo.HttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestPreBook.class)
public class TestPreBook{

    /**
     * 10个用户同时提交随机三天的预约
     */
    @Test
    public void test1(){
        for(int i = 0;i<10;i++){
            MyThread myThread = new MyThread();
            MyThread myThread2 = new MyThread();
            MyThread myThread3 = new MyThread();
            MyThread myThread4 = new MyThread();
            MyThread myThread5 = new MyThread();
            MyThread myThread6 = new MyThread();
            MyThread myThread7 = new MyThread();
            MyThread myThread8 = new MyThread();
            MyThread myThread9 = new MyThread();
            MyThread myThread0 = new MyThread();
            myThread.setUserId("RIHAM1");
            myThread2.setUserId("RIHAM2");
            myThread3.setUserId("RIHAM3");
            myThread4.setUserId("RIHAM4");
            myThread5.setUserId("RIHAM5");
            myThread6.setUserId("RIHAM6");
            myThread7.setUserId("RIHAM7");
            myThread8.setUserId("RIHAM8");
            myThread9.setUserId("RIHAM9");
            myThread0.setUserId("RIHAM0");
            myThread.start();
            myThread2.start();
            myThread3.start();
            myThread4.start();
            myThread5.start();
            myThread6.start();
            myThread7.start();
            myThread8.start();
            myThread9.start();
            myThread0.start();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 10个用户同时提交同一天不同时间段的预约
     */
    @Test
     public void test4(){
        for(int i = 0;i<10;i++){
            MyThread4 myThread = new MyThread4();
            MyThread4 myThread2 = new MyThread4();
            MyThread4 myThread3 = new MyThread4();
            MyThread4 myThread4 = new MyThread4();
            MyThread4 myThread5 = new MyThread4();
            MyThread4 myThread6 = new MyThread4();
            MyThread4 myThread7 = new MyThread4();
            MyThread4 myThread8 = new MyThread4();
            MyThread4 myThread9 = new MyThread4();
            MyThread4 myThread0 = new MyThread4();
            myThread.setUserId("RIHAM1");
            myThread2.setUserId("RIHAM2");
            myThread3.setUserId("RIHAM3");
            myThread4.setUserId("RIHAM4");
            myThread5.setUserId("RIHAM5");
            myThread6.setUserId("RIHAM6");
            myThread7.setUserId("RIHAM7");
            myThread8.setUserId("RIHAM8");
            myThread9.setUserId("RIHAM9");
            myThread0.setUserId("RIHAM0");
            myThread.start();
            myThread2.start();
            myThread3.start();
            myThread4.start();
            myThread5.start();
            myThread6.start();
            myThread7.start();
            myThread8.start();
            myThread9.start();
            myThread0.start();
            try {
                Thread.currentThread().sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 10个用户同时提交同一天同一个时间段的预约
     */
    @Test
    public void test3(){
//        for(int i = 0;i<10;i++){
        MyThread3 myThread = new MyThread3();
        MyThread3 myThread2 = new MyThread3();
        MyThread3 myThread3 = new MyThread3();
        MyThread3 myThread4 = new MyThread3();
        MyThread3 myThread5 = new MyThread3();
        MyThread3 myThread6 = new MyThread3();
        MyThread3 myThread7 = new MyThread3();
        MyThread3 myThread8 = new MyThread3();
        MyThread3 myThread9 = new MyThread3();
        MyThread3 myThread0 = new MyThread3();
        myThread.setUserId("RIHAM1");
        myThread2.setUserId("RIHAM2");
        myThread3.setUserId("RIHAM3");
        myThread4.setUserId("RIHAM4");
        myThread5.setUserId("RIHAM5");
        myThread6.setUserId("RIHAM6");
        myThread7.setUserId("RIHAM7");
        myThread8.setUserId("RIHAM8");
        myThread9.setUserId("RIHAM9");
        myThread0.setUserId("RIHAM0");
        myThread.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        myThread6.start();
        myThread7.start();
        myThread8.start();
        myThread9.start();
        myThread0.start();
//        }
    }





    /**、
     * 单用户提交10次预约,日期固定随机，时间段随机
     */
    @Test
    public void test2(){
        for(int i = 0;i<10;i++){
            MyThread2 myThread = new MyThread2();
            myThread.setUserId("RIHAM1");
            myThread.start();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyThread2 extends Thread{
        //RestTemplate restTemplate = new RestTemplate();

        String userId = "RIHAM";

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public void run(){

            Random rand = new Random();

//            for(int i = 1;i<20;i++) {
            String Url = "http://localhost:18624/ServiceHall/PrebookInfos/user/"+userId;
            String json = "{\n" +
                    "  \"contact\": \"联系人\",\n" +
                    "  \"contactTel\": \"联系电话\",\n" +
                    "  \"prebookCode\": \"\",\n" +
                    "  \"prebookDate\":"+" \"2019-09-2"+3+"\",\n" +
                    "  \"prebookStartTime\": \t \""+ PrebookStartTimeConstants.TIME_LIST.get(rand.nextInt(5))+"\",\n" +
                    "  \"serviceHallId\": \"营业厅id2\",\n" +
                    "  \"submitDate\":  \"2019-09-21T14:41:58.928Z\",\n" +
                    "  \"userId\": \""+userId+"\"\n" +
                    "}";
            System.out.println(
                    HttpRequest.sendPost(Url
                            ,json) +"    userid : "+userId

            );

//            }

        }
    }


    public class MyThread3 extends Thread{
        //RestTemplate restTemplate = new RestTemplate();

        String userId = "RIHAM";

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public void run(){

            Random rand = new Random();

//            for(int i = 1;i<20;i++) {
            String Url = "http://localhost:18624/ServiceHall/PrebookInfos/user/"+userId;
            String json = "{\n" +
                    "  \"contact\": \"联系人\",\n" +
                    "  \"contactTel\": \"联系电话\",\n" +
                    "  \"prebookCode\": \"\",\n" +
                    "  \"prebookDate\":"+" \"2019-09-2"+3+"\",\n" +
                    "  \"prebookStartTime\": \t \""+ PrebookStartTimeConstants.TIME_LIST.get(3)+"\",\n" +
                    "  \"serviceHallId\": \"营业厅id2\",\n" +
                    "  \"submitDate\":  \"2019-09-21T14:41:58.928Z\",\n" +
                    "  \"userId\": \""+userId+"\"\n" +
                    "}";
            System.out.println(
                    HttpRequest.sendPost(Url
                            ,json) +"    userid : "+userId

            );

//            }

        }
    }

    public class MyThread extends Thread{
        //RestTemplate restTemplate = new RestTemplate();

        String userId = "RIHAM";

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public void run(){

            Random rand = new Random();

//            for(int i = 1;i<20;i++) {
            String Url = "http://localhost:18624/ServiceHall/PrebookInfos/user/"+userId;
            String json = "{\n" +
                    "  \"contact\": \"联系人\",\n" +
                    "  \"contactTel\": \"联系电话\",\n" +
                    "  \"prebookCode\": \"\",\n" +
                    "  \"prebookDate\":"+" \"2019-09-2"+rand.nextInt(3)+"\",\n" +
                    "  \"prebookStartTime\": \t \""+ PrebookStartTimeConstants.TIME_LIST.get(rand.nextInt(5))+"\",\n" +
                    "  \"serviceHallId\": \"营业厅id2\",\n" +
                    "  \"submitDate\":  \"2019-09-21T14:41:58.928Z\",\n" +
                    "  \"userId\": \""+userId+"\"\n" +
                    "}";
            System.out.println(
                    HttpRequest.sendPost(Url
                            ,json) +"    userid : "+userId

            );

//            }

        }
    }

    public class MyThread4 extends Thread{
        //RestTemplate restTemplate = new RestTemplate();

        String userId = "RIHAM";

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public void run(){

            Random rand = new Random();

//            for(int i = 1;i<20;i++) {
            String Url = "http://localhost:18624/ServiceHall/PrebookInfos/user/"+userId;
            String json = "{\n" +
                    "  \"contact\": \"联系人\",\n" +
                    "  \"contactTel\": \"联系电话\",\n" +
                    "  \"prebookCode\": \"\",\n" +
                    "  \"prebookDate\":"+" \"2019-09-2"+3+"\",\n" +
                    "  \"prebookStartTime\": \t \""+ PrebookStartTimeConstants.TIME_LIST.get(rand.nextInt(5))+"\",\n" +
                    "  \"serviceHallId\": \"营业厅id2\",\n" +
                    "  \"submitDate\":  \"2019-09-21T14:41:58.928Z\",\n" +
                    "  \"userId\": \""+userId+"\"\n" +
                    "}";
            System.out.println(
                    HttpRequest.sendPost(Url
                            ,json) +"    userid : "+userId

            );

//            }

        }
    }



}
