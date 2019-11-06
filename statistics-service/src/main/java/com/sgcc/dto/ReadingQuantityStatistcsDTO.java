package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ReadingQuantityStatistcsDTO implements Serializable {
    private static final long serialVersionUID = 2258378347629663962L;

    //文章标题
    private String title;
    //文章url
    private String url;
    //阅读数量
    private int readNum;

    public ReadingQuantityStatistcsDTO(
            String title
            ,String url
            ,int readNum
    ){
        this.title = title;
        this.url = url;
        this.readNum = readNum;
    }
}
