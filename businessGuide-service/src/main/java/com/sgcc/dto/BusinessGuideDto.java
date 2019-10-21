package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessGuideDto implements Serializable {
    private static final long serialVersionUID = -2820156438106643259L;

    private String id;
    private String title;
    private String content;
    private String contentUrl;
    private String categoryId;
    private String categoryName;
    private Date createDate;
}
