package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubmitDTO implements Serializable {

    private static final long serialVersionUID = -3651963881700598173L;

    private String openid;

    private String lang;




}
