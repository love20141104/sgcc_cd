package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairProgressSubmitDTO implements Serializable {

    private static final long serialVersionUID = 2357394754223917414L;

    private String noticeId;
    private String progressStatus;
//    private Date progressDate;
    private String progressImg1;
    private String progressImg2;
    private String progressImg3;


}
