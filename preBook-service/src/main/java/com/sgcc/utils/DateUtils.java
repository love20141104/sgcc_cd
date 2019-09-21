package com.sgcc.utils;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

}
