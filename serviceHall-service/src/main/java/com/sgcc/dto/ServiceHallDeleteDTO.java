package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class ServiceHallDeleteDTO implements Serializable {
    private static final long serialVersionUID = 1461958230622806366L;
    private List<String> ids;
}
