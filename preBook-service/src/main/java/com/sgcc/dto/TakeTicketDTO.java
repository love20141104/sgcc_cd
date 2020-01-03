package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeTicketDTO implements Serializable {

    private static final long serialVersionUID = -415700683700751480L;

    private String hallId;
    private String phone;

}
