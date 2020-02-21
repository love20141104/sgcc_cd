package com.sgcc.entity.query;

import com.sgcc.dao.Role;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.User;
import com.sgcc.dto.HouseholdInfosDTO;
import com.sgcc.dto.JwtRole;
import com.sgcc.dto.RolePermissionDTO;
import com.sgcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryEntity {
    @Autowired
    private UserRepository userRepository;

    public HouseholdInfosDTO getDefaultHousehold(String openId) {
        return userRepository.getDefaultHousehold(openId);
    }

    public SubscribeDao findSubscribe(String openId) {
        return userRepository.findSubscribe(openId);
    }

    public List<User> getUserByName(String username){
        return userRepository.getUserByName(username);
    }

    public List<Role> getRolesByUserId(String userId) {
        return userRepository.getRolesByUserId(userId);
    }

    public List<RolePermissionDTO> getRolePermissions(){
        return userRepository.getRolePermissions();
    }

}
