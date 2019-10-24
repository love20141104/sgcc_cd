package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiStatisticsActiveDto implements Serializable {
    private static final long serialVersionUID = -2720156418106116259L;

    //活跃人数
    private Integer usernum;
    //同比
    private Double qoq;
    //环比
    private Double yoy;

    public ApiStatisticsActiveDto(Integer usernum) {
        this.usernum = usernum;
    }
}
