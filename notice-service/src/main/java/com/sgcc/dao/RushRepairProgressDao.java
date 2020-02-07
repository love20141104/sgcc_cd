package com.sgcc.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RushRepairProgressDao implements Serializable {
    private static final long serialVersionUID = 732546740604300503L;
    private String id;
    private String notice_id;
    private Integer progress_state;
    private String repair_personnel;
    private String cause_of_failure;
    private String progressImg1;
    private String progressImg2;
    private String progressImg3;
    private Date submit_date;
}
