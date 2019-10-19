package com.example.demo.test;

import com.sgcc.DemoApplication;
import com.sgcc.FastDFSClient.FastDFSClient;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionImgDao;
import com.sgcc.dto.SuggestionDeleteDTO;
import com.sgcc.dto.SuggestionSubmitDTO;
import com.sgcc.repository.SuggestionRepository;
import com.sgcc.repository.SuggestionsRepository;
import com.sgcc.repository.SuggestionImgRepository;
import com.sgcc.service.SuggestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestSuggestion{

    @Autowired
    private SuggestionsRepository suggestionsRepository;

    @Autowired
    private SuggestionImgRepository suggestionImgRepository;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private SuggestionRepository suggestionRepository;


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

    /****************************************SuggestionImgDao**********************************************/
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
        SuggestionImgDao suggestionImgDao  = new SuggestionImgDao();
        suggestionImgDao.setId(UUID.randomUUID().toString());
        suggestionImgDao.setImgId(UUID.randomUUID().toString());
        suggestionImgDao.setUserId(UUID.randomUUID().toString());
        suggestionImgDao.setImgUrl("/opt/test.jpg");
        suggestionImgDao.setSubmitDate(new Date());

        suggestionImgRepository.addSuggestionImg(suggestionImgDao);
    }

    /**
     * 测试修改意见图片信息
     */
    @Test
    public void updateSuggestionImg(){
        SuggestionImgDao suggestionImgDao = new SuggestionImgDao();
        suggestionImgDao.setImgUrl("/opt/test3.jpg");
        suggestionImgDao.setImgId("c2da0092-84a7-4ed6-bb8f-536cd994b618");

        suggestionImgRepository.updateSuggestionImg(suggestionImgDao);
    }

    /**
     * 测试删除意见信息
     */
    @Test
    public void delSuggestionImg(){
        List<String> list = new ArrayList<>();
        list.add("6bdb58fb-f48f-448c-be4b-ceb17a74106c");

        suggestionImgRepository.delSuggestionImg(list);
    }





    /****************************************SuggestionDao**********************************************/

    /**
     * 测试查询所有意见信息
     */
    @Test
    public void findAllSuggestion(){
        List<SuggestionDao> suggestionDaoList = suggestionsRepository.findAll();
        for (int i = 0; i < suggestionDaoList.size(); i++) {
            System.out.println(suggestionDaoList.get(i).getSuggestionContent());
        }
    }

    /**
     *测试添加意见信息
     */
    @Test
    public void addSuggestion(){
        SuggestionDao suggestionDao = new SuggestionDao();
        suggestionDao.setId(UUID.randomUUID().toString());
        suggestionDao.setSuggestionId(UUID.randomUUID().toString());
        suggestionDao.setUserId(UUID.randomUUID().toString());
        suggestionDao.setSuggestionContent("这是意见789");
        suggestionDao.setSuggestionContact("rose");
        suggestionDao.setSuggestionTel("69696969");
        suggestionDao.setSubmitDate(new Date());
        suggestionDao.setImg_1("");
        suggestionDao.setImg_2("");
        suggestionDao.setImg_3("");


        suggestionRepository.saveAll(suggestionDao);
    }

    /**
     * 测试修改意见信息
     */
    @Test
    public void updateSuggestion(){
        SuggestionDao suggestionDao = new SuggestionDao();
        suggestionDao.setSuggestionContent("这是意见1111");
        suggestionDao.setSuggestionContact("张三3333");
        suggestionDao.setSuggestionTel("33333333");
        suggestionDao.setSuggestionId("7ccf03a9-158b-4743-a16b-525ed82c1776");

        suggestionRepository.updateAll(suggestionDao);
    }

    /**
     * 测试删除意见信息
     */
    @Test
    public void delSuggestion(){
        List<String> list = new ArrayList<>();
        list.add("7ccf03a9-158b-4743-a16b-525ed82c1776");
        list.add("c0a802cc-c9d7-4a52-a4a7-bfafb8308db6");
        suggestionRepository.deleteAll(list);
    }


}
