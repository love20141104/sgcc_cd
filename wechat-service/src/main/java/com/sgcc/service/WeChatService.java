package com.sgcc.service;

import com.example.constant.WechatURLConstants;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeChatService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取微信服务号的AccessToken
     * @return
     */
    public Result getAccessToken(){
        return Result.success(restTemplate.getForObject(WechatURLConstants.GETACCESSTOKEN,AccessTokenDTO.class));
    }
}
