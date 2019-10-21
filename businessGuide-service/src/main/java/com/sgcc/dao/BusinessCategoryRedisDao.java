package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("business_category")
public class BusinessCategoryRedisDao {
    @TimeToLive
    private long timetolive = 600000;
    @Id
    private String id;
    private String categoryName;
    private String note;
}
