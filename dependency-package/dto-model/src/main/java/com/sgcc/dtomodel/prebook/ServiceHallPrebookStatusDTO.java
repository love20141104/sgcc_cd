package com.sgcc.dtomodel.prebook;

import com.example.constant.PrebookStartTimeConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Data
public class ServiceHallPrebookStatusDTO implements Serializable {

    private static final long serialVersionUID = -3096504596323928724L;

    //营业厅id
    private String serviceHallId;
    //预约业务办理日期
    private String prebookDate;
    //营业厅预约状态
    private Map<String,PrebookStartTimeDTO> prebookStartTimeDTOS = new HashMap<>();

    /**
     * 构造函数
     * @param serviceHallId
     * @param prebookDate
     * @param prebookStartTimeDTOS
     */
    public ServiceHallPrebookStatusDTO(String serviceHallId,String prebookDate,Map<String,PrebookStartTimeDTO> prebookStartTimeDTOS){
        this.serviceHallId = serviceHallId;
        this.prebookDate = prebookDate;
        this.prebookStartTimeDTOS = new HashMap<>(prebookStartTimeDTOS);
    }

    /**
     * 构造函数
     * @param serviceHallId
     * @param prebookDate
     */
    public ServiceHallPrebookStatusDTO(String serviceHallId,String prebookDate){
        this.serviceHallId = serviceHallId;
        this.prebookDate = prebookDate;
        this.prebookStartTimeDTOS = new HashMap<>();
    }

    /**
     * 更新营业厅预约信息
     * @param prebookStartTimeDTO
     * @return
     */
    public void buildPprebookStartTimeDTOS(PrebookStartTimeDTO prebookStartTimeDTO){
        if(this.prebookStartTimeDTOS.keySet().contains(prebookStartTimeDTO.prebookStartTime)){
            this.prebookStartTimeDTOS.get(prebookStartTimeDTO.prebookStartTime).buildCount(prebookStartTimeDTO.getPrebookCount());
        }else {
            this.prebookStartTimeDTOS.put(prebookStartTimeDTO.prebookStartTime,prebookStartTimeDTO);
        }
    }


    /**
     * 补全没有预约信息的时段
     */
    public void buildNullPprebookStartTimeDTOS(){
        PrebookStartTimeConstants.TIME_LIST.forEach(time ->{
            if(!this.prebookStartTimeDTOS.keySet().contains(time)){
                this.prebookStartTimeDTOS.put(time,new PrebookStartTimeDTO().buildPrebookStartTime(time));
            }
        });
    }
}
