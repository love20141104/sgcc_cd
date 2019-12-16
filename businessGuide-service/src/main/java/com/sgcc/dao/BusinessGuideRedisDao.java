package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;
@Wither
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("business_guide")
public class BusinessGuideRedisDao implements Serializable {
    private static final long serialVersionUID = -2720156413463423359L;
    @TimeToLive
    private long timetolive = 600000;
    @Id
    private String id;
    private String title;
    //private String content;
    private String contentUrl;
    @Indexed
    private String categoryId;
    private String categoryName;
    private Date createDate;
}
