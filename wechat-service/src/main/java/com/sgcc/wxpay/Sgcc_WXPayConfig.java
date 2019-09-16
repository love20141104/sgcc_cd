package com.sgcc.wxpay;

import com.example.constant.WechatURLConstants;
import com.sgcc.wxpay.sdk.IWXPayDomain;
import com.sgcc.wxpay.sdk.WXPayConfig;
import com.sgcc.wxpay.sdk.WXPayConstants;

import java.io.*;
import java.io.InputStream;

public class Sgcc_WXPayConfig extends WXPayConfig {

    private byte[] certData;

    public Sgcc_WXPayConfig() throws Exception {
//        String certPath = "/path/to/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
    }



    protected String getAppID() {
        return WechatURLConstants.APPID;
    }

    public String getMchID() {
        return WechatURLConstants.mch_id;
    }


    public String getKey() {
        return "SSCDGDabcdefghijklmnopqrstuvwxyz";
    }


    public InputStream getCertStream() {
//        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
//        return certBis;
        return null;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }


    public IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {

            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }

}
