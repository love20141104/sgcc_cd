package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor  //生成无参构造函数
public class NoticeFormDTO implements Serializable {

    private static final long serialVersionUID = -8659676023635259437L;

    private String typeName;

    private String time;

    private String range;

    private String progress;

    private String progressTime;

}
