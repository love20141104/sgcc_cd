package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRole implements GrantedAuthority {

    private Integer roleId;
    private String roleCode;
    private String roleName;

    @Override
    public String getAuthority() {
        return roleCode;
    }
}
