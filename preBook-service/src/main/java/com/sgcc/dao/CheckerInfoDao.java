package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckerInfoDao implements Serializable {

    private static final long serialVersionUID = 9168107979491708997L;
    private String id;
    private String checkerName;
    private String checkTel;
    private String userOpenId;
    private String serviceHallName;

}
