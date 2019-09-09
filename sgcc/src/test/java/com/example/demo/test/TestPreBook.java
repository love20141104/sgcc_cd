package com.example.demo.test;

import com.example.demo.DemoApplicationTests;
import com.sgcc.dao.BusinessTypeDao;
import com.sgcc.dao.PreBookDao;
import com.sgcc.repository.BusinessTypeRepository;
import com.sgcc.repository.PreBookRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class TestPreBook extends DemoApplicationTests{

    @Autowired
    private PreBookRepository preBookRepository;

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    /**************************************业务类型增删改查*************************************/
    /**
     * 查询
     */
    @Test
    public void getBTypeList(){
        List<BusinessTypeDao> businessTypeDaoList = businessTypeRepository.findAllBTypeList();
        for (int i = 0; i < businessTypeDaoList.size(); i++) {
            System.out.println(businessTypeDaoList.get(i).getBusinessType());
        }
    }


    /**
     * 增加
     */
    @Test
    public void addBType(){
        List<BusinessTypeDao> businessTypeDaoList = new ArrayList<BusinessTypeDao>();
        BusinessTypeDao dao = null;
        dao= new BusinessTypeDao();
        dao.setId("10001");
        dao.setBusinessTypeId(UUID.randomUUID().toString().substring(0,28));
        dao.setBusinessType("一级业务");
        dao.setOrder(101);
        businessTypeDaoList.add(dao);

        dao= new BusinessTypeDao();
        dao.setId("10002");
        dao.setBusinessTypeId(UUID.randomUUID().toString().substring(0,28));
        dao.setBusinessType("二级业务");
        dao.setOrder(102);
        businessTypeDaoList.add(dao);

        businessTypeRepository.addBType(businessTypeDaoList);
    }

    /**
     * 删除
     */
    @Test
    public void delBType(){
        List<BusinessTypeDao> businessTypeDaoList = new ArrayList<BusinessTypeDao>();
        BusinessTypeDao dao =null;
        dao = new BusinessTypeDao();
        dao.setBusinessTypeId("5a2ba620-a08e-4971-a7b2-c893");

        businessTypeDaoList.add(dao);
        dao = new BusinessTypeDao();
        dao.setBusinessTypeId("3e5b94cb-ff31-4be6-836b-4eca");
        businessTypeDaoList.add(dao);

        businessTypeRepository.delBType(businessTypeDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updateBType(){
        List<BusinessTypeDao> businessTypeDaoList = new ArrayList<BusinessTypeDao>();
        BusinessTypeDao dao =null;
        dao = new BusinessTypeDao();
        dao.setBusinessTypeId("5a2ba620-a08e-4971-a7b2-c893");
        dao.setBusinessType("特级业务");
        businessTypeDaoList.add(dao);

        dao = new BusinessTypeDao();
        dao.setBusinessTypeId("3e5b94cb-ff31-4be6-836b-4eca");
        dao.setBusinessType("超级业务");
        businessTypeDaoList.add(dao);

        businessTypeRepository.updateBType(businessTypeDaoList);
    }


    /**************************************预约信息增删改查*************************************/
    /**
     * 查询
     */
    @Test
    public void getPreBookList(){
        preBookRepository.findAllPreBookList();
    }


    /**
     * 增加
     */
    @Test
    public void addPreBook(){
        List<PreBookDao> PreBookDaoList = new ArrayList<PreBookDao>();
        PreBookDao dao = null;
        dao= new PreBookDao();
        dao.setId("10001");
        dao.setUserId("6666");
        dao.setServiceHallId("010f7bce-d594-4e0f-a");
        dao.setPrebookDate(new Date());
        dao.setPrebookStartTime(new Timestamp(new Date().getTime()));
        dao.setBusinessTypeId("166566");
        dao.setPrebookCode("4646464");
        dao.setContact("张三");
        dao.setContactTel("3333333");
        dao.setSubmitDate(new Timestamp(new Date().getTime()));
        PreBookDaoList.add(dao);

        dao= new PreBookDao();
        dao.setId("10002");
        dao.setUserId("8888");
        dao.setServiceHallId("010f7bce-d594-4e0f-a");
        dao.setPrebookDate(new Date());
        dao.setPrebookStartTime(new Timestamp(new Date().getTime()));
        dao.setBusinessTypeId("166566");
        dao.setPrebookCode("4646464");
        dao.setContact("李四");
        dao.setContactTel("4444444");
        dao.setSubmitDate(new Timestamp(new Date().getTime()));
        PreBookDaoList.add(dao);

        preBookRepository.addPreBook(PreBookDaoList);
    }

    /**
     * 删除
     */
    @Test
    public void delPreBook(){
        List<PreBookDao> PreBookDaoList = new ArrayList<PreBookDao>();
        PreBookDao dao =null;
        dao = new PreBookDao();
        dao.setId("10001");

        PreBookDaoList.add(dao);
        dao = new PreBookDao();
        dao.setId("10002");
        PreBookDaoList.add(dao);

        preBookRepository.delPreBook(PreBookDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updatePreBook(){
        List<PreBookDao> PreBookDaoList = new ArrayList<PreBookDao>();
        PreBookDao dao =null;
        dao = new PreBookDao();
        dao.setId("10001");
        dao.setContact("王麻子");
        dao.setContactTel("9999999");
        PreBookDaoList.add(dao);

        dao = new PreBookDao();
        dao.setId("10002");
        dao.setContact("王武");
        dao.setContactTel("5555555");
        PreBookDaoList.add(dao);

        preBookRepository.updatePreBook(PreBookDaoList);
    }



}
