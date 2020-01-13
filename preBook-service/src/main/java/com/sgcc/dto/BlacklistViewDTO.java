package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistViewDTO implements Serializable {

    private static final long serialVersionUID = 8998283324931393896L;
    private Integer id;
    private String userOpenId;
    private String householdNo;
    private String contact;
    private String contactTel;
    private String createDate;

}
