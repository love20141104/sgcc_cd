package com.example.constant;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PrebookStartTimeConstants {
    public static final String TIME_09_10 = "上午09:00~10:00";
    public static final String TIME_10_11 = "上午10:00~11:00";
    public static final String TIME_11_12 = "上午11:00~12:00";
    public static final String TIME_12_13 = "下午12:00~13:00";
    public static final String TIME_13_14 = "下午13:00~14:00";
    public static final String TIME_14_15 = "下午14:00~15:00";
    public static final String TIME_15_16 = "下午15:00~16:00";
    public static final String TIME_16_17 = "下午16:00~17:00";

    public static final List<String> TIME_LIST = new ArrayList<String>(){{
                add(TIME_09_10);
                add(TIME_10_11);
                add(TIME_11_12);
                add(TIME_12_13);
                add(TIME_13_14);
                add(TIME_14_15);
                add(TIME_15_16);
                add(TIME_16_17);
    }};

}
