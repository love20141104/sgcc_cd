package com.sgcc.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class ArticleDao implements Serializable {

    private static final long serialVersionUID = -180015909903358138L;
    private String id;

    private String article_title;

    private String article_desc;

    private String article_img;

    private String article_url;

    private boolean article_recommended;

    private String article_type;

    private Date submit_time;

    private Integer order_no;

}
