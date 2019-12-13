package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionViewDTO implements Serializable{
    private static final long serialVersionUID = -1820156418106116259L;
    private String id;

    // 意见id
    private String suggestionId;

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

    private String replyUserId;

    private String replyContent;

    private Date replyDate;

    private Boolean checkState;
}
