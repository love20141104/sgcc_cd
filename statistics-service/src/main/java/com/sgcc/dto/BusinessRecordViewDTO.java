package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRecordViewDTO implements Serializable {
    private static final long serialVersionUID = 1210666646516417326L;

    //总次数
    private int total;
    private List<BusinessRecordDTO> businessRecordDTOs = new ArrayList<>();
}
