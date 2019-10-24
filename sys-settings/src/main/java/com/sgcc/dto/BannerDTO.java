package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BannerDTO implements Serializable {

    private static final long serialVersionUID = 3424072700150589558L;
    private String id;
    private String banner_img;
    private String banner_url;
}
