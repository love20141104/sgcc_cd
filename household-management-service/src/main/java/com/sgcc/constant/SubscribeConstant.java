package com.sgcc.constant;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.HashMap;

public class SubscribeConstant {
    public static final HashMap<String,String> SUBSCRIBE_CATEGORY = new HashMap<String,String>(
    ){
        private static final long serialVersionUID = 1957427526412979567L;

        {
        put("月度账单","sub_bill");
        put("充值缴费","sub_pay");
        put("欠费提醒","sub_arrears");
        put("电量分析","sub_coulometric_analysis");
        put("停送电信息","sub_power");
    }};
}
