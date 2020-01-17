package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookInfoSubmitDTO implements Serializable {

    private static final long serialVersionUID = -4050132859628546270L;
    private String userOpenId;
    private List<String> ticketMonth;
    private String businessTypeId;
    private String businessTypeName;
    private String serviceHallId;
    private String serviceHallName;
    private List<HouseHoldDTO> householdNos;
    private String contact;
    private String contactTel;
    private String day;
    private String prebookDate;


}
