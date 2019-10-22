package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RecordViewDTO implements Serializable {

    private static final long serialVersionUID = -6610419557689567757L;

    private List<PaymentChannelDTO> paymentChannelDTOS = new ArrayList<>();

    private List<RecordTypeDTO> recordTypeDTOS = new ArrayList<>();


    public RecordViewDTO(List<PaymentChannelDTO> paymentChannelDTOS, List<RecordTypeDTO> recordTypeDTOS) {
        this.paymentChannelDTOS = paymentChannelDTOS;
        this.recordTypeDTOS = recordTypeDTOS;
    }
}
