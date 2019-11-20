package com.sgcc.dao;

import com.sgcc.Enum.RepairProgressEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairProgressDao {
    private String id;
    private String userOpenId;
    private String jobId;
    private RepairProgressEnum progressStatus;
    private Date progressDate;
    private String progressImg1;
    private String progressImg2;
    private String progressImg3;
}
