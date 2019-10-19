package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class ArticleSubmitDTO implements Serializable{
    private static final long serialVersionUID = -8803429542639497184L;
    private String article_title;

    private String article_desc;

    private String article_img;

    private boolean article_recommended;

    private String article_type;

    private String article_url;
}
