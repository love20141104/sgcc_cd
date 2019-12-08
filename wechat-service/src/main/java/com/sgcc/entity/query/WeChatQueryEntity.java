package com.sgcc.entity.query;

import com.alibaba.fastjson.JSONObject;
import com.example.CurrentPage;
import com.example.FileUtil;
import com.example.constant.CommonConstants;
import com.example.constant.WechatURLConstants;
import com.google.common.base.Strings;
import com.sgcc.dao.AccessTokenDao;
import com.sgcc.dao.JSApiTicketDao;
import com.sgcc.dao.UserDao;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.wechat.AccessTokenDTO;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.dtomodel.wechat.TempMessageDTO;
import com.sgcc.dtomodel.wechat.WXConfigDTO;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.event.AccessTokenEntity;
import com.sgcc.repository.WechatUserRepository;
import com.sgcc.wechat.SignatureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
public class WeChatQueryEntity {

    @Autowired
    private WechatUserRepository wechatUserRepository;

    public List<UserDao> findUsers(){
        return wechatUserRepository.findUsers();
    }


    public CurrentPage<UserDao> findUsersByNickName(String nickName,int pageNo,int pageSize){
        return wechatUserRepository.findUsersByNickName(nickName,pageNo,pageSize);
    }


    public CurrentPage<UserDao> findPageList(int pageNo, int pageSize){
        return wechatUserRepository.findPageList(pageNo, pageSize);
    }


    public List<UserDao> findUsersByFullNickName(String fullNickName){
        return wechatUserRepository.findUsersByFullNickName(fullNickName);
    }


    public Integer findUsersByOpenID(String openId,String temp){
        return wechatUserRepository.findUsersByOpenID(openId,temp);
    }


}
