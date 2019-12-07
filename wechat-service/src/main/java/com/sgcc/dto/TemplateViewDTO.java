package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateViewDTO implements Serializable{
    private static final long serialVersionUID = 2521820711681469471L;
    private String template_id;
    private String title;
    private Map<String,String> datas;
}
