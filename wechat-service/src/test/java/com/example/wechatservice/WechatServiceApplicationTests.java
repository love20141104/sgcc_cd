package com.example.wechatservice;

import com.example.Utils;
import com.example.constant.WechatURLConstants;
import com.sgcc.wxpay.Sgcc_WXPay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatServiceApplicationTests.class)
public class WechatServiceApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        Sgcc_WXPay sgcc_wxPay = new Sgcc_WXPay();
        Map<String,String >prepay = sgcc_wxPay.unifiedorder("o7sDrspJi-VkHsv2ls7r_Bso_mgQ","1");

        prepay.get("prepay_id");

        Map<String,String > requ = new HashMap<>();
        requ.put("appId", WechatURLConstants.APPID);
        requ.put("timeStamp",Utils.create_timestamp());
        requ.put("nonceStr",Utils.create_nonce_str());
        requ.put("package","prepay_id="+ prepay.get("prepay_id"));
        //requ.put("signType","MD5");
        requ.put("paySign",Utils.encryption(requ,WechatURLConstants.MD5,true,WechatURLConstants.APPKEY));
        //requ.put("paySign",Utils.createSign(requ,WechatURLConstants.APPKEY));
        System.out.print(prepay);
    }

}
