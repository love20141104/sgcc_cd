package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxOpenIdInfoDTO implements Serializable {

    private static final long serialVersionUID = 3177769607245527446L;

    private List<String> openIds;

}
