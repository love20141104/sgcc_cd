package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class UserSubDTO implements Serializable {

    private static final long serialVersionUID = -4538394149109177046L;
    private String key;
    private String caption;
    private int isSub;
}
