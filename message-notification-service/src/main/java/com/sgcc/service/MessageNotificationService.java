package com.sgcc.service;


import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.sgcc.entity.MessageNotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageNotificationService {
    @Autowired
    private MessageNotificationEntity messageNotificationEntity;

    private final PostMsg pm = new PostMsg(false, 7000, 1);

    private final Account account = new Account("test", "test");

    /**
     * 短信下发样例
     */
    public void doSendSms(List<String> phoneNums, String content) throws Exception {
        messageNotificationEntity.doSendSms(pm, account, phoneNums, content);
    }

    /**
     * 获取账号信息
     */
    public void doGetAccountInfo() throws Exception {
        messageNotificationEntity.doGetAccountInfo(pm, account);
    }

    /**
     * 获取上行信息
     */
    public void doGetMos() throws Exception {
        messageNotificationEntity.doGetMos(pm, account);
    }

    /**
     * 修改密码
     */
    public void doModifyPwd(String newPwd) throws Exception {
        messageNotificationEntity.doModifyPwd(pm, account, newPwd);
    }
}
