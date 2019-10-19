package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class UpdateServiceHallDTO extends ServiceHallMappingDTO{

    private String id;
    // 营业厅id
    private String serviceHallId;




}
