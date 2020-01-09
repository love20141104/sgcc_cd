package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintStatusUpdateDTO implements Serializable {

    private static final long serialVersionUID = -511295952353294676L;
    private List<String> ids;

}
