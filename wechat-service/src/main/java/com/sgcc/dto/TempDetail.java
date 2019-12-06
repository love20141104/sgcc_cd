package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TempDetail implements Serializable {
    private static final long serialVersionUID = 8198156172839711523L;
    private String name;
    private String key;
}
