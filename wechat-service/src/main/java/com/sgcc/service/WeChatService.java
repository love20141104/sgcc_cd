package com.sgcc.service;

import com.example.Utils;
import com.example.constant.CommonConstants;
import com.example.constant.WechatURLConstants;
import com.example.errorcode.WechatURLErrorcode;
import com.example.result.Result;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.WeChatEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.wxpay.Sgcc_WXPay;
import com.sgcc.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WeChatService {

    @Autowired
    private WeChatEntity weChatEntity;

    static int retries = 0;
    /**
     * 获取微信服务号的AccessToken
     * @return
     */
    public Result getAccessToken(){
        return Result.success(weChatEntity.getAccessToken());
    }

    /**
     * 获取微信js-api-ticket
     */
    public JSAPITicketDTO getJsApiTicket(){
        return weChatEntity.getJsApiTicket();
    }

    /**
     * 获取Signature
     * @param url
     * @return
     */
    public Result getSignature(String url){
        System.out.println("url:"+url);
        return Result.success(weChatEntity.getSignature(url));
    }

    /**
     *
     * @param openId
     * @param totalFee
     * @return
     * @throws Exception
     */
    public Result getPrepay(String openId,String totalFee) throws Exception {
        Sgcc_WXPay sgcc_wxPay = new Sgcc_WXPay();
        Map<String,String > prepay = sgcc_wxPay.unifiedorder(openId,totalFee);

        prepay.get("prepay_id");
        Map<String,String > requ = new HashMap<>();
        requ.put("appId", WechatURLConstants.APPID);
        requ.put("timeStamp",Utils.create_timestamp());
        requ.put("nonceStr",Utils.create_nonce_str());
        requ.put("package","prepay_id="+ prepay.get("prepay_id"));
        requ.put("signType","MD5");
        //appId, timeStamp, nonceStr, package, signType
        //requ.put("paySign",Utils.createSign(requ,WechatURLConstants.APPKEY));
        requ.put("paySign",Utils.encryption(requ,WechatURLConstants.MD5,true,WechatURLConstants.APPKEY));
        return Result.success(requ);
    }

    /**
     *
     * @param noticeXml
     * @return
     */
    public Result PayNotice(String noticeXml) {
        try {
            Map<String,String> notice = WXPayUtil.xmlToMap(noticeXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Result sendTempMsg(String openId){
        try {
            Map<String, TemplateData> data = new LinkedHashMap<>();
            data.put("first",new TemplateData("停电通知","#173177"));
            data.put("event",new TemplateData("2019-11-12 8:00 ~ 2019-11-12 18:00\n","#173177"));
            data.put("dept",new TemplateData("华府大道沿线","#173177"));
            data.put("date",new TemplateData("线路检修","#173177"));
            data.put("remark",new TemplateData("如有不便敬请谅解！","#173177"));

//            data.put("first",new TemplateData("申请成功模板消息测试","#173177"));
//            data.put("event",new TemplateData("超级","#173177"));
//            data.put("dept",new TemplateData("神部","#173177"));
//            data.put("date",new TemplateData("2019年10月09日","#173177"));
//            data.put("remark",new TemplateData("申请成功！","#173177"));

            TemplateMessage templateMessage = new TemplateMessage(
                    "OPENTM410779767",//"PtiXzgOlsGB2B2NaOMNtJhHdYaxD5Df41pZEe8RIj1A",
                    openId,     //  o7sDrsqAggP4dwbNnVMEC-JX__tE    o7sDrso9Jk1F_lhoItpSY2xTqEmY
                    "https://cdgd.pryun.vip",
                    data
            );
            weChatEntity.sendTempMsg(templateMessage);
            return Result.success();
        } catch (Exception e) {
            System.out.println("模板消息发送失败！");
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }




}
