package com.example.demo.test;

import com.sgcc.DemoApplication;
import com.sgcc.dao.PreBookDao;
import com.sgcc.repository.PreBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestPreBook {

    @Autowired
    private PreBookRepository preBookRepository;

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
