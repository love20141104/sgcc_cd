package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ConsumerManagerListDTO implements Serializable {

    private List<ConsumerManagerDTO> consumerManagerDTOS = new ArrayList<>();
    private static final long serialVersionUID = -1347736123969047062L;

    public ConsumerManagerListDTO(List<ConsumerManagerDTO> consumerManagerDTOS){
        this.consumerManagerDTOS = new ArrayList<>(consumerManagerDTOS);
    }


}
