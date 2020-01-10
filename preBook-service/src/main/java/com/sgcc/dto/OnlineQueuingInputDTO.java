package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineQueuingInputDTO implements Serializable {

    private static final long serialVersionUID = 7511838287348445923L;
    private String userOpenId;
    private String hallId;
    private String busiId;
    private String contact;
    private String phone;
}
