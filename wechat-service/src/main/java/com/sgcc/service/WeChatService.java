package com.sgcc.service;

import com.example.constant.WechatURLConstants;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import com.example.result.Result;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.entity.WeChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeChatService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeChatEntity weChatEntity;

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
    public Result getJsApiTicket(){
        return Result.success(weChatEntity.getJsApiTicket());
    }

    public Result getSignature(String url,String noncestr,String timestamp){
        return Result.success(weChatEntity.getSignature(url,noncestr,timestamp));
    }
}
