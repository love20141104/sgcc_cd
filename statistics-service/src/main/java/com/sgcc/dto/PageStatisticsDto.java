package com.sgcc.dto;

import com.sgcc.dao.PageStatisticsDao;
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
public class PageStatisticsDto implements Serializable {
    //页面地址
    private String pageUrl;

    private String pageName;

    //
    private String userOpenId;


}
