package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDeleteDTO implements Serializable {
    private static final long serialVersionUID = -2552994649360331354L;
    private List<String> articleIds;
}
