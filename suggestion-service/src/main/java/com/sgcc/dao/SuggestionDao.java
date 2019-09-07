package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 意见建议
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class SuggestionDao {

    private String id;

    // 意见id
    private String suggestionId;

    // 用户id
    private String userId;

    // 意见标题
    private String suggestionTitle;

    // 意见内容
    private String suggestionContent;

    // 联系人
    private String suggestionContact;

    // 联系电话
    private String suggestionTel;

    // 意见提交时间
    private Timestamp submitDate;

}
