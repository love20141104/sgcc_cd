package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddFormDTO implements Serializable {

    private static final long serialVersionUID = 2219396935452655443L;

    private String district;

    private String typeName;

    private String time;

    private String range;

}
