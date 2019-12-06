package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTableDTO implements Serializable {

    private static final long serialVersionUID = 4653202369483679614L;
    private Integer total;

    private Integer count;

    private List<UserInfoViewDTO> userInfoList;

    private String next_openid;

}
