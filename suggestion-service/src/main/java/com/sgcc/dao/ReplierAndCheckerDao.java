package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplierAndCheckerDao implements Serializable {
    private static final long serialVersionUID = -7730958603404414050L;
    protected String id;
    protected String major_region;
    protected String replier_openid;
    protected String replier_name;
    protected String checker_openid;
    protected String checker_name;
}
