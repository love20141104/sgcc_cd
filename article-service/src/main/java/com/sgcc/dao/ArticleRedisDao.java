package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Wither
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ArticleRedisDao")
public class ArticleRedisDao implements Serializable {
    @TimeToLive
    private long livetime = 86400  ; //TTL
    @Id
    private String id;

    private String ArticleTitle;

    private String ArticleDesc;

    private String ArticleImg;

    private String ArticleUrl;
    @Indexed
    private boolean ArticleRecommended;
    @Indexed
    private String ArticleType;
}
