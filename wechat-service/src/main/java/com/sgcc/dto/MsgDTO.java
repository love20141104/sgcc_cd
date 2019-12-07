package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MsgDTO implements Serializable {
    private static final long serialVersionUID = 8198156172839711523L;
    private String tempId;
    private Map<String,String> data;
}