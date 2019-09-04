package com.sgcc.dao.basedao;

import lombok.Data;
import lombok.experimental.Wither;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@Wither
@Data
public abstract class RedisBaseDao implements Serializable {

    //@TimeToLive
    //final long livetime = 60L; //TTL
}
