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
public class PaymentTimesDTO implements Serializable {


    private static final long serialVersionUID = 3704569364156205841L;
    private double total;

    private String date;

}
