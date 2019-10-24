package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BannerSubmitDTO implements Serializable {

    private static final long serialVersionUID = 8150011773282029804L;
    private String banner_img;
    private String banner_url;
}
