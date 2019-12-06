package com.sgcc.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoList implements Serializable {
    private static final long serialVersionUID = 4781800861235413215L;
    private List<UserInfo> user_info_list;
}
