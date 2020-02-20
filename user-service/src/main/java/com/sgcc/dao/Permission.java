package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = -5338825304154347183L;
    private Integer perId;
    private String parentId;
    private String type;
    private String url;
    private String name;
    private String icon;
    private String description;
    private String available;
    private Date createTime;
    private Date updateTime;

}
