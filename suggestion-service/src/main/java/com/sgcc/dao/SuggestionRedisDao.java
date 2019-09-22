package com.sgcc.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

@Wither
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("SuggestionRedisDao")
public class SuggestionRedisDao implements Serializable{
    private static final long serialVersionUID = 651593204725609408L;
    @TimeToLive
    private long livetime = 86400 * 3 ; //TTL

    private String id;
    // 意见id
    @Indexed
    private String suggestionId;
    // 用户id
    @Indexed
    private String userId;
    // 意见内容
    private String suggestionContent;
    // 联系人
    private String suggestionContact;
    // 联系电话
    private String suggestionTel;
    // 意见提交时间
    private Date submitDate;

    private String mediaId_1;

    private String img_1;

    private String mediaId_2;

    private String img_2;

    private String mediaId_3;

    private String img_3;

    private Date replyDate;

    private String replyUserId;

    private String replyContent;

}
