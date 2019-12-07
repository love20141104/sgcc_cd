package com.sgcc.dto;

import com.sgcc.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
public class UserPageableListDTO implements Serializable {
    protected int pageNo;
    protected int pageSize;
    protected int pageTotal;
    protected int itemtotal;
    protected List<UserDao> items;
}
