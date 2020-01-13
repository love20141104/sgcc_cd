package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUpViewDao implements Serializable {

    private static final long serialVersionUID = 7548784504327102339L;

    private String id;
    private String userOpenId;
    private String serviceHallId;
    private String busiId;
    private String contact;
    private String phone;
    private String lineUpNo;
    private String lineUpTime;
    private String submitDate;


}
