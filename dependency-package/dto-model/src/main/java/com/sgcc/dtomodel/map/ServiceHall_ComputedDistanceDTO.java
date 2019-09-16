package com.sgcc.dtomodel.map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceHall_ComputedDistanceDTO implements Comparator<ServiceHall_ComputedDistanceDTO> {
    private static final long serialVersionUID = 15L;

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
    public int compare(ServiceHall_ComputedDistanceDTO o1, ServiceHall_ComputedDistanceDTO o2) {
        if(o1.getServicehall_distance() > o2.getServicehall_distance()){
            return 1;
        }else{
            return -1;
        }
    }
}
