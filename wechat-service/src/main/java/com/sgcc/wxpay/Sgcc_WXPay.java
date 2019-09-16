package com.sgcc.wxpay;

import com.example.Utils;
import com.sgcc.wxpay.sdk.WXPay;
import com.sgcc.wxpay.sdk.WXPayConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Sgcc_WXPay {

    public Map<String, String> unifiedorder(String openId,String totalFee) throws Exception {
        Sgcc_WXPayConfig config = new Sgcc_WXPayConfig();
        WXPay wxpay = new WXPay(config,"cdgd.pryun.vip/wxPay");
        Random rand = new Random();
        StringBuffer sb=new StringBuffer();
        for (int i=1;i<=32;i++){
            int randNum = rand.nextInt(9)+1;
            String num=randNum+"";
            sb=sb.append(num);
        }
        String random=String.valueOf(sb);
        Map<String, String> data = new HashMap<String, String>();

        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", random);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", totalFee);
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
        data.put("openid",openId);
        data.put("sign_type",WXPayConstants.MD5);
        try{
            Map<String, String> resp = new HashMap<>();
            resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("统一下单失败！！");

        }



    }


}
