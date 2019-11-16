package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class BusinessCategoryHotDto implements Serializable {
    private static final long serialVersionUID = 2891600526435163028L;
    private String id;

    public BusinessCategoryHotDto(String id, String categoryName, String note) {
        this.id = id;
        this.categoryName = categoryName;
        this.note = note;
        this.isHot = false;
    }

    public BusinessCategoryHotDto(String id, String categoryName, String note,boolean isHot) {
        this.id = id;
        this.categoryName = categoryName;
        this.note = note;
        this.isHot = isHot;
    }

    private String categoryName;
    private String note;
    private Boolean isHot = false;

}
