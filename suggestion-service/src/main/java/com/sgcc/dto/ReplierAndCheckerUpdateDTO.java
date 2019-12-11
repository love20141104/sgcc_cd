package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplierAndCheckerUpdateDTO implements Serializable {
    protected String id;
    protected String major_region;
    protected String replier_openid;
    protected String replier_name;
    protected String checker_openid;
    protected String checker_name;
}
