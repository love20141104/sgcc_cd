package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRedisDaos implements Serializable {

    private static final long serialVersionUID = -7278962045348720072L;
    private List<ArticleRedisDao> ArticleRedisDaoList;
}
