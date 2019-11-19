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
import java.util.UUID;

/**
 * 预约信息
 */
@Wither
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("preBook")
public class PreBookDao implements Serializable {
    private static final long serialVersionUID = -5166501578939845645L;
    @TimeToLive
    private long livetime; //TTL
    @Id
    private String id;
    // 用户id
    @Indexed
    private String userId;

    // 营业厅id
    @Indexed
    private String serviceHallId;

    // 预约业务办理日期
    @Indexed
    private String prebookDate;

    // 预约业务办理时间段
    @Indexed
    private String prebookStartTime;

    // 办理业务类型
//    private String businessTypeId;

    // 预约号
    private String prebookCode;

    // 联系人
    private String contact;

    // 联系电话
    private String contactTel;

    // 预约提交时间
    private String submitDate;

    public PreBookDao(String id, String userId, String serviceHallId, String prebookCode, String prebookDate,
                      String prebookStartTime, String contact, String contactTel, String submitDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            this.livetime = DateUtils.getSeconds() + (DateUtils.daysBetweenTwoDate(new Date(), simpleDateFormat.parse(prebookDate)) * 24 * 3600);
            this.id = id;
            this.userId = userId;
            this.serviceHallId = serviceHallId;
            this.prebookDate = prebookDate;
            this.prebookStartTime = prebookStartTime;
            this.prebookCode = prebookCode;
            this.contact = contact;
            this.contactTel = contactTel;
            this.submitDate = submitDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }


}
