package com.sgcc.entity.event;

import com.sgcc.dao.AccessTokenDao;
import com.sgcc.dao.JSApiTicketDao;
import com.sgcc.repository.AccessTokenRepository;
import com.sgcc.repository.JSApiTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenEntity {
    @Autowired
    AccessTokenRepository accessTokenRepository;
    @Autowired
    JSApiTicketRepository jsApiTicketRepository;

    /**
     * 存accessTokenDao
     * @param accessTokenDao
     */
    public void saveAccessToken(AccessTokenDao accessTokenDao){
        accessTokenRepository.save(accessTokenDao);
    }

    /**
     * 存jsApiTicketDao
     * @param jsApiTicketDao
     */
    public void saveJSApiTicket(JSApiTicketDao jsApiTicketDao){
        jsApiTicketRepository.save(jsApiTicketDao);
    }
}
