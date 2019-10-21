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
public class BusinessGuideSelectDto implements Serializable {
    private static final long serialVersionUID = -2820151066259L;

    public String title;
    public String categoryId;
}
