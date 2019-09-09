package com.example.demo.test;

import com.example.demo.DemoApplicationTests;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.service.ServiceHallService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestServiceHall extends DemoApplicationTests{

    @Autowired
    private ServiceHallService serviceHallService;

    /**
     * 查询
     */
    @Test
    public void getHallList(){
        serviceHallService.findHallList();
    }


    /**
     * 增加
     */
    @Test
    public void addServiceHall(){
        List<ServiceHallDao> serviceHallDaoList = new ArrayList<ServiceHallDao>();
        ServiceHallDao dao = null;
        dao= new ServiceHallDao();
        dao.setId("10003");
        dao.setServiceHallName("测试网点名字3");
        dao.setServiceHallAddr("测试网点地址3");
        dao.setOpenTime("8:30-17:30");
        dao.setServiceHallTel("66666666662");
        dao.setServiceHallLatitude(102.05);
        dao.setServiceHallLongitude(50.0);
        dao.setServiceHallDistrict("测试网点地区3");
        dao.setServiceHallAvailable(true);
        serviceHallDaoList.add(dao);

        dao = new ServiceHallDao();
        dao.setId("10004");
        dao.setServiceHallName("测试网点名字4");
        dao.setServiceHallAddr("测试网点地址4");
        dao.setOpenTime("8:30-17:30");
        dao.setServiceHallTel("66666666664");
        dao.setServiceHallLatitude(102.05);
        dao.setServiceHallLongitude(50.0);
        dao.setServiceHallDistrict("测试网点地区4");
        dao.setServiceHallAvailable(true);

        serviceHallDaoList.add(dao);

        serviceHallService.saveServiceHall(serviceHallDaoList);
    }

    /**
     * 删除
     */
    @Test
    public void delServiceHall(){
        List<ServiceHallDao> serviceHallDaoList = new ArrayList<ServiceHallDao>();
        ServiceHallDao dao =null;
        dao = new ServiceHallDao();
        dao.setServiceHallId("558d0314-08fe-4743-b");

        serviceHallDaoList.add(dao);
        dao = new ServiceHallDao();
        dao.setServiceHallId("09f2bdb8-060e-4177-b");
        serviceHallDaoList.add(dao);

        serviceHallService.delServiceHall(serviceHallDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updateServiceHall(){
        List<ServiceHallDao> serviceHallDaoList = new ArrayList<ServiceHallDao>();
        ServiceHallDao dao =null;
        dao = new ServiceHallDao();
        dao.setServiceHallId("558d0314-08fe-4743-b");
        dao.setServiceHallName("网点10003");
        dao.setServiceHallAddr("网点10003地址");

        serviceHallDaoList.add(dao);
        dao = new ServiceHallDao();
        dao.setServiceHallId("09f2bdb8-060e-4177-b");
        dao.setServiceHallName("网点10004");
        dao.setServiceHallAddr("网点10004地址");
        serviceHallDaoList.add(dao);

        serviceHallService.updateServiceHall(serviceHallDaoList);
    }



}
