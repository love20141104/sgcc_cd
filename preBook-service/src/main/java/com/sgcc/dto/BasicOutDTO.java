package com.sgcc.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicOutDTO implements Serializable {

    private static final long serialVersionUID = -8822599479936693961L;

    private String code;
    private String msg;

}
