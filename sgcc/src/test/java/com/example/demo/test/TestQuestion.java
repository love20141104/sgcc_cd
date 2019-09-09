package com.example.demo.test;

import com.example.demo.DemoApplicationTests;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.repository.QAnswerRepository;
import com.sgcc.repository.QCategoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestQuestion extends DemoApplicationTests{

    @Autowired
    private QCategoryRepository QCategoryRepository;
    @Autowired
    private QAnswerRepository qAnswerRepository;

    /***********************************问题回答增删改查**************************************/
    /**
     * 查询
     */
    @Test
    public void getQAnswerList(){
        List<QuestionAnswerDao> categoryDaoList = qAnswerRepository.findAllQAnswer();
        for (int i = 0; i < categoryDaoList.size(); i++) {
            System.out.println(categoryDaoList.get(i).getQuestionDesc());
        }
    }

    /**
     * 增加
     */
    @Test
    public void addQAnswer(){
        List<QuestionAnswerDao> answerDaoList = new ArrayList<QuestionAnswerDao>();
        QuestionAnswerDao dao = null;
        dao= new QuestionAnswerDao();
        dao.setId(UUID.randomUUID().toString().substring(0,28));
        dao.setCategoryId("2c37b94b-268e-49a7-8e3d-1e9b");
        dao.setQuestionDesc("我是问题的描述一");
        dao.setAnswer("我是问题的回答一");
        answerDaoList.add(dao);

        dao = new QuestionAnswerDao();
        dao.setId(UUID.randomUUID().toString().substring(0,28));
        dao.setCategoryId("a949024a-774f-4621-ade0-0540");
        dao.setQuestionDesc("我是问题的描述一");
        dao.setAnswer("我是问题的回答一");
        answerDaoList.add(dao);

        qAnswerRepository.addQAnswer(answerDaoList);

    }

    /**
     * 删除
     */
    @Test
    public void delQAnswer(){
        List<QuestionAnswerDao> answerDaoList = new ArrayList<QuestionAnswerDao>();
        QuestionAnswerDao dao =null;
        dao = new QuestionAnswerDao();
        dao.setId("80b057f2-9679-46ce-8a8e-0d0e");

        answerDaoList.add(dao);
        dao = new QuestionAnswerDao();
        dao.setId("9a92431d-14d8-4079-abd2-0394");
        answerDaoList.add(dao);

        qAnswerRepository.delQAnswer(answerDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updateQAnswer(){
        List<QuestionAnswerDao> answerDaoList = new ArrayList<QuestionAnswerDao>();
        QuestionAnswerDao dao =null;
        dao = new QuestionAnswerDao();
        dao.setId("1e974093-9c22-48b8-aa8e-ebc1");
        dao.setQuestionDesc("我是问题的描述1111");
        dao.setAnswer("我是问题的回答1111");

        answerDaoList.add(dao);
        dao = new QuestionAnswerDao();
        dao.setId("80b057f2-9679-46ce-8a8e-0d0e");
        dao.setQuestionDesc("我是问题的描述2222");
        dao.setAnswer("我是问题的回答2222");
        answerDaoList.add(dao);

        qAnswerRepository.updateQAnswer(answerDaoList);
    }




    /***********************************问题类型增删改查**************************************/

    /**
     * 查询
     */
    @Test
    public void getQCategoryList(){
        List<QuestionCategoryDao> categoryDaoList = QCategoryRepository.findAllQCategory();
        for (int i = 0; i < categoryDaoList.size(); i++) {
            System.out.println(categoryDaoList.get(i).getCategoryDesc()+"==>"+i);
        }
    }

    /**
     * 增加
     */
    @Test
    public void addQCategory(){
        List<QuestionCategoryDao> categoryDaoList = new ArrayList<QuestionCategoryDao>();
        QuestionCategoryDao dao = null;
        dao= new QuestionCategoryDao();
        dao.setId("10003");
        dao.setCategoryId(UUID.randomUUID().toString().substring(0,28));
        dao.setCategoryDesc("问题类型3333");
        dao.setCategoryOrder(10003);
        categoryDaoList.add(dao);

        dao = new QuestionCategoryDao();
        dao.setId("10004");
        dao.setCategoryId(UUID.randomUUID().toString().substring(0,28));
        dao.setCategoryDesc("问题类型4444");
        dao.setCategoryOrder(10004);
        categoryDaoList.add(dao);

        QCategoryRepository.addQCategory(categoryDaoList);

    }

    /**
     * 删除
     */
    @Test
    public void delQCategory(){
        List<QuestionCategoryDao> categoryDaoList = new ArrayList<QuestionCategoryDao>();
        QuestionCategoryDao dao =null;
        dao = new QuestionCategoryDao();
        dao.setCategoryId("3c513a0c-eb16-4817-9980-9400");

        categoryDaoList.add(dao);
        dao = new QuestionCategoryDao();
        dao.setCategoryId("cdf437e4-919b-4308-a38a-90ea");
        categoryDaoList.add(dao);

        QCategoryRepository.delQCategory(categoryDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updateQCategory(){
        List<QuestionCategoryDao> categoryDaoList = new ArrayList<QuestionCategoryDao>();
        QuestionCategoryDao dao =null;
        dao = new QuestionCategoryDao();
        dao.setCategoryId("2c37b94b-268e-49a7-8e3d-1e9b");
        dao.setCategoryDesc("问题类型1111");
        dao.setCategoryOrder(1111);

        categoryDaoList.add(dao);
        dao = new QuestionCategoryDao();
        dao.setCategoryId("a949024a-774f-4621-ade0-0540");
        dao.setCategoryDesc("问题类型2222");
        dao.setCategoryOrder(2222);
        categoryDaoList.add(dao);

        QCategoryRepository.updateQCategory(categoryDaoList);
    }



}
