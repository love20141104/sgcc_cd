package com.sgcc.dao;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDao {
    private String userId;
    private String userOpenId;
    private String userTel;
    private Boolean isAvailable;

    public UserDao(String userId, String userOpenId, String userTel, Boolean isAvailable) {
        this.userId = userId;
        this.userOpenId = userOpenId;
        this.userTel = Strings.isNullOrEmpty(userTel)?"":userTel;
        this.isAvailable = isAvailable;
    }
}
