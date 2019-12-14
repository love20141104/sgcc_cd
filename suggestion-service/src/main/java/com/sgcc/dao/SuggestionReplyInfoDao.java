package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyInfoDao implements Serializable {

    private static final long serialVersionUID = -7698954629222840967L;


    // 用户id
    private String userId;

    // 意见内容
    private String suggestionContent;

    // 联系人
    private String suggestionContact;

    // 联系电话
    private String suggestionTel;

    // 意见提交时间
    private Date submitDate;

    private String img_1;

    private String img_2;

    private String img_3;


    private String id;
    private String suggestion_id;
    private String reply_content;     // 回复内容
    private String reply_openid;  // 回复人
    private String reply_date;    // 回复时间
    private String check_openid;  // 审核人
    private int check_state;      // 审核状态
    private String check_reject;
    private String check_date;    // 审核时间





}
