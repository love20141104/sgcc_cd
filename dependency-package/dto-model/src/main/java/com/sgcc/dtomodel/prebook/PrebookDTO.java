package com.sgcc.dtomodel.prebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrebookDTO implements Serializable,Comparable<PrebookDTO> {
    private static final long serialVersionUID = -3090200043033631931L;

    // 用户id
    private String userId;

    // 营业厅id

    private String serviceHallId;

    // 预约业务办理日期
    private String prebookDate;

    // 预约业务办理时间段
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

    @Override
    public int compareTo(PrebookDTO prebookDTO) {
        if(this.getPrebookDate().compareTo(prebookDTO.getPrebookDate()) == 0){
            return this.getPrebookStartTime().compareTo(prebookDTO.getPrebookStartTime());
        }else {
            return this.getPrebookDate().compareTo(prebookDTO.getPrebookDate());
        }
    }
}
