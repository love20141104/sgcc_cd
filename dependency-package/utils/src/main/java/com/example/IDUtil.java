package com.example;

import org.bouncycastle.util.Strings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IDUtil {
    public static String generate12NumId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddssSSS");
        String id = format.format(new Date());
        String substring = id.substring(0,8);
        String substring2 = id.substring(9);
        return substring+substring2;
    }

    public static void main(String[] args) {
        System.out.println(generate12NumId());
    }
}
