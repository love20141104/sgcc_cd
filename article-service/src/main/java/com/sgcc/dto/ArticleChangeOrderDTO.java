package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleChangeOrderDTO implements Serializable {

    private static final long serialVersionUID = 3791786321704724478L;
    private String id1;
    private String id2;
}
