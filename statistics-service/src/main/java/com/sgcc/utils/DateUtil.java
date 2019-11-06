package com.sgcc.utils;

import com.example.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    //今天00:00:00
    public static Date getToday0() {
        Long time = System.currentTimeMillis();  //当前时间的时间戳
        long zero = time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        Date date = new Date(zero);
        return date;
    }
    //今天23:59:59
    public static Date getToday24() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),23,59,59);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }


    //昨天00:00:00
    public static Date getYesterday0() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-1,0,0,0);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //昨天23:59:59
    public static Date getYesterday24() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-1,23,59,59);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //上周今天00:00:00
    public static Date getLastweek0() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-7,0,0,0);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //上周今天23:59:59
    public static Date getLastweek24() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-7,23,59,59);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //前n 月 1号 00:00:00
    public static Date getnMonthFirst(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -n);
        //设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //前n 月 最后一天 23:59:59
    public static Date getnMonthLast(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -n+1);
        //设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH,0);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }


    //今天 n:00:00
    public static Date getnHourFirst(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,n);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //今天 n:59:59
    public static Date getnHourLast(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,n);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //本周星期 n 00:00:00
    public static Date getnweekFirst(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,n);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //本周星期 n 23:59:59
    public static Date getnweekLast(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,n);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //第 n 天前 00:00:00
    public static Date getndayFirst(int n) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-n);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }

    //第 n 天前 23:59:59
    public static Date getndayLast(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-n);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        long tt = calendar.getTime().getTime();
        Date date = new Date(tt);
        return date;
    }
    public static void main(String[] args) {
        /*System.out.println(Utils.GetTime(getnMonthFirst(0)));
        System.out.println(Utils.GetTime(getnMonthLast(0)));*/
        /*for (int i = 0; i <=29 ; i++) {
            if(null!=getndayFirst(i)){
                System.out.println(Utils.GetTime(getndayFirst(i)));
                System.out.println(Utils.GetTime(getndayLast(i)));
            }
        }*/
        System.out.println(new Date());
    }
}

