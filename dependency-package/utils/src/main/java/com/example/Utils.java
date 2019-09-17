package com.example;

import com.example.constant.WechatURLConstants;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Utils {
    /**
     * 生成随即字符串
     * @return
     */
    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 获取签名
     * @param string1
     * @return
     */
    public static String sign(String string1) {

        System.out.println(string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            string1 = Utils.byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return string1;
    }

//    public static String createSign(Map params, String key) {
//
////        StringBuffer sbkey = new StringBuffer();
//        // entrySet 所有参与传参的参数按照accsii排序（升序）
////        Set es = params.entrySet();
////        Iterator it = es.iterator();
////
////        while (it.hasNext()) {
////            Map.Entry entry = (Map.Entry) it.next();
////            String k = (String) entry.getKey();
////            Object v = entry.getValue();
////            //空值不传递，不参与签名组串
////            if (null != v && !"".equals(v)) {
////                sbkey.append(k + "=" + v + "&");
////            }
////        }
//        String appid = "appId=" + params.get("appId") ;
//        String nonceStr = "&nonceStr=" + params.get("nonceStr") ;
//        //String nonceStr = "&nonceStr=" + "794036" ;
//        String strPackage = "&package=" + params.get("package") ;
//        String signType = "&signType=" + params.get("signType") ;
//        String timeStamp = "&timeStamp=" + params.get("timeStamp") ;
//        String appkey = "&key=" +key;
//
//
//        String ctnt = appid + nonceStr + strPackage + signType + timeStamp + appkey;
//        System.out.println("ctnt = "+ctnt);
//        //MD5加密,结果转换为大写字符
//        String sign = encodeByMD5(ctnt).toUpperCase();
//        System.out.println("sig = "+ sign );
//        return sign;
//    }


    /**
     * 根据map过去签名
     *
     * @param params    map
     * @param signType 签名类型
     * @param useKey   是否使用key
     * @param key      key
     * @return sign
     */
    public static String encryption(Map<String,String> params,String signType,boolean useKey, String key){
        try{
            //参数校验
            if(!(signType.equals(WechatURLConstants.SHA1)
                    ||signType.equals(WechatURLConstants.MD5)
                    ||signType.equals(WechatURLConstants.HMACSHA256)
            )){
                throw new RuntimeException("签名方法不正确,signType："+signType+" !!");
            }else if(useKey && Strings.isNullOrEmpty(key)){
                throw new RuntimeException("key 为空！！");
            }

            //参数按照accsii排序
            List<String> keyList = new ArrayList<>(params.keySet());
            Collections.sort(keyList);

            //生成拼接字符串
            StringBuffer ctnt = new StringBuffer();
            for(int i = 0;i < keyList.size();i++){
                if(i == 0){
                    ctnt.append(keyList.get(i) +"="+ params.get(keyList.get(i)));
                } else {
                    ctnt.append("&"+keyList.get(i) +"="+ params.get(keyList.get(i)));
                }
            }
            if(Strings.isNullOrEmpty(ctnt.toString())){
                throw new RuntimeException("params 为空！！");
            }else if(useKey){
                ctnt.append("&key="+key);
            }
            System.out.println("ctnt = "+ctnt);
//            String sign = ctnt.toString();

            //加密生成签名
//            MessageDigest crypt = MessageDigest.getInstance(signType);
//            crypt.reset();
//            crypt.update(sign.getBytes("UTF-8"));
//            sign = Utils.byteToHex(crypt.digest()).toUpperCase();
//            System.out.println("sign = "+sign);

            return sign(signType,ctnt.toString());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取签名失败");
        }
    }

    public static String sign(String signType,String ctnt) throws Exception {
        MessageDigest crypt = MessageDigest.getInstance(signType);
        crypt.reset();
        crypt.update(ctnt.getBytes("UTF-8"));
        ctnt = Utils.byteToHex(crypt.digest()).toUpperCase();
        System.out.println("sign = "+ctnt);
        return ctnt;
    }

//    /**
//     * 对字符串进行MD5加密
//     *
//     * @param str 需要加密的字符串
//     * @return 小写MD5字符串 32位
//     */
//    public static String encodeByMD5(String str) {
//        MessageDigest digest;
//
//        try {
//            digest = MessageDigest.getInstance("MD5");
//            digest.update(str.getBytes());
//            return new BigInteger(1, digest.digest()).toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}