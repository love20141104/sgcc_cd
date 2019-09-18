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
public class PrebookDTO implements Serializable {
    private static final long serialVersionUID = -3090200043033631931L;

    // 用户id
    private String userId;

    // 营业厅id

    private String serviceHallId;

    // 预约业务办理日期
    private Date prebookDate;

    // 预约业务办理开始时间
    private Date prebookStartTime;

    // 办理业务类型
//    private String businessTypeId;

    // 预约号
    private String prebookCode;

    // 联系人
    private String contact;

    // 联系电话
    private String contactTel;

    // 预约提交时间
    private Date submitDate;
}
