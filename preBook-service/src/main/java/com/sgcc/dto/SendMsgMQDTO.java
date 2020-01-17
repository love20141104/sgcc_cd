package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMsgMQDTO implements Serializable {

    private static final long serialVersionUID = 1868458792170937003L;

    private String checkerOpenId;
    private String userName;
    private String cancelDate;

}
