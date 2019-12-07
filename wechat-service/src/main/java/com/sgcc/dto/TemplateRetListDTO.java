package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRetListDTO implements Serializable {

    private static final long serialVersionUID = -2761358344411007625L;
    private List<TemplateRetDTO> template_list;
}
