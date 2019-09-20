package com.example.demo.test;

import com.example.demo.DemoApplicationTests;
import com.sgcc.DemoApplication;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionImgDao;
import com.sgcc.repository.SuggestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestSuggestion{

    @Autowired
    private SuggestionRepository suggestionRepository;


    /****************************************SuggestionDao**********************************************/
    /**
     * 测试查询所有意见图片信息
     */
    @Test
    public void findAllSuggestionImg(){
        List<SuggestionImgDao> suggestionDaoList =suggestionRepository.findAllSuggestionImg();
        for (int i = 0; i < suggestionDaoList.size(); i++) {
            System.out.println(suggestionDaoList.get(i).getImgUrl());
        }
    }

    /**
     *测试添加意见图片信息
     */
    @Test
    public void addSuggestionImg(){
        List<SuggestionImgDao> suggestionImgDaoList = new ArrayList<SuggestionImgDao>();
        SuggestionImgDao suggestionImgDao = null;

        suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setId(UUID.randomUUID().toString());
        suggestionImgDao.setImgId(UUID.randomUUID().toString());
        suggestionImgDao.setUserId(UUID.randomUUID().toString());
        suggestionImgDao.setImgUrl("/opt/test1.jpg");
        suggestionImgDao.setSubmitDate(new Date());
        suggestionImgDaoList.add(suggestionImgDao);

        suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setId(UUID.randomUUID().toString());
        suggestionImgDao.setImgId(UUID.randomUUID().toString());
        suggestionImgDao.setUserId(UUID.randomUUID().toString());
        suggestionImgDao.setImgUrl("/opt/test2.jpg");
        suggestionImgDao.setSubmitDate(new Date());
        suggestionImgDaoList.add(suggestionImgDao);

        suggestionRepository.addSuggestionImg(suggestionImgDaoList);
    }

    /**
     * 测试修改意见图片信息
     */
    @Test
    public void updateSuggestionImg(){
        List<SuggestionImgDao> suggestionImgDaoList = new ArrayList<SuggestionImgDao>();
        SuggestionImgDao suggestionImgDao = null;

        suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setImgUrl("/opt/test3.jpg");
        suggestionImgDao.setImgId("680ef49d-725d-46b1-b11a-7587ab3fb9b4");
        suggestionImgDaoList.add(suggestionImgDao);

        suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setImgUrl("/opt/test4.jpg");
        suggestionImgDao.setImgId("31562843-aff4-48ac-81ec-e404258ed435");
        suggestionImgDaoList.add(suggestionImgDao);

        suggestionRepository.updateSuggestionImg(suggestionImgDaoList);
    }

    /**
     * 测试删除意见信息
     */
    @Test
    public void delSuggestionImg(){
        List<SuggestionImgDao> suggestionImgDaoList = new ArrayList<SuggestionImgDao>();
        SuggestionImgDao suggestionImgDao = null;

        suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setImgId("680ef49d-725d-46b1-b11a-7587ab3fb9b4");
        suggestionImgDaoList.add(suggestionImgDao);

        suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setImgId("31562843-aff4-48ac-81ec-e404258ed435");
        suggestionImgDaoList.add(suggestionImgDao);

        suggestionRepository.delSuggestionImg(suggestionImgDaoList);
    }





    /****************************************SuggestionDao**********************************************/

    /**
     * 测试查询所有意见信息
     */
    @Test
    public void findAllSuggestion(){
        List<SuggestionDao> suggestionDaoList =suggestionRepository.findAllSuggestion();
        for (int i = 0; i < suggestionDaoList.size(); i++) {
            System.out.println(suggestionDaoList.get(i).getSuggestionContent());
        }
    }

    /**
     *测试添加意见信息
     */
    @Test
    public void addSuggestion(){
        List<SuggestionDao> suggestionDaoList = new ArrayList<SuggestionDao>();
        SuggestionDao suggestionDao = null;

        suggestionDao = new SuggestionDao();
        suggestionDao.setId(UUID.randomUUID().toString());
        suggestionDao.setSuggestionId(UUID.randomUUID().toString());
        suggestionDao.setUserId(UUID.randomUUID().toString());
        suggestionDao.setSuggestionContent("这是意见一");
        suggestionDao.setSuggestionContact("张三");
        suggestionDao.setSuggestionTel("1234567890");
        suggestionDao.setSubmitDate(new Date());
        suggestionDao.setImg_1("");
        suggestionDao.setImg_2("");
        suggestionDao.setImg_3("");
        suggestionDaoList.add(suggestionDao);

        suggestionDao = new SuggestionDao();
        suggestionDao.setId(UUID.randomUUID().toString());
        suggestionDao.setSuggestionId(UUID.randomUUID().toString());
        suggestionDao.setUserId(UUID.randomUUID().toString());
        suggestionDao.setSuggestionContent("这是意见二");
        suggestionDao.setSuggestionContact("李四");
        suggestionDao.setSuggestionTel("9876543210");
        suggestionDao.setSubmitDate(new Date());
        suggestionDao.setImg_1("");
        suggestionDao.setImg_2("");
        suggestionDao.setImg_3("");
        suggestionDaoList.add(suggestionDao);

        suggestionRepository.addSuggestion(suggestionDaoList);
    }

    /**
     * 测试修改意见信息
     */
    @Test
    public void updateSuggestion(){
        List<SuggestionDao> suggestionDaoList = new ArrayList<SuggestionDao>();
        SuggestionDao suggestionDao = null;
        suggestionDao = new SuggestionDao();
        suggestionDao.setSuggestionContent("这是意见1111");
        suggestionDao.setSuggestionContact("张三3333");
        suggestionDao.setSuggestionTel("33333333");
        suggestionDao.setSuggestionId("7295b7c9-8caf-40aa-9317-974710a421dd");
        suggestionDaoList.add(suggestionDao);

        suggestionDao = new SuggestionDao();
        suggestionDao.setSuggestionContent("这是意见2222");
        suggestionDao.setSuggestionContact("李四4444");
        suggestionDao.setSuggestionTel("44444444");
        suggestionDao.setSuggestionId("f469a4e9-d389-4d17-9067-1f2ddc06ade8");
        suggestionDaoList.add(suggestionDao);

        suggestionRepository.updateSuggestion(suggestionDaoList);
    }

    /**
     * 测试删除意见信息
     */
    @Test
    public void delSuggestion(){
        List<SuggestionDao> suggestionDaoList = new ArrayList<SuggestionDao>();
        SuggestionDao suggestionDao = null;
        suggestionDao = new SuggestionDao();
        suggestionDao.setSuggestionId("7295b7c9-8caf-40aa-9317-974710a421dd");
        suggestionDaoList.add(suggestionDao);

        suggestionDao = new SuggestionDao();
        suggestionDao.setSuggestionId("f469a4e9-d389-4d17-9067-1f2ddc06ade8");
        suggestionDaoList.add(suggestionDao);

        suggestionRepository.delSuggestion(suggestionDaoList);
    }


}
