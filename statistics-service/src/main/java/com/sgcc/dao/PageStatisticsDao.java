package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageStatisticsDao {
    private String id;

    //页面地址
    private String pageUrl;

    private String pageName;

    //
    private String userOpenId;

    //调用时间
    private Date visitDate;

    //客户端ip
    private String clientIp;
}
