package com.sgcc.entity.query;

import com.sgcc.dto.HouseholdInfoDTO;
import com.sgcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserQueryEntity {
    @Autowired
    private UserRepository userRepository;

    public HouseholdInfoDTO getDefaultHousehold(String openId) {
        return userRepository.getDefaultHousehold(openId);
    }
}
