package com.sgcc.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class NoticeDao implements Serializable {

    private static final long serialVersionUID = -6436241340803665322L;

    private String id;

    private String noticeId;

    private String noticeDistrict;

    private String typeName;    // 停电类型

    private String range;       // 抢修范围

    private String progress;    // 抢修进度

    private String progressTime;    //抢修进度对应时间

    private String noticeDate;  // 停电时间

}
