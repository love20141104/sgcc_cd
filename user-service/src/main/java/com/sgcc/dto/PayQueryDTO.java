package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayQueryDTO implements Serializable {

    private static final long serialVersionUID = -4589317787906409811L;
    private Date StartDate; //查询时间 "yyyy-MM-dd HH:mm:ss"
    private String DateUnit; // 查询单位 YEAR or MONTH
    // case : { "StartDate":"2019-08-02 11:49:52","DateUnit":"MONTH"}
}
