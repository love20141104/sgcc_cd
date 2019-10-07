package com.example.demo.test;

import com.example.demo.DemoApplicationTests;
import com.example.result.Result;
import com.sgcc.DemoApplication;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.repository.ServiceHallRepository;
import com.sgcc.service.ServiceHallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestServiceHall{

    @Autowired
    private ServiceHallService serviceHallService;

    @Autowired
    private ServiceHallRepository serviceHallRepository;

//    /**
//     * 查询
//     */
//    @Test
//    public void getHallList(){
//        Result rs = serviceHallService.findHallList();
//        if( rs.getResultCode() == 0 )
//        {
//            rs = serviceHallService.findHallList();
//            if( rs.getResultCode() == 0 ){}
//        }
//    }

//    @Test
//    public void NearestServiceHallsTest()
//    {
//        double lat = 30.540936;
//        double lng = 104.075187;
//        Result rs = serviceHallService.NearestServiceHalls(lat,lng);
//        if( rs.getResultCode() == 0 )
//        {
//            List<ServiceHall_ComputedDistanceDTO> dtos = (List<ServiceHall_ComputedDistanceDTO>)rs.getData();
//            if( dtos == null )
//            {
//
//
//            }
//        }
//    }
//    @Test
//    public void serviceHallsTest()
//    {
//        String district = "高新区";
//        Result rs = serviceHallService.serviceHalls(district);
//        if( rs.getResultCode() == 0 )
//        {
//            List<ServiceHall_ComputedDistanceDTO> dtos = (List<ServiceHall_ComputedDistanceDTO>)rs.getData();
//            if( dtos == null )
//            {
//
//
//            }
//        }
//    }

    /**
     * 增加
     */
//    @Test
//    public void addServiceHall(){
//        List<ServiceHallDao> serviceHallDaoList = new ArrayList<ServiceHallDao>();
//        ServiceHallDao dao = null;
//        dao= new ServiceHallDao();
//        dao.setId("10003");
//        dao.setServiceHallName("测试网点名字3");
//        dao.setServiceHallAddr("测试网点地址3");
//        dao.setServiceHallOpenTime("8:30-17:30");
//        dao.setServiceHallTel("66666666662");
//        dao.setServiceHallLatitude(102.05);
//        dao.setServiceHallLongitude(50.0);
//        dao.setServiceHallDistrict("测试网点地区3");
//        dao.setServiceHallAvailable(true);
//        serviceHallDaoList.add(dao);
//
//        dao = new ServiceHallDao();
//        dao.setId("10004");
//        dao.setServiceHallName("测试网点名字4");
//        dao.setServiceHallAddr("测试网点地址4");
//        dao.setServiceHallOpenTime("8:30-17:30");
//        dao.setServiceHallTel("66666666664");
//        dao.setServiceHallLatitude(102.05);
//        dao.setServiceHallLongitude(50.0);
//        dao.setServiceHallDistrict("测试网点地区4");
//        dao.setServiceHallAvailable(true);
//
//        serviceHallDaoList.add(dao);
//
//        serviceHallService.saveServiceHall(serviceHallDaoList);
//    }

    @Test
    public void addServiceHall(){
        ServiceHallDao dao = new ServiceHallDao();
        dao.setId("10003");
        dao.setServiceHallId(UUID.randomUUID().toString());
        dao.setServiceHallName("测试网点名字3");
        dao.setServiceHallAddr("测试网点地址3");
        dao.setServiceHallOpenTime("8:30-17:30");
        dao.setServiceHallTel("66666666662");
        dao.setServiceHallOwner("测试所在供电所");
        dao.setServiceHallTraffic("公交102");
        dao.setServiceHallRank("rank");
        dao.setServiceHallCollect(true);
        dao.setServiceHallLatitude(102.05);
        dao.setServiceHallLongitude(50.0);
        dao.setServiceHallDistrict("测试网点地区3");
        dao.setServiceHallBusinessDesc("");
        dao.setServiceHallLandmarkBuilding("");
        dao.setServiceHallBusinessDistrict("");

        serviceHallRepository.saveServiceHall(dao);
    }


    /**
     * 删除
     */
//    @Test
//    public void delServiceHall(){
//        List<ServiceHallDao> serviceHallDaoList = new ArrayList<ServiceHallDao>();
//        ServiceHallDao dao =null;
//        dao = new ServiceHallDao();
//        dao.setServiceHallId("558d0314-08fe-4743-b");
//
//        serviceHallDaoList.add(dao);
//        dao = new ServiceHallDao();
//        dao.setServiceHallId("09f2bdb8-060e-4177-b");
//        serviceHallDaoList.add(dao);
//
//        serviceHallService.delServiceHall(serviceHallDaoList);
//    }

    @Test
    public void delServiceHall(){
        List<String> list = new ArrayList<String>();
        list.add("6044d59f-55b3-4433-a1ce-782fb639f475");

        serviceHallRepository.delServiceHalls(list);
    }

    /**
     * 修改
     */
    @Test
    public void updateServiceHall(){
        ServiceHallDao dao = new ServiceHallDao();
        dao.setServiceHallId("6044d59f-55b3-4433-a1ce-782fb639f475");
        dao.setServiceHallName("测试网点");
        dao.setServiceHallAddr("测试网点地址");
        serviceHallRepository.updateServiceHall(dao);
    }



}
