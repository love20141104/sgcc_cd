package com.example.demo.test.ConcurrentTest;

import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@NoArgsConstructor
public class ApiTest {

    /**
     * 定义并发线程数量
     */
    public static int THREAD_NUM = 0;


    /**
     * 开始时间
     */
    private static long startTime = 0L;

    private String url;

    private String interfaceName;

    public ApiTest(String url,String interfaceName,int num) {
        this.url=url;
        this.interfaceName=interfaceName;
        this.THREAD_NUM = num;
    }

    @PostConstruct
    public void init() {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
            startTime = System.currentTimeMillis();
            System.out.println("CountDownLatch started at: " + startTime);
            for (int i = 0; i < THREAD_NUM; i ++) {
                executor.submit(new Run(url,interfaceName));
//                new Thread(new Run(url,interfaceName)).start();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }finally {

        }
    }


    /**
     * 线程类
     */
    private class Run implements Runnable {
        private String url;

        private String interfaceName;

        public Run(String url,String interfaceName) {
            this.url=url;
            this.interfaceName=interfaceName;
        }

        @Override
        public void run() {
            try {
                ConcurrentTestUtil.sendGet(url, interfaceName);
                long endTime = System.currentTimeMillis();
                long spendTime = (endTime - startTime) / 1000;
                System.out.println(" name： " + Thread.currentThread().getName() +
                        " ,ended at: " + endTime +
                        " ,cost: " + spendTime + " s.");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ApiTest apiTest = new ApiTest("https://119.23.22.176:18624/businessGuide/backstageBusinessGuides",
                "后台查询业务指南列表",1000);
        apiTest.init();
    }


}
