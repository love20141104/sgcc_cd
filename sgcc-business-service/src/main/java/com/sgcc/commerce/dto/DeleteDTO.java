package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDTO implements Serializable {
    private static final long serialVersionUID = -6483727644176515560L;
    private List<String> Ids;
}
