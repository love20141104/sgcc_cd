package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.example.result.Result;
import com.sgcc.DemoApplication;
import com.sgcc.service.HouseholdService;
import com.sgcc.sgccenum.SubscribeCateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestHousehold.class, DemoApplication.class})
public class TestHousehold {
    private Logger logger = Logger.getLogger(TestHousehold.class.toString());
    @Autowired
    private HouseholdService householdService;


    private static String openId = "ownrvsoFgPHBFV0qUugsMTGc7tWo";
    private static List<String> openIds = new ArrayList<String>() {{
        add("ownrvsoFgPHBFV0qUugsMTGc7tWo1");
        add("ownrvsoFgPHBFV0qUugsMTGc7tWo2");
        add("ownrvsoFgPHBFV0qUugsMTGc7tWo3");
        add("ownrvsoFgPHBFV0qUugsMTGc7tWo4");
        add("ownrvsoFgPHBFV0qUugsMTGc7tWo5");
    }};

    private static List<String> houseNums = new ArrayList<String>() {
        private static final long serialVersionUID = -8009670422095852284L;

        {
            add("202154871");
            add("202154871");
            add("202154872");
            add("202154873");
            add("202154874");
            add("202154875");
            add("202154876");
        }
    };

    private static List<String> pwd_old = new ArrayList<String>() {
        private static final long serialVersionUID = -8009670422095852284L;

        {
            add("202154871pwd_old");
            add("202154871pwd_old");
            add("202154872pwd_old");
            add("202154873pwd_old");
            add("202154874pwd_old");
            add("202154875pwd_old");
            add("202154876pwd_old");

        }
    };


    private static List<String> pwd_new = new ArrayList<String>() {
        private static final long serialVersionUID = -8009670422095852284L;

        {
            add("202154871pwd_new");
            add("202154871pwd_new");
            add("202154872pwd_new");
            add("202154873pwd_new");
            add("202154874pwd_new");
            add("202154875pwd_new");
            add("202154876pwd_new");

        }
    };

    /**
     * 用户绑定户号
     */
    @Test
    public void testBind() throws InterruptedException {
        for (int i = 0; i < houseNums.size(); i++) {
            Result result = householdService.bindHousehold(openId, houseNums.get(i), pwd_old.get(i));
            logger.info(JSON.toJSONString(result));
            Thread.sleep(1000);
        }
        for (int i = 0; i < openIds.size(); i++) {
            Result result = householdService.bindHousehold(openIds.get(i), houseNums.get(0), pwd_old.get(0));
            logger.info(JSON.toJSONString(result));
            Thread.sleep(1000);
        }

    }


    /**
     * 用户获取绑定户号列表
     */
    @Test
    public void testGetBindList1() {
        Result result = householdService.getBindList(openId);
        logger.info(JSON.toJSONString(result));
    }


    /**
     * 设置默认户号
     */
    @Test
    public void testSetDefaultHouseholdNum() throws InterruptedException {
        for (int i = 0; i < houseNums.size(); i++) {
            Result result = householdService.setDefaultHouseholdNum(openId, houseNums.get(i));
            logger.info(JSON.toJSONString(result));
            Thread.sleep(1000);
        }
    }

    /**
     * 用户获取绑定户号列表
     */
    @Test
    public void testGetBindList2() {
        Result result = householdService.getBindList(openId);
        logger.info(JSON.toJSONString(result));
    }

    /**
     * 数据库中记录的密码失效时，提示用户输入新密码修改密码
     */
    @Test
    public void testChangePWD() throws InterruptedException {
        for (int i = 0; i < houseNums.size(); i++) {

            Result result = householdService.changePWD(openId, houseNums.get(i), pwd_new.get(i));
            logger.info(JSON.toJSONString(result));
            Thread.sleep(1000);
        }
    }


    /**
     * 查询用户消息订阅状态
     */
    @Test
    public void TestGetSubscribeInfo() {
        Result result = householdService.getSubscribeInfo(openId);
        logger.info(JSON.toJSONString(result));
    }

    /**
     * 用户修改消息订阅状态
     */
    @Test
    public void updateSubscribe() {
        Result result = householdService.updateSubscribe(
                openId
                , SubscribeCateEnum.sub_bill
                , false);
        logger.info(JSON.toJSONString(result));
    }

    /**
     * 用户解邦户号
     */
    @Test
    public void testRemoveBind() throws InterruptedException {
        for (int i = 0; i < houseNums.size(); i++) {
            Result result = householdService.removeBind(openId, houseNums.get(i));
            logger.info(JSON.toJSONString(result));
            Thread.sleep(1000);
        }

        for (int i = 0; i < openIds.size(); i++) {
            Result result = householdService.removeBind(openIds.get(i), houseNums.get(0));
            logger.info(JSON.toJSONString(result));
            Thread.sleep(1000);
        }
    }

    /**
     * 用户取消关注
     */
    @Test
    public void testCancelFocusWechat() {
        Result result = householdService.cancelFocusWechat(openId);
        logger.info(JSON.toJSONString(result));
    }

    /**
     * 用户关注公众号
     */
    @Test
    public void testFocusWechat() {
        Result result = householdService.focusWechat(openId);
        logger.info(JSON.toJSONString(result));
    }

    /**
     * 加解密
     */
    @Test
    public void testSecret(){
        String ss = householdService.encrypt("1234567890");
        logger.info("密文："+ss);
        String pwd = householdService.decrypt(ss);
        logger.info("明文："+pwd);

    }

}
