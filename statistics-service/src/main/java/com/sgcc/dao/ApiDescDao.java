package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiDescDao implements Serializable {
    private static final long serialVersionUID = -2720156418106643259L;

    private String id;

    //
    private String requestMapping;

    private String requestMethod;
    //
    private String requestDesc;


}
