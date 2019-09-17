package com.sgcc.entity.query;

import com.example.constant.RedisConstants;
import com.sgcc.dao.AccessTokenDao;
import com.sgcc.dao.JSApiTicketDao;
import com.sgcc.repository.AccessTokenRepository;
import com.sgcc.repository.JSApiTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import java.util.List;

@Component
public class AccessTokenQueryEntity {
    @Autowired
    AccessTokenRepository accessTokenRepository;
    @Autowired
    JSApiTicketRepository jsApiTicketRepository;

    /**
     * 查询AccessToken
     */
    public AccessTokenDao findAccessToken(){
        return accessTokenRepository.findById(RedisConstants.ACCESS_TOKEN_ID).get();
    }

    /**
     * 查询JSApiTicket
     */
    public JSApiTicketDao findJSApiTicket(){
        return jsApiTicketRepository.findById(RedisConstants.JSAPI_TICKET_ID).get();
    }


}
