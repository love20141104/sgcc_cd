package com.sgcc.dao;

import com.sgcc.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 黑名单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistDao implements Serializable {

    private static final long serialVersionUID = 7136868697073821703L;

    private Integer id;
    private String userOpenId;
    private Date createDate;

}
