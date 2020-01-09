package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookListViewDTO implements Serializable {

    private static final long serialVersionUID = 3569673389286793423L;
    private String id;
    private String userOpenId;
    private String businessTypeId;
    private String businessTypeName;
    private String serviceHallId;
    private String serviceHallName;
    private String householdNo;

    private String lineupNo;
    private String lineupTime;

    private String contact;
    private String contactTel;
    private String submitDate;
    private Integer status;
    private String rejectReason;
    private String checkerId;

    private String startDate;
    private String endDate;

    private Integer ticketStatus;

    private String prebookNo;

}
