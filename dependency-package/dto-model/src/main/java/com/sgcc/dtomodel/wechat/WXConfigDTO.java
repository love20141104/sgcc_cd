package com.sgcc.dtomodel.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WXConfigDTO implements Serializable {
    private static final long serialVersionUID = 592397521130785367L;

    private boolean debug = true;// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    private String appId; // 必填，公众号的唯一标识
    private String timestamp; // 必填，生成签名的时间戳
    private String nonceStr; // 必填，生成签名的随机串
    private String signature;// 必填，签名
    private List<String> jsApiList = new ArrayList<>(); // 必填，需要使用的JS接口列表

    public WXConfigDTO(String appId,String timestamp,String nonceStr,String signature){
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }
}
