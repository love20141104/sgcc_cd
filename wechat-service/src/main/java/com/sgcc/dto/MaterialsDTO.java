package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialsDTO implements Serializable {

    private static final long serialVersionUID = -8817128799399147907L;

    private int total_counttotal_count;
    private int item_count;
    private MaterialDTO item;
}
