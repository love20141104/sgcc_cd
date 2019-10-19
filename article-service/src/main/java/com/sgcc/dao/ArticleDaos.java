package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDaos implements Serializable {
    private static final long serialVersionUID = 5515475957182561386L;
    private List<ArticleDao> ArticleRedisDaoList;
}
