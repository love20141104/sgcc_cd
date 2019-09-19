package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class PrebookInfoSaveDTO implements Serializable {

    private static final long serialVersionUID = 1530545234570336395L;
    private List<String> ids = new ArrayList<>();

    public PrebookInfoSaveDTO(List<String> ids){
        this.ids = new ArrayList<>(ids);
    }





}
