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
public class BusinessGuideSubmitDto implements Serializable {
    private static final long serialVersionUID = -28201564381066259L;

    private String title;
    private String content;
    private String contentUrl;
    private String categoryId;

}
