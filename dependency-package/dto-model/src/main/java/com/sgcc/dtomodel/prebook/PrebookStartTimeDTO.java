package com.sgcc.dtomodel.prebook;

import com.example.enumclass.PrebookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrebookStartTimeDTO implements Serializable {
    private static final long serialVersionUID = -5404374679217431138L;

    //预约时间段
    String prebookStartTime;
    //预约数量
    int prebookCount;
    //预约状态
    PrebookStatus prebookStatus = PrebookStatus.BOOKABLE;


    public PrebookStartTimeDTO(String prebookStartTime){
        this.prebookStartTime = prebookStartTime;
        this.prebookCount = 1;
        this.prebookStatus = PrebookStatus.BOOKABLE;
    }

    public PrebookStartTimeDTO buildPrebookStartTime(String prebookStartTime){
        this.prebookStartTime = prebookStartTime;
        return this;
    }

    public void buildCount (int prebookCount){
        this.prebookCount += prebookCount;
        if(this.prebookCount >= 4){
            this.prebookStatus = PrebookStatus.UNBOOKABLE;
        }else {
            this.prebookStatus = PrebookStatus.BOOKABLE;
        }
    }

}
