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
public class BusinessCategoryDto implements Serializable {
    private static final long serialVersionUID = -2720156418106543359L;

    private String id;
    private String categoryName;
}
