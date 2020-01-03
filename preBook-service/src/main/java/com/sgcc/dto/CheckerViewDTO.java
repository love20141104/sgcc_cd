package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 黑名单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckerViewDTO implements Serializable {

    private static final long serialVersionUID = 2224759854085611409L;
    private String id;
    private String checkerName;
    private String checkTel;
    private String userOpenId;
    private String serviceHallId;
    private String serviceHallName;

}
