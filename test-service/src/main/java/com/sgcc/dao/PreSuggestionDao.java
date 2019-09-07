package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 意见建议
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class PreSuggestionDao {

    // 意见id
    private String suggestionId;

    // 用户id
    private String userId;

    // 意见标题
    private String title;

    // 意见提交时间
    private Date submitDate;

}
