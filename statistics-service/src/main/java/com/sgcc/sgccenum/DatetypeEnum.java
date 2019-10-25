package com.sgcc.sgccenum;

public enum DatetypeEnum {
        MONTH("MONTH"),WEEK("WEEK"),DAY("DAY"),HOUR("HOUR");
    //成员变量
    private String name;

    //构造方法
    private DatetypeEnum(String name)
    {
        this.name=name;
    }

    public static void main(String[] args) {
        System.out.println(DatetypeEnum.DAY);
    }
}
