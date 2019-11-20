package com.sgcc.Enum;

public enum JobEnum {
    未完成("未完成"),已完成("已完成");
    //成员变量
    private String name;

    //构造方法
    private JobEnum(String name)
    {
        this.name=name;
    }
}
