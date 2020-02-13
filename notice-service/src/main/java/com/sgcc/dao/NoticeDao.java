package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeDao implements Serializable {

    private static final long serialVersionUID = -6436241340803665322L;

    private String id;

    private String noticeId;

    private String noticeDistrict;

    private String typeName;    // 停电类型

    private String range;       // 抢修范围

    private String noticeDate;  // 停电时间

    public NoticeDao(String id, String noticeId, String noticeDistrict, String typeName,
                     String range, String noticeDate) {
        this.id = id;
        this.noticeId = noticeId;
        this.noticeDistrict = noticeDistrict;
        this.typeName = typeName;
        this.range = range;
        this.noticeDate = noticeDate;
    }
}
