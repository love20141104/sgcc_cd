package com.sgcc.Enum;

public enum RepairProgressEnum {
    已受理("已受理"),抢修中("抢修中"),已完成("已完成");
    //成员变量
    private String name;

    //构造方法
    private RepairProgressEnum(String name)
    {
        this.name=name;
    }
}
