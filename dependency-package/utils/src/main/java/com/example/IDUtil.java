package com.example;

import org.bouncycastle.util.Strings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class IDUtil {
    public static String generate12NumId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddssSSS");
        String id = format.format(new Date());
        String substring = id.substring(0,8);
        String substring2 = id.substring(9);
        return substring+substring2;
    }

    public static String generateYMDHMS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String id = format.format(new Date());
        return id;
    }

    public static String getRandString(int length)
    {
        String charList = "0123456789"; // ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
        String rev = "";
        Random f = new Random();
        for(int i=0;i<length;i++)
        {
            rev += charList.charAt(Math.abs(f.nextInt())%charList.length());
        }
        return rev;
    }


    public static void main(String[] args) {
        System.out.println(getRandString(4));
    }
}
