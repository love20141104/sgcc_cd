package com.example.sha1;

import com.example.constant.WechatURLConstants;
import com.sgcc.dtomodel.wechat.SignatureDTO;

import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class Sign {
//    public static void main(String[] args) {
//        String jsapi_ticket = "jsapi_ticket";
//
//        // 注意 URL 一定要动态获取，不能 hardcode
//        String url = "http://example.com";
//        Map<String, String> ret = sign(jsapi_ticket, url);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
//    };
    public static SignatureDTO sign(String jsapi_ticket, String url,String noncestr,String times_tamp) {
//        Map<String, String> ret = new HashMap<String, String>();
        SignatureDTO signatureDTO = new SignatureDTO(noncestr,jsapi_ticket,times_tamp,url,WechatURLConstants.APPID,"");
//        String nonce_str = noncestr;
//        String timestamp = times_tamp;
        String string1;
//        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + signatureDTO.getJsapi_ticket() +
                "&noncestr=" + signatureDTO.getNoncestr() +
                "&timestamp=" + signatureDTO.getTimestamp() +
                "&url=" + signatureDTO.getURL();
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signatureDTO.setSignature(byteToHex(crypt.digest()));
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }


        return signatureDTO;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

//    private static String create_nonce_str() {
//        return UUID.randomUUID().toString();
//    }
//
//    private static String create_timestamp() {
//        return Long.toString(System.currentTimeMillis() / 1000);
//    }
}
