package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialDTO implements Serializable {
    private static final long serialVersionUID = 8198156172839711523L;

    private String media_id;
    private List<ContentDTO> content;
    private String name;
    private String update_time;
    private String url;
}
