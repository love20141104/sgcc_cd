package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerManagerInsertDTO implements Serializable {
    private static final long serialVersionUID = -6959601097856347077L;

    private String consumerManagerName="";
    private String consumerManagerTel="";
    //服务区域
    private String consumerManagerServiceArea="";
    //所属行政区域
    private String consumerManagerAdministrativeRegion="";
    private String consumerManagerDuty="";
    private String consumerManagerWorkTime="";
    private String consumerManagerEmergencyTel="";
    private String consumerManagerWorkUnit="";
    private String consumerManagerCategory="";
    private String consumerManagerImg="";
}
