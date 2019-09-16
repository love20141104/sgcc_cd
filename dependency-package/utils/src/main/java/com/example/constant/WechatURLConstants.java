package com.example.constant;

/**
 * 微信接口URL信息
 */
public class WechatURLConstants {

    private static final  String BASE = "https://api.weixin.qq.com";

    public static final String APPID = "wxe5aa7f870094050d";

    private static final String SECRET = "949ad61412c16d82b58dba6c49cee038";

    public static final  String  GETACCESSTOKEN = BASE+"/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;

    public static final  String  GET_JSAPI_TICKET =  BASE+"/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public static final  String mch_id = "1550585661";

    public static final  String APPKEY = "SSCDGDabcdefghijklmnopqrstuvwxyz";

    public static final String HMACSHA256 = "HMAC-SHA256";

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
}
