package com.example.demo.test;

import com.sgcc.DemoApplication;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.repository.QAnswerRepository;
import com.sgcc.repository.QAnswersRepository;
import com.sgcc.repository.QCategoryRepository;
import com.sgcc.repository.QCategorysRepository;
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
public class TestQuestion {

    @Autowired
    private QCategorysRepository QCategorysRepository;
    @Autowired
    private QAnswersRepository qAnswersRepository;
    @Autowired
    private QCategoryRepository qCategoryRepository;
    @Autowired
    private QAnswerRepository qAnswerRepository;

    /***********************************问题回答增删改查**************************************/
    /**
     * 查询
     */
    @Test
    public void getQAnswerList(){
        List<QuestionAnswerDao> categoryDaoList = qAnswersRepository.findAllQAnswer();
        for (int i = 0; i < categoryDaoList.size(); i++) {
            System.out.println(categoryDaoList.get(i).getQuestionDesc());
        }
    }

    /**
     * 增加
     */
    @Test
    public void addQAnswer(){
        QuestionAnswerDao dao = new QuestionAnswerDao();
        dao.setId(UUID.randomUUID().toString());
        dao.setCategoryId("2c37b94b-268e-49a7-8e3d-1e9b");
        dao.setQuestionDesc("我是描述1001");
        dao.setAnswer("我是问题的回答1001");

        qAnswerRepository.addQAnswer(dao);

    }

    /**
     * 删除
     */
    @Test
    public void delQAnswer(){
//        List<QuestionAnswerDao> answerDaoList = new ArrayList<QuestionAnswerDao>();
//        QuestionAnswerDao dao =null;
//        dao = new QuestionAnswerDao();
//        dao.setId("80b057f2-9679-46ce-8a8e-0d0e");
//
//        answerDaoList.add(dao);
//        dao = new QuestionAnswerDao();
//        dao.setId("9a92431d-14d8-4079-abd2-0394");
//        answerDaoList.add(dao);
//
//        qAnswerRepository.delQAnswer(answerDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updateQAnswer(){
        QuestionAnswerDao dao =new QuestionAnswerDao();
        dao.setId("f2f1ef58-6668-4e6d-b0c3-ec21f38e69f0");
        dao.setQuestionDesc("我是问题的描述9999");
        dao.setAnswer("我是问题的回答9999");
        qAnswerRepository.updateQAnswer(dao);
    }




    /***********************************问题类型增删改查**************************************/

    /**
     * 查询
     */
    @Test
    public void getQCategoryList(){
        List<QuestionCategoryDao> categoryDaoList = QCategorysRepository.findAllQCategory();
        for (int i = 0; i < categoryDaoList.size(); i++) {
            System.out.println(categoryDaoList.get(i).getCategoryDesc()+"==>"+i);
        }
    }

    /**
     * 增加
     */
    @Test
    public void addQCategory(){
        QuestionCategoryDao dao = new QuestionCategoryDao();
        dao.setId("10005");
        dao.setCategoryId(UUID.randomUUID().toString());
        dao.setCategoryDesc("问题类型5555");
        dao.setCategoryOrder(10005);

        qCategoryRepository.addQCategory(dao);

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

//        QCategoryRepository.delQCategory(categoryDaoList);
    }

    /**
     * 修改
     */
    @Test
    public void updateQCategory(){
        QuestionCategoryDao dao = new QuestionCategoryDao();
        dao.setCategoryId("c9c03934-9bf5-4e6d-b882-c1920c72ae9b");
        dao.setCategoryDesc("描述5555");
        dao.setCategoryOrder(5555);

        qCategoryRepository.updateQCategory(dao);
    }



}
