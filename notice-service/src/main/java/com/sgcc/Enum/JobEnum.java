package com.sgcc.Enum;

public enum JobEnum {
    UNFINISHED("未完成"),FINISHED("已完成");
    //成员变量
    private String name;

    //构造方法
    private JobEnum(String name)
    {
        this.name=name;
    }
}
