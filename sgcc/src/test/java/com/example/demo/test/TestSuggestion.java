package com.example.demo.test;

import com.example.constant.WechatURLConstants;
import com.example.demo.DemoApplicationTests;
import com.sgcc.DemoApplication;
import com.sgcc.FastDFSClient.FastDFSClient;
import com.sgcc.dao.AccessTokenDao;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionImgDao;
import com.sgcc.dto.SuggestionDeleteDTO;
import com.sgcc.dto.SuggestionSubmitDTO;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import com.sgcc.entity.event.AccessTokenEntity;
import com.sgcc.repository.SuggestionRepository;
import com.sgcc.repository.SuggestionImgRepository;
import com.sgcc.service.SuggestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestSuggestion{

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private SuggestionImgRepository suggestionImgRepository;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private FastDFSClient fastDFSClient;


    @Test
    public void uploadFile() throws IOException {
//        File file = new File("/home/xiemin/图片/电网logo.jpeg");
//
//        String path = fastDFSClient.uploadFile(file,"jpg");
//        System.out.println(path);
    }

    @Test
    public void submitTest()
    {
        SuggestionSubmitDTO dto = new SuggestionSubmitDTO();
        dto.setSuggestionContact("Riham");
        dto.setSuggestionTel("15810274507");
        dto.setSuggestionContent("JustTest");
        dto.setUserId("112233");
        suggestionService.submit( dto, dto.getUserId() );
    }

    /****************************************SuggestionDao**********************************************/
    /**
     * 测试查询所有意见图片信息
     */
    @Test
    public void findAllSuggestionImg(){
        List<SuggestionImgDao> suggestionDaoList =suggestionImgRepository.findAllSuggestionImg();
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

        suggestionImgRepository.addSuggestionImg(suggestionImgDaoList);
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

        suggestionImgRepository.updateSuggestionImg(suggestionImgDaoList);
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

        suggestionImgRepository.delSuggestionImg(suggestionImgDaoList);
    }





    /****************************************SuggestionDao**********************************************/

    /**
     * 测试查询所有意见信息
     */
    @Test
    public void findAllSuggestion(){
        List<SuggestionDao> suggestionDaoList =suggestionRepository.findAll();
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

        suggestionRepository.saveAll(suggestionDaoList);
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

        suggestionRepository.updateAll(suggestionDaoList);
    }

    /**
     * 测试删除意见信息
     */
    @Test
    public void delSuggestion(){
        SuggestionDeleteDTO dto = new SuggestionDeleteDTO();
        List<String> suggestionDaoList = new ArrayList<>();
        suggestionDaoList.add("1408aeda-c943-43d1-8c27-8fd0fe320009");
        suggestionDaoList.add("6d6c522b-d19e-4fc5-ba72-1388488d0145");
        suggestionDaoList.add("49697a8c-af62-40ab-8634-2090c90bd8af");
        suggestionDaoList.add("d91cab7c-b5e3-4803-94a3-535425e88473");
        dto.setSuggestionIds(suggestionDaoList);
        suggestionService.delete(dto);
    }


}
