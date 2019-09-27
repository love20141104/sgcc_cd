package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Comparator;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceHallComputedDistanceDTO implements Comparator<ServiceHallComputedDistanceDTO>, Serializable {
    private static final long serialVersionUID = -1508811425256673147L;

    private String servicehall_id;
    private String servicehall_name ;
    private String servicehall_addr;
    private String servicehall_tel;
    private String servicehall_opentime;
    private String servicehall_district;
    private Double servicehall_lat;
    private Double servicehall_lng;
    private Double servicehall_distance;

    @Override
    public int compare(ServiceHallComputedDistanceDTO o1, ServiceHallComputedDistanceDTO o2) {
        if(o1.getServicehall_distance() > o2.getServicehall_distance()){
            return 1;
        }else{
            return -1;
        }
    }
}