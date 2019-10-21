package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessCategorySubmitDto implements Serializable {
    private static final long serialVersionUID = -2720156418106543359L;

    private String categoryName;
    private String note;
}
