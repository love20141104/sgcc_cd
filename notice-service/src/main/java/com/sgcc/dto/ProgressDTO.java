package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDTO implements Serializable {

    private static final long serialVersionUID = 5171964147355278977L;
    private String day;
    private String time;
    private Integer progressState;
    private String title;
    private String rushRepairPerson;
    private String causeOfFailure;
    private List<String> photo;

}
