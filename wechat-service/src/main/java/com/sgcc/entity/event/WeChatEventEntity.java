package com.sgcc.entity.event;

import com.sgcc.dao.UserDao;
import com.sgcc.dto.UserInfoViewDTO;
import com.sgcc.repository.WechatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class WeChatEventEntity {

    @Autowired
    private WechatUserRepository wechatUserRepository;

    @Async
    public void saveUsers(List<UserInfoViewDTO> userInfoList){
        List<UserDao> userDaos = new ArrayList<>();
        for(UserInfoViewDTO dto : userInfoList){
            userDaos.add(new UserDao(
                    UUID.randomUUID().toString(),
                    dto.getOpenid(),
                    dto.getNickname(),
                    dto.getSex(),
                    dto.getCity(),
                    dto.getHeadimgurl()
            ));
        }

        wechatUserRepository.saveUsers(userDaos);
    }


    public void delWechatUsers(){
        wechatUserRepository.delWechatUsers();
    }

}
