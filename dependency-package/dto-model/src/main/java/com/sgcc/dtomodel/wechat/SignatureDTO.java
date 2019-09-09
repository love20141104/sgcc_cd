package com.sgcc.dtomodel.wechat;


import com.example.Utils;
import com.example.constant.WechatURLConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SignatureDTO implements Serializable {
    private static final long serialVersionUID = -5760408591640273446L;

    private String noncestr = Utils.create_nonce_str();
    private String jsapi_ticket;
    private String timestamp = Utils.create_timestamp();
    private String URL;
    private String appId = WechatURLConstants.APPID;
    private String signature;

    /**
     *
     * @param ticket
     * @param url
     */
    public SignatureDTO(String ticket, String url) {
        this.jsapi_ticket = ticket;
        this.URL = url;
        this.signature = Utils.sign(this.getString1());//生成签名
    }

    /**
     * 生成拼接成字符串string1
     * @return
     */
    private String getString1(){
        return "jsapi_ticket=" + this.getJsapi_ticket() +
                "&noncestr=" + this.getNoncestr() +
                "&timestamp=" + this.getTimestamp() +
                "&url=" + this.getURL();
    }

    public WXConfigDTO build(){
        return new WXConfigDTO(this);
    }
}
