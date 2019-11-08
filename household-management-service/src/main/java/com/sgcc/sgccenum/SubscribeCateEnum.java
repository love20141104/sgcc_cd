package com.sgcc.sgccenum;

public enum SubscribeCateEnum {
    sub_bill("月度账单")
    ,sub_pay("充值缴费")
    ,sub_arrears("欠费提醒")
    ,sub_coulometric_analysis("电量分析")
    ,sub_power("停送电信息");

    private String name;

    private SubscribeCateEnum(String name)
    {
        this.name=name;
    }
}
