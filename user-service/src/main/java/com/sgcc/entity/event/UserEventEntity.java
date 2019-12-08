package com.sgcc.entity.event;

import com.sgcc.dao.SubscribeDao;
import com.sgcc.dto.HouseholdInfosDTO;
import com.sgcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventEntity {
    @Autowired
    private UserRepository userRepository;

    public int updateSubscribe(SubscribeDao dao) {
        return userRepository.updateSubscribe(dao);
    }



}
