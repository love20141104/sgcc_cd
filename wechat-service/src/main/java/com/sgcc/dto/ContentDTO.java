package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContentDTO implements Serializable {

    private static final long serialVersionUID = -7955900005275447233L;
    private List<Item> news_item = new ArrayList<>();
}
