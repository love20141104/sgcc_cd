package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayResultViewsDTO implements Serializable {

    private static final long serialVersionUID = 8585182610303805670L;

    private double total; // 最近一次缴费金额

    private List<PayResultViewDTO> payResultViewDTOS = new ArrayList<>();


}
