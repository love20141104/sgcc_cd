package com.sgcc.entity;

import com.example.constant.WechatURLConstants;
import com.sgcc.wechat.SignatureModel;
import com.sgcc.dao.AccessTokenDao;
import com.sgcc.dao.JSApiTicketDao;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.dtomodel.wechat.WXConfigDTO;
import com.sgcc.entity.event.AccessTokenEntity;
import com.sgcc.entity.query.AccessTokenQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WeChatEntity {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccessTokenEntity accessTokenEntity;
    @Autowired
    private AccessTokenQueryEntity accessTokenQueryEntity;

    /**
     * 获取微信服务号的AccessToken
     * @return
     */
    public AccessTokenDTO getAccessToken(){
        List<AccessTokenDao> accessTokenDaos = accessTokenQueryEntity.findAccessToken();
        if(accessTokenDaos.size()>0 && null != accessTokenDaos.get(0)){
            return accessTokenDaos.get(0).build();
        }else {
            AccessTokenDTO accessTokenDTO = restTemplate.getForObject(WechatURLConstants.GETACCESSTOKEN,AccessTokenDTO.class);
            accessTokenEntity.saveAccessToken(new AccessTokenDao(accessTokenDTO));
            return accessTokenDTO;
        }



    }

    /**
     * 获取微信js-api-ticket
     */
    public JSAPITicketDTO getJsApiTicket(){
        List<JSApiTicketDao> jsApiTicketDaos = accessTokenQueryEntity.findJSApiTicket();
        if(jsApiTicketDaos.size()>0 && null != jsApiTicketDaos.get(0)){
            return jsApiTicketDaos.get(0).build();
        }else {
            JSAPITicketDTO jsapiTicketDTO = restTemplate
                    .getForObject
                            (
                                    WechatURLConstants.GET_JSAPI_TICKET
                                            .replace(
                                                    "ACCESS_TOKEN"
                                                    ,getAccessToken().getAccess_token()
                                            )
                            ,JSAPITicketDTO.class);
            accessTokenEntity.saveJSApiTicket(new JSApiTicketDao(jsapiTicketDTO));
            return jsapiTicketDTO;
        }
    }

    /**
     * 获取签名
     * @param url
     * @return
     */
    public WXConfigDTO getSignature(String url) {
        return new SignatureModel(getJsApiTicket().getTicket(),url).build();
    }
}
