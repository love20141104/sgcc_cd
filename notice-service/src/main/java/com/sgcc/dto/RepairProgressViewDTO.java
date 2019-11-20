package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairProgressViewDTO implements Serializable {

    private static final long serialVersionUID = -8003277195932797932L;

    private String progress_status;
    private String progress_date;
    private String progress_img1;
    private String progress_img2;
    private String progress_img3;


}
