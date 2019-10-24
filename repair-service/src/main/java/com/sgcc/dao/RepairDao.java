package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairDao implements Serializable {

    private static final long serialVersionUID = 3992162304209357821L;

    private String id;

    private String repairId;

    private String openId;

    private String repairContent;

    private String repairContact;

    private String repairTel;

    private String repairAddr;

    private String repairImg1;

    private String repairImg2;

    private String repairImg3;

    private Date submitDate;



}
