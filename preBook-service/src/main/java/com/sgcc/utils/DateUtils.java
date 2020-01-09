package com.sgcc.utils;

import com.example.Utils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    /**
     * 获取今天还剩多少秒
     *
     * @return
     */
    public static long getSeconds() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }


    /**
     * 计算两个日期差几天
     *
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public static long daysBetweenTwoDate(Date dateFrom, Date dateTo) {
        dateFrom.setHours(0);
        dateFrom.setMinutes(0);
        dateFrom.setSeconds(0);
        dateTo.setHours(0);
        dateTo.setMinutes(0);
        dateTo.setSeconds(0);
        long from = dateFrom.getTime();
        long to = dateTo.getTime();
        return ((to - from) / (1000 * 60 * 60 * 24L));
    }

    /**
     * 拆分当天日期
     * @param date
     * @param repl
     * @return
     */
    public static Map<String,Date> splitDate(String date, String time,String repl) {
        Map<String,Date> dates = new LinkedHashMap<>();
        String[] times = date.split(repl);

        System.out.println("开始时间："+time+" "+times[0]+":00");
        Date startDate = Utils.GetDate(time+" "+times[0]+":00");
        Date endDate = Utils.GetDate(time+" "+times[1]+":00");
        dates.put("startDate",startDate);
        dates.put("endDate",endDate);
        return dates;
    }

    /**
     * 拆分明天日期
     * @param date
     * @param repl
     * @return
     */
//    public static Map<String,Date> splitDate(String date,String repl) {
//        Map<String,Date> dates = new LinkedHashMap<>();
//        String[] times = date.split(repl);
//
//        System.out.println("开始时间："+Utils.GetTimeForYMD(tomorrow(new Date()))+" "+times[0]+":00");
//        Date startDate = Utils.GetDate(Utils.GetTimeForYMD(tomorrow(new Date()))+" "+times[0]+":00");
//        Date endDate = Utils.GetDate(Utils.GetTimeForYMD(tomorrow(new Date()))+" "+times[1]+":00");
//        dates.put("startDate",startDate);
//        dates.put("endDate",endDate);
//        return dates;
//    }


    public static Date tomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    /**
     * 拼装日期
     * @returna
     */
    public static String assembleDate(Date start, Date end) {
        String ymd = Utils.GetTimeForYMD(start);
        String startDate = Utils.GetTimeForHM(start);
        String endDate = Utils.GetTimeForHM(end);
        String finalDate = ymd +" "+ startDate +"~"+ endDate;
        return finalDate;
    }


    public static String addDate(String day, int x){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
        Date date = null;
        try
        {
            date = format.parse(day);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (date == null) return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);//24小时制
        date = cal.getTime();
        cal = null;
        return format.format(date);
    }


    public static void main(String[] args){
        String date1 = "2009-03-23 08:00:00";//指定时间
        String date2= addDate(date1,1);//加1小时方法
        System.out.println("after:"+date2);

    }


}
