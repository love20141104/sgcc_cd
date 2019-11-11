package com.example.constant;

/**
 * 微信接口URL信息
 */
public class WechatURLConstants {

    private static final  String BASE = "https://api.weixin.qq.com";

    //public static final String APPID = "wxe5aa7f870094050d";    //电享+

    public static final String APPID = "wxe6c880a3994d6443";  // 索思科得

    //private static final String SECRET = "949ad61412c16d82b58dba6c49cee038";    //电享+

    private static final String SECRET = "c0629cd108bf6f8d60d4aa31f4f6484a";  // 索思科得

    public static final  String  GETACCESSTOKEN = BASE+"/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;

    public static final  String  GET_JSAPI_TICKET =  BASE+"/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public static final  String  URL_GET_MEDIA =  BASE+"/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    public static final  String SEND_MESSAGE_TEMPLATE = BASE+"/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public static final String UPLOAD_TEMPORARY_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    //public static final  String mch_id = "1550585661";      // 电享+
    public static final  String mch_id = "1555016091";    // 索思科得
    public static final  String APPKEY = "SSCDGDabcdefghijklmnopqrstuvwxyz";

    public static final String HMACSHA256 = "HMAC-SHA256";

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
}
