package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRecordDTO implements Serializable {
    private static final long serialVersionUID = -3625685831285087038L;

    //业务类型
    private String businessCategory;
    //办理次数
    private int applications;
}
