package com.sgcc.entity;

import com.esms.MOMsg;
import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.*;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MessageNotificationEntity {
    /**
     * 短信下发样例
     */
    public void doSendSms(PostMsg pm, Account ac , List<String> phoneNums,String content) throws Exception {
        MTPack pack = new MTPack();
        pack.setBatchID(UUID.randomUUID());
        pack.setBatchName("短信测试批次");
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setDistinctFlag(false);
        ArrayList<MessageData> msgs = new ArrayList<MessageData>();
        /** 群发, 多号码同一内容 */
		pack.setSendType(MTPack.SendType.MASS);
        phoneNums.forEach(phone->{
            msgs.add(new MessageData(phone,content));
        });
		pack.setMsgs(msgs);

        GsmsResponse resp = pm.post(ac, pack);
        System.out.println(resp);
    }

//    /**
//     * 彩信下发范例
//     */
//    public void doSendMms(PostMsg pm, Account ac) throws Exception {
//        MTPack pack = new MTPack();
//        pack.setBatchID(UUID.randomUUID());
//        pack.setBatchName("彩信测试批次");
//        pack.setMsgType(MTPack.MsgType.MMS);
//        //pack.setBizType(1);
//        pack.setDistinctFlag(false);
//        ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//        String path = PostMsgTest.class.getClassLoader().getResource("mms_test").getPath();
//        path = URLDecoder.decode(path, "utf-8");
//
//        // 设置公共彩信资源
//        pack.setMedias(MediaUtil.getMediasFromFolder(path));
//
////		/** 单发，一号码一内容 */
////		msgs.add(new MessageData("13430258111", null));
////		pack.setMsgs(msgs);
//
//        /** 群发，多号码一内容 */
//        pack.setSendType(MTPack.SendType.MASS);
//        msgs.add(new MessageData("13430258111", null));
//        msgs.add(new MessageData("13430258222", null));
//        msgs.add(new MessageData("13430258333", null));
//        pack.setMsgs(msgs);
//
////		/** 组发，多号码多内容 */
////		pack.setSendType(MTPack.SendType.GROUP);
////		//设置私有彩信资源
////		MessageData msg1 = new MessageData("13430258111", null);
////		msg1.setMedias(MediaUtil.getMediasFromFolder("F:/mms_test/msg1"));
////		msgs.add(msg1);
////		MessageData msg2 = new MessageData("13430258222", null);
////		msg2.setMedias(MediaUtil.getMediasFromFolder("F:/mms_test/msg2"));
////		msgs.add(msg2);
////		MessageData msg3 = new MessageData("13430258333", null);
////		msg3.setMedias(MediaUtil.getMediasFromFolder("F:/mms_test/msg3"));
////		msgs.add(msg3);
////		pack.setMsgs(msgs);
//
//        GsmsResponse resp = pm.post(ac, pack);
//        System.out.println(resp);
//    }

    /**
     * 获取账号信息
     */
    public void doGetAccountInfo(PostMsg pm, Account ac) throws Exception {
        System.out.println(pm.getAccountInfo(ac));   // 获取账号详细信息

        BusinessType[] bizTypes = pm.getBizTypes(ac); // 获取账号绑定业务类型
        if (bizTypes != null) {
            for (BusinessType bizType : bizTypes) {
                System.out.println(bizType);
            }
        }
    }

    /**
     * 获取上行信息
     */
    public void doGetMos(PostMsg pm, Account ac) throws Exception {
        MOMsg[] mos = pm.getMOMsgs(ac, 100);
        if (mos != null) {
            String confirmId = null;
            for (MOMsg mo : mos) {
                System.out.println(mo);
                confirmId = mo.getReserve();
            }

            // 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
            if (!pm.isAutoConfirm()) {
                pm.confirmMoRequest(ac, confirmId);
            }
        }
    }

    /**
     * 查询提交报告
     */
    public void doFindResps(PostMsg pm, Account ac) throws Exception {
        UUID batchID = UUID.fromString("3e1f13f4-1677-41f1-b67d-702f2c01eafb"); // 如果需要按批次ID来查询
        String phone = "135XXXXXXXX";
        MTResponse[] foundMtResps = pm.findResps(ac, 1, batchID, phone, 1);
        if (foundMtResps != null) {
            for (MTResponse resp : foundMtResps) {
                System.out.println(resp);
            }
        }
    }

    /**
     * 获取提交报告
     */
    public void doGetResps(PostMsg pm, Account ac) throws Exception{
        MTResponse[] mtResps = pm.getResps(ac, 100);
        if (mtResps != null) {
            String confirmId = null;
            for (MTResponse resp : mtResps) {
                System.out.println(resp);
                confirmId = resp.getReserve();
            }

            // 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
            if (!pm.isAutoConfirm()) {
                pm.confirmRespRequest(ac, confirmId);
            }
        }
    }

    /**
     * 查询状态报告
     */
    public void doFindReports(PostMsg pm, Account ac) throws Exception {
        UUID batchID = UUID.fromString("3e1f13f4-1677-41f1-b67d-702f2c01eafb"); //如果需要按批次ID来查询
        String phone = "135XXXXXXXX";
        MTReport[] foundMtReports = pm.findReports(ac, 1, batchID, phone, 1);
        if (foundMtReports != null) {
            for (MTReport report : foundMtReports) {
                System.out.println(report);
            }
        }
    }

    /**
     * 获取状态报告
     */
    public void doGetReports(PostMsg pm, Account ac) throws Exception{
        MTReport[] mtReports = pm.getReports(ac, 100);
        if (mtReports != null) {
            String confirmId = null;
            for (MTReport report : mtReports) {
                System.out.println(report);
                confirmId = report.getReserve();
            }

            // 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
            if (!pm.isAutoConfirm()) {
                pm.confirmReportRequest(ac, confirmId);
            }
        }
    }

    /**
     * 修改密码
     */
    public void doModifyPwd(PostMsg pm, Account ac,String newPwd) throws Exception{
        System.out.println(pm.modifyPassword(ac, newPwd));
    }
}
