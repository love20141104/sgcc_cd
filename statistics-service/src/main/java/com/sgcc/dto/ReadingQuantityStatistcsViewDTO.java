package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingQuantityStatistcsViewDTO implements Serializable {
    private static final long serialVersionUID = -6880389934379769604L;

    private List<ReadingQuantityStatistcsDTO> readingQuantityStatistcsDTOS = new ArrayList<>();
}
