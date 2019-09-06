package com.sgcc.entity.query;

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
    public List<AccessTokenDao> findAccessToken(){
        return Lists.newArrayList(accessTokenRepository.findAll());
    }

    /**
     * 查询JSApiTicket
     */
    public List<JSApiTicketDao> findJSApiTicket(){
        return Lists.newArrayList(jsApiTicketRepository.findAll());
    }


}
