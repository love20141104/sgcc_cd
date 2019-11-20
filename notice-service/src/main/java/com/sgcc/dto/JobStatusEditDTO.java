package com.sgcc.dto;

import com.google.common.base.Strings;
import com.sgcc.Enum.JobEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import org.checkerframework.common.value.qual.ArrayLen;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobStatusEditDTO implements Serializable {

    private static final long serialVersionUID = -5462387477816617378L;

    private JobEnum jobEnum;



}
