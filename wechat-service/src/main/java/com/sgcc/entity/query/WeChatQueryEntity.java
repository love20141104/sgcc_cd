package com.sgcc.entity.query;

import com.example.CurrentPage;
import com.sgcc.dao.UserDao;
import com.sgcc.repository.WechatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
