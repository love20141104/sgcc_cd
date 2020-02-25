package com.sgcc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RushRepairProgressSubmitDTO implements Serializable {
    private static final long serialVersionUID = 4203117079774187126L;
    private String notice_id;
    private Integer progress_state;
    private String repair_personnel;
    private String cause_of_failure;
    private String progressImg1;
    private String progressImg2;
    private String progressImg3;
}
