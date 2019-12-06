package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TempDTO implements Serializable {
    private static final long serialVersionUID = 8198156172839711523L;

    private String tempId;
    private String tempName;
    private List<TempDetail> tempDetails;
}