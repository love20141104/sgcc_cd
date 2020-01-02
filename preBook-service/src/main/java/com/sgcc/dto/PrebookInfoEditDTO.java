package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookInfoEditDTO implements Serializable {

    private static final long serialVersionUID = -4050132859628546270L;
    private String id;
    private int status;
    private String rejectReason;
    private String userOpenId;

}
