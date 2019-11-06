package com.sgcc.sgccenum;

public enum DateRangeEnum {
    YEAR("YEAR"),MONTH("MONTH"),WEEK("WEEK");

    private String name;

    private DateRangeEnum(String name)
    {
        this.name=name;
    }

}
