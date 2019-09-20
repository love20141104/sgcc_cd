package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 意见图片
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class SuggestionImgDao {

    private String id;

    // 意见id
    private String imgId;

    // 用户id
    private String userId;

    // 图片url
    private String imgUrl;

    // 意见提交时间
    private Date submitDate;


}
