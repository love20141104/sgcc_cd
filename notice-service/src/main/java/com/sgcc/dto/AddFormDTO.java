package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddFormDTO implements Serializable {

    private static final long serialVersionUID = 2219396935452655443L;

    private String noticeDistrict;

    private String typeName;

    private String notiveDate;

    private String range;

}
