package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListSubmitDTO implements Serializable {

    private static final long serialVersionUID = -3651963881700598173L;
    private List<UserSubmitDTO> user_list;
}