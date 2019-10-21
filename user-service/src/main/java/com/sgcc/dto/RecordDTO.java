package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor  //生成无参构造函数
public class RecordDTO implements Serializable {

    private static final long serialVersionUID = -8659676023635259437L;

    private String userName;

    private String userNo;

    private List<RecordTypeDTO> recordTypeDTOS = new ArrayList<>();

    public RecordDTO(String userName, String userNo, List<RecordTypeDTO> recordTypeDTOS) {
        this.userName = userName;
        this.userNo = userNo;
        this.recordTypeDTOS = recordTypeDTOS;
    }



}
