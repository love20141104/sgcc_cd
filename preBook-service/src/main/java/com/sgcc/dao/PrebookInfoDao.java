package com.sgcc.dao;

import com.sgcc.repository.PreBookInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookInfoDao implements Serializable {

    private static final long serialVersionUID = -6211857993819466752L;

    private String id;
    private String userOpenId;
    private String businessTypeId;
    private String businessTypeName;
    private String serviceHallId;
    private String serviceHallName;
    private String ticketMonth;

    private String lineupNo;
    private Date lineupTime;

    private String contact;
    private String contactTel;
    private Date submitDate;
    private Integer status;
    private String rejectReason;
    private String checkerId;

    private Date startDate;
    private Date endDate;

    private Integer ticketStatus;

    private String prebookNo;

    private Boolean isPrinted;

    private Date checkDate;

    private Boolean isBlackList;

    private Boolean isCancel;
    private Date cancelDate;


}
