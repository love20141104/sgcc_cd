package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ConsumerManagerGroupDTO implements Serializable {
    private static final long serialVersionUID = -1347736123969047062L;

    private List<ConsumerManagerDTO> consumerManagerDTOS = new ArrayList<>();
    private Map<String,List<ConsumerManagerDTO>> gourpMap = new HashMap<>();

    public ConsumerManagerGroupDTO(List<ConsumerManagerDTO> consumerManagerDTOS){
        this.consumerManagerDTOS = new ArrayList<>(consumerManagerDTOS);
    }
    public ConsumerManagerGroupDTO(Map<String,List<ConsumerManagerDTO>> hmap){
        this.gourpMap = new HashMap<>(hmap);
    }
}
