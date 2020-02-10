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
public class LineUpInfoOutDTO extends BasicOutDTO implements Serializable {

    private static final long serialVersionUID = 8039358207271955601L;

//    private InputData data;

    private Map<String,String> data = new LinkedHashMap<>();

    public LineUpInfoOutDTO(String code,String msg,Map<String, String> data) {
        super.setCode(code);
        super.setMsg(msg);
        this.data = data;
    }
}
