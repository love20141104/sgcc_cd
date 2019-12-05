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
@RedisHash("consumerManager")
public class ConsumerManagerDao implements Serializable {
    private static final long serialVersionUID = -7114624215470571074L;
    @TimeToLive
    private long timeToLive;

    @Id
    private String id;
    @Indexed
    private String consumerManagerId;

    private String consumerManagerName="";

    private String consumerManagerTel="";

    //服务区域
    private String consumerManagerServiceArea="";

    @Indexed
    //所属行政区域
    private String consumerManagerAdministrativeRegion="";

    private String consumerManagerDuty="";

    private String consumerManagerWorkTime="";

    private String consumerManagerEmergencyTel="";

    @Indexed
    private String consumerManagerWorkUnit="";

    private String consumerManagerCategory="";

    private String consumerManagerImg="";




    public ConsumerManagerDao(
            String id,
            String consumerManagerId,
            String consumerManagerName,
            String consumerManagerTel,
            String consumerManagerServiceArea,
            String consumerManagerAdministrativeRegion,
            String consumerManagerDuty,
            String consumerManagerWorkTime,
            String consumerManagerEmergencyTel,
            String consumerManagerWorkUnit,
            String consumerManagerCategory,
            String consumerManagerImg
    ){
                this.timeToLive = 24 * 3600;
                this.id = id;
                this.consumerManagerId = consumerManagerId;
                this.consumerManagerName = consumerManagerName;
                this.consumerManagerTel = consumerManagerTel;
                this.consumerManagerServiceArea = consumerManagerServiceArea;
                this.consumerManagerAdministrativeRegion = consumerManagerAdministrativeRegion;
                this.consumerManagerDuty = consumerManagerDuty;
                this.consumerManagerWorkTime = consumerManagerWorkTime;
                this.consumerManagerEmergencyTel = consumerManagerEmergencyTel;
                this.consumerManagerWorkUnit = consumerManagerWorkUnit;
                this.consumerManagerCategory = consumerManagerCategory;
                this.consumerManagerImg = consumerManagerImg;
    }

}
