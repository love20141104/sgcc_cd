package com.sgcc.inhabitant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class InhabitantRenameDTO implements Serializable {
    private static final long serialVersionUID = 8259578234317479309L;
    private String id;
}
