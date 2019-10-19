

package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class ArticleUpdateDTO implements Serializable {

    private static final long serialVersionUID = 3934451276087963939L;
    private String id;

    private String article_title;

    private String article_desc;

    private String article_img;

    private boolean article_recommended;

    private String article_type;

    private String article_url;
}