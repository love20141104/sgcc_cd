package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    private String userId;
    private String userOpenId;
    private String userTel;
    private Boolean isAvailable;

}
