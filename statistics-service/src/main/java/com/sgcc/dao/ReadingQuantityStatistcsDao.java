package com.sgcc.dao;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ReadingQuantityStatistcsDao implements Serializable {

    private static final long serialVersionUID = 2443071554308503675L;
    //文章标题
    private String title;
    //文章url
    private String url;
    //阅读数量
    private int readNum;

    public ReadingQuantityStatistcsDao(
            String title
            ,String url
            ,int readNum
    ){
        this.title = title;
        this.url = url;
        this.readNum = readNum;
    }
}
