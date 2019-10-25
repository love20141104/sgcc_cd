package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiStatistcsDateDto implements Serializable {
    private static final long serialVersionUID = -2720156418106116259L;

    private Date date;
    private Integer urlNum;
    private Integer clientIpNum;
    private Integer userOpenIdNum;
}
