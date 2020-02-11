package com.sgcc.enums;

import lombok.Getter;

@Getter
public enum RushRepairProgressEnum {

    ACCEPTED(0,"已受理"),
    APPOINTEES(1,"已指派抢修人员"),
    RUSHREPAIR(2,"紧张抢修中"),
    FINISHED(3,"抢修已完成，恢复供电");

    private Integer index;
    private String name;

    private RushRepairProgressEnum(Integer key,String val){
        this.index = key;
        this.name = val;
    }

    public static String getVal(Integer index) {
        RushRepairProgressEnum[] progressEnums = values();
        for (RushRepairProgressEnum progressEnum : progressEnums) {
            if (progressEnum.getIndex().equals(index)) {
                return progressEnum.getName();
            }
        }
        return null;
    }

}
