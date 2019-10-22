package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UpdateFormDTO implements Serializable {

    private static final long serialVersionUID = 4716469677203124951L;

    private String noticeId;

    private String noticeDistrict;

    private String typeName;    // 停电类型

    private String range;       // 抢修范围

    private String noticeDate;  // 停电时间

    public UpdateFormDTO(String noticeId, String noticeDistrict, String typeName, String range, String noticeDate) {
        this.noticeId = noticeId;
        this.noticeDistrict = noticeDistrict;
        this.typeName = typeName;
        this.range = range;
        this.noticeDate = noticeDate;
    }
}
