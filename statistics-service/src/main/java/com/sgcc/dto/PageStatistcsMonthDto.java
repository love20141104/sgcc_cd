package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageStatistcsMonthDto implements Serializable {
    private static final long serialVersionUID = -2720156418106116259L;
    private Integer total;
    private List<PageStatistcsDateDto> pageStatistcsList;
}
