package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 预约信息
 */
@Data
@Wither
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
@RedisHash("PreBook")
public class PreBookDao implements Serializable {
    private static final long serialVersionUID = -5166501578939845645L;
    @TimeToLive
    private long livetime ; //TTL
    @Id
    private String id;
    // 用户id
    private String userId;

    // 营业厅id
    private String serviceHallId;

    // 预约业务办理日期
    private Date prebookDate;

    // 预约业务办理开始时间
    private Timestamp prebookStartTime;

    // 办理业务类型
//    private String businessTypeId;

    // 预约号
    private String prebookCode;

    // 联系人
    private String contact;

    // 联系电话
    private String contactTel;

    // 预约提交时间
    private Timestamp submitDate;

}
