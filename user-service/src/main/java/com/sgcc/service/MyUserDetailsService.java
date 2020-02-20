package com.sgcc.service;

import com.sgcc.dao.Role;
import com.sgcc.dao.User;
import com.sgcc.dto.JwtRole;
import com.sgcc.dto.JwtUser;
import com.sgcc.entity.query.UserQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service 层需要实现 UserDetailsService 接口，该接口是根据用户名获取该用户的所有信息， 包括用户信息和权限点。
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserQueryEntity userQueryEntity;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser user = new JwtUser();
        List<JwtRole> roleList = new ArrayList<>();
        User result = userQueryEntity.getUserByName(username);
        user.setUserId(result.getUserId());
        user.setUsername(result.getUsername());
        user.setPassword(result.getPassword());
        if (null != user){
            List<Role> roles = userQueryEntity.getRolesByUserId(user.getUserId());
            roles.forEach(role->{
                roleList.add(new JwtRole(
                        role.getRoleId(),
                        role.getRoleCode(),
                        role.getRoleName()
                ));
            });
            user.setRoles(roleList);
        }
        return user;
    }
}
