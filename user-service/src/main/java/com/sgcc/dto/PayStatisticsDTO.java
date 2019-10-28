package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayStatisticsDTO implements Serializable {
    private static final long serialVersionUID = 5808923659854287871L;
    private Date StartDate; //查询时间 "yyyy-MM-dd HH:mm:ss"
    private String DateUnit; // 查询单位 YEAR or MONTH
    private int PayTotal; //支付笔数
    private Double PaySum; // 支付总额
//    private List<PayStatisticsDTO> payStatisticsDTOList;
}
