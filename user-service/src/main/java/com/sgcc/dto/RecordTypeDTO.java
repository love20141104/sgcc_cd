package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor  //生成无参构造函数
public class RecordTypeDTO implements Serializable {

    private static final long serialVersionUID = -8177476256795153618L;

    private String dateTime;

    private Double money;

    private List<RecordDetailDTO> recordTypeDTOS = new ArrayList<>();


    public RecordTypeDTO(String dateTime, Double money, List<RecordDetailDTO> recordTypeDTOS) {
        this.dateTime = dateTime;
        this.money = money;
        this.recordTypeDTOS = recordTypeDTOS;
    }
}
