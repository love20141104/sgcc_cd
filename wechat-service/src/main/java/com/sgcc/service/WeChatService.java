package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.entity.WeChatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeChatService {

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

    /**
     * 获取Signature
     * @param url
     * @return
     */
    public Result getSignature(String url){
        return Result.success(weChatEntity.getSignature(url));
    }
}
