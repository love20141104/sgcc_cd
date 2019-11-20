package com.sgcc.Enum;

public enum RepairProgressEnum {
    ACCEPTED("已受理"),RUSHREPAIR("抢修中"),FINISHED("已完成");
    //成员变量
    private String name;

    //构造方法
    private RepairProgressEnum(String name)
    {
        this.name=name;
    }
}
