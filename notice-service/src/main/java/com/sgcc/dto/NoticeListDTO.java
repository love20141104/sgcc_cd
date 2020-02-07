package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor  //生成无参构造函数
@AllArgsConstructor
public class NoticeListDTO implements Serializable {
    private static final long serialVersionUID = -4429799369811700616L;
    private String typeName;
    private String time;
    private String range;
    private List<ProgressDTO> list;

}
