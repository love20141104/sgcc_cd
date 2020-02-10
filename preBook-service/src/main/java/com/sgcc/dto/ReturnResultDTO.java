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
public class ReturnResultDTO extends BasicOutDTO implements Serializable {
    private static final long serialVersionUID = -2344493957331367989L;
    private String data;
    private String signature;
}
