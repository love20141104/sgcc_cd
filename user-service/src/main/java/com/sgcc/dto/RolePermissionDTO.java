package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 4841063511167131394L;
    private String roleCode;
    private String url;
}
