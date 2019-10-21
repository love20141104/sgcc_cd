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

    public String id;
    public String title;
    public String content;
    public String contentUrl;
    public String categoryId;
    public Date createTime;
}
