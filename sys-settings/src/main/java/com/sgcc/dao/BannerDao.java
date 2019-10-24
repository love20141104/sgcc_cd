package com.sgcc.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BannerDao implements Serializable {

    private static final long serialVersionUID = -86082086440602094L;
    private String id;
    private String banner_img;
    private String banner_url;
}
