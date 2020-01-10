package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUpQueryInputDTO implements Serializable {

    private static final long serialVersionUID = -2126199813378292954L;
    private String lineUpNo;

}
