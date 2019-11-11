package com.sgcc.model;

import com.sgcc.dao.UserSubscribeDao;
import com.sgcc.dto.UserSubscribeDTO;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserSubscribeModel {

    private List<UserSubscribeDao> userSubscribeDaoList;
    private List<UserSubscribeDTO> userSubscribeDTOList;

    public UserSubscribeModel(List<UserSubscribeDao> userSubscribeDaoList) {
        this.userSubscribeDaoList = userSubscribeDaoList;
        List<UserSubscribeDTO> collect = userSubscribeDaoList.stream().map(userSubscribeDao -> {
            UserSubscribeDTO userSubscribeDTO = new UserSubscribeDTO();
            BeanUtils.copyProperties(userSubscribeDao, userSubscribeDTO);
            return userSubscribeDTO;
        }).collect(Collectors.toList());
        this.userSubscribeDTOList=collect;
    }
}
