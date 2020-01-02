package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrebookInfoViewDTO implements Serializable {

    private static final long serialVersionUID = 2795856741230473606L;
    private String id;
    private String businessTypeName;
    private String contact;
    private String contactTel;
    private String submitDate;

}
