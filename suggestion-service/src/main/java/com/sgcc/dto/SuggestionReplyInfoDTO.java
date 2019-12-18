package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyInfoDTO implements Serializable {

    private static final long serialVersionUID = 8881547566687943859L;
    private String id;
    // 用户id
    private String userId;

    // 意见内容
    private String suggestionContent;

    // 联系人
    private String suggestionContact;

    // 联系电话
    private String suggestionTel;

    // 意见提交时间
    private String submitDate;

    private String img_1;

    private String img_2;

    private String img_3;


    //private String id;
    private String suggestionId;
    private String replyContent;     // 回复内容
    private String replyOpenid;  // 回复人
    private String replyDate;    // 回复时间
    private String checkOpenid;  // 审核人
    private Boolean checkState;      // 审核状态
    private String checkReject;
    private String checkDate;    // 审核时间
    private String state;




}
