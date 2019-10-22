package com.sgcc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ConsumerManagerDTO implements Serializable,Comparable<ConsumerManagerDTO> {
    private static final long serialVersionUID = -1695157165054820860L;

    private String consumerManagerId;
    private String consumerManagerName;
    private String consumerManagerTel;
    //服务区域
    private String consumerManagerServiceArea;

    //所属行政区域
    private String consumerManagerAdministrativeRegion;
    private String consumerManagerDuty;
    private String consumerManagerWorkTime;
    private String consumerManagerEmergencyTel;
    private String consumerManagerWorkUnit;
    private String consumerManagerCategory;
    private String consumerManagerImg;

    public ConsumerManagerDTO(
            String consumerManagerId,
            String consumerManagerName,
            String consumerManagerTel,
            //服务区域
            String consumerManagerServiceArea,

            //所属行政区域
            String consumerManagerAdministrativeRegion,
            String consumerManagerDuty,
            String consumerManagerWorkTime,
            String consumerManagerEmergencyTel,
            String consumerManagerWorkUnit,
            String consumerManagerCategory,
            String consumerManagerImg
    ) {
        this.consumerManagerId = consumerManagerId;
        this.consumerManagerName = consumerManagerName;
        this.consumerManagerTel = consumerManagerTel;
        //服务区域
        this.consumerManagerServiceArea = consumerManagerServiceArea;

        //所属行政区域
        this.consumerManagerAdministrativeRegion = consumerManagerAdministrativeRegion;
        this.consumerManagerDuty = consumerManagerDuty;
        this.consumerManagerWorkTime = consumerManagerWorkTime;
        this.consumerManagerEmergencyTel = consumerManagerEmergencyTel;
        this.consumerManagerWorkUnit = consumerManagerWorkUnit;
        this.consumerManagerCategory = consumerManagerCategory;
        this.consumerManagerImg = consumerManagerImg;
    }

    @Override
    public int compareTo(ConsumerManagerDTO consumerManagerDTO) {
        if(this.getConsumerManagerAdministrativeRegion().compareTo(consumerManagerDTO.getConsumerManagerAdministrativeRegion()) == 0){
            return this.getConsumerManagerName().compareTo(consumerManagerDTO.getConsumerManagerName());
        }else {
            return this.getConsumerManagerAdministrativeRegion().compareTo(consumerManagerDTO.getConsumerManagerAdministrativeRegion());
        }
    }
}
