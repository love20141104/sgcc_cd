//package com.sgcc.config;
//
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.UUID;
//
//import com.esms.MOMsg;
//import com.esms.MessageData;
//import com.esms.PostMsg;
//import org.junit.Test;
//
//import com.esms.common.entity.Account;
//import com.esms.common.entity.BusinessType;
//import com.esms.common.entity.GsmsResponse;
//import com.esms.common.entity.MTPack;
//import com.esms.common.entity.MTReport;
//import com.esms.common.entity.MTResponse;
//import com.esms.common.util.Interceptor;
//import com.esms.common.util.MediaUtil;
//
//public class PostMsgTest {
//
//	@Test
//	public void test() throws Exception {
//		/*
//		 * !!!重要!!! PostMsg对象请使用单例模式(而非每次下发new一次)
//		 * 下面构造函数字段分别为:
//		 * 1. 连接模式: false代表长连接(短连接为true)
//		 * 2. 超时时间: socket超时时间(毫秒)
//		 * 3. 下行短信每个账号最大并发socket连接数: 一般配置为2, 如果发送量比较大可调整为3-4
//		 */
//		final PostMsg pm = new PostMsg(false, 7000, 2);
//		pm.setInterceptor(new Interceptor() {
//			public void beforeMtSend(long waitConnTime, long loginTime, MTPack pack) {
//				// 加上自定义的日志输出
//				System.out.println("MT before send [" + pack.getBatchID()
//						+ "] wait:" + waitConnTime
//						+ ", login:" + loginTime
//						+ ", now:" + System.currentTimeMillis());
//			}
//		});
//		pm.getCmHost().setHost("127.0.0.1", 8090); // 设置网关的IP和port，用于发送信息
//		pm.getWsHost().setHost("127.0.0.1", 8088); // 设置网关的IP和port，用于获取账号信息、上行、状态报告等等
//
//		doSendSms(pm, new Account("admin@test1", "123456")); // 短信下行
////		doSendMms(pm, new Account("admin@test2", "123456")); // 彩信下行
//
////		Account ac = new Account("admin@test1", "123456"); // 单账号
////		doSendSms(pm, ac); // 短信下行
//
////		doGetAccountInfo(pm, ac); // 获取账号信息
////		doModifyPwd(pm, ac); // 修改密码
//
////		doFindResps(pm, ac); // 查询提交报告
////		doFindReports(pm, ac); // 查询状态报告
//
////		doGetMos(pm, ac); // 获取上行信息
////		doGetResps(pm, ac); // 获取提交报告
////		doGetReports(pm, ac); // 获取状态报告
//	}
//
//	/**
//	 * 短信下发样例
//	 */
//	public static void doSendSms(PostMsg pm, Account ac) throws Exception {
//		MTPack pack = new MTPack();
//		pack.setBatchID(UUID.randomUUID());
//		pack.setBatchName("短信测试批次");
//		pack.setMsgType(MTPack.MsgType.SMS);
//		//pack.setBizType(2); // 设置业务类型
//		pack.setDistinctFlag(false);
//		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//		/** 单发, 一号码一内容 */
//		pack.setSendType(MTPack.SendType.GROUP);
//		msgs.add(new MessageData("13700000000", "短信单发测试"));
//		pack.setMsgs(msgs);
//
//		/** 群发, 多号码同一内容 */
//		/*pack.setSendType(MTPack.SendType.MASS);
//		String content = "短信群发测试";
//		msgs.add(new MessageData("13700000000", content));
//		msgs.add(new MessageData("13700000001", content));
//		msgs.add(new MessageData("13700000002", content));
//		pack.setMsgs(msgs);*/
//
//		/** 组发, 多号码多内容 */
//		/*pack.setSendType(MTPack.SendType.GROUP);
//		msgs.add(new MessageData("13700000000", "短信组发测试0"));
//		msgs.add(new MessageData("13700000001", "短信组发测试1"));
//		msgs.add(new MessageData("13700000002", "短信组发测试2"));
//		pack.setMsgs(msgs);*/
//
//		/** 使用模板方式发送, 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值) */
//		/*pack.setTemplateNo("8973febf65e144d492d070dc8c55b46c");
//		msgs.add(new MessageData("13700000000", "[]")); // 1. 静态模板(不需要传变量, 空数组即可)
//		msgs.add(new MessageData("13700000000", "[\"测试用户\",\"123456\"]")); // 2. 动态模板设置依次替换的参数
//		*/
//
//		GsmsResponse resp = pm.post(ac, pack);
//		System.out.println(resp);
//	}
//
//	/**
//	 * 彩信下发范例
//	 */
//	public static void doSendMms(PostMsg pm, Account ac) throws Exception {
//		MTPack pack = new MTPack();
//		pack.setBatchID(UUID.randomUUID());
//		pack.setBatchName("彩信测试批次");
//		pack.setMsgType(MTPack.MsgType.MMS);
//		//pack.setBizType(1);
//		pack.setDistinctFlag(false);
//		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//		String path = PostMsgTest.class.getClassLoader().getResource("mms_test").getPath();
//		path = URLDecoder.decode(path, "utf-8");
//
//		// 设置公共彩信资源
//		pack.setMedias(MediaUtil.getMediasFromFolder(path));
//
////		/** 单发，一号码一内容 */
////		msgs.add(new MessageData("13430258111", null));
////		pack.setMsgs(msgs);
//
//		/** 群发，多号码一内容 */
//		pack.setSendType(MTPack.SendType.MASS);
//		msgs.add(new MessageData("13430258111", null));
//		msgs.add(new MessageData("13430258222", null));
//		msgs.add(new MessageData("13430258333", null));
//		pack.setMsgs(msgs);
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
//		GsmsResponse resp = pm.post(ac, pack);
//		System.out.println(resp);
//	}
//
//	/**
//	 * 获取账号信息
//	 */
//	public static void doGetAccountInfo(PostMsg pm, Account ac) throws Exception {
//		System.out.println(pm.getAccountInfo(ac));   // 获取账号详细信息
//
//		BusinessType[] bizTypes = pm.getBizTypes(ac); // 获取账号绑定业务类型
//		if (bizTypes != null) {
//			for (BusinessType bizType : bizTypes) {
//				System.out.println(bizType);
//			}
//		}
//	}
//
//	/**
//	 * 获取上行信息
//	 */
//	public static void doGetMos(PostMsg pm, Account ac) throws Exception {
//		MOMsg[] mos = pm.getMOMsgs(ac, 100);
//		if (mos != null) {
//			String confirmId = null;
//			for (MOMsg mo : mos) {
//				System.out.println(mo);
//				confirmId = mo.getReserve();
//			}
//
//			// 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
//			if (!pm.isAutoConfirm()) {
//				pm.confirmMoRequest(ac, confirmId);
//			}
//		}
//	}
//
//	/**
//	 * 查询提交报告
//	 */
//	public static void doFindResps(PostMsg pm, Account ac) throws Exception {
//		UUID batchID = UUID.fromString("3e1f13f4-1677-41f1-b67d-702f2c01eafb"); // 如果需要按批次ID来查询
//		String phone = "135XXXXXXXX";
//		MTResponse[] foundMtResps = pm.findResps(ac, 1, batchID, phone, 1);
//		if (foundMtResps != null) {
//			for (MTResponse resp : foundMtResps) {
//				System.out.println(resp);
//			}
//		}
//	}
//
//	/**
//	 * 获取提交报告
//	 */
//	public static void doGetResps(PostMsg pm, Account ac) throws Exception{
//		MTResponse[] mtResps = pm.getResps(ac, 100);
//		if (mtResps != null) {
//			String confirmId = null;
//			for (MTResponse resp : mtResps) {
//				System.out.println(resp);
//				confirmId = resp.getReserve();
//			}
//
//			// 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
//			if (!pm.isAutoConfirm()) {
//				pm.confirmRespRequest(ac, confirmId);
//			}
//		}
//	}
//
//	/**
//	 * 查询状态报告
//	 */
//	public static void doFindReports(PostMsg pm, Account ac) throws Exception {
//		UUID batchID = UUID.fromString("3e1f13f4-1677-41f1-b67d-702f2c01eafb"); //如果需要按批次ID来查询
//		String phone = "135XXXXXXXX";
//		MTReport[] foundMtReports = pm.findReports(ac, 1, batchID, phone, 1);
//		if (foundMtReports != null) {
//			for (MTReport report : foundMtReports) {
//				System.out.println(report);
//			}
//		}
//	}
//
//	/**
//	 * 获取状态报告
//	 */
//	public static void doGetReports(PostMsg pm, Account ac) throws Exception{
//		MTReport[] mtReports = pm.getReports(ac, 100);
//		if (mtReports != null) {
//			String confirmId = null;
//			for (MTReport report : mtReports) {
//				System.out.println(report);
//				confirmId = report.getReserve();
//			}
//
//			// 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
//			if (!pm.isAutoConfirm()) {
//				pm.confirmReportRequest(ac, confirmId);
//			}
//		}
//	}
//
//	/**
//	 * 修改密码
//	 */
//	public static void doModifyPwd(PostMsg pm, Account ac) throws Exception{
//		System.out.println(pm.modifyPassword(ac, "123456"));
//	}
//
//}
//
