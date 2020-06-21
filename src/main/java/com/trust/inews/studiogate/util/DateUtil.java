package com.trust.inews.studiogate.util;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: hehaijin
 * @Date: 2019/1/18 15:26
 * @Version 1.0.0
 * @Description: 时间相关工具类
 */
public class DateUtil {

    public static final String CN_FORMAT_PATTERN = "yyyy年MM月dd日";

    public static final String EN_FORMAT_PATTERN = "yyyy-MM-dd";

    public static final String EN_FORMAT_MONTH = "yyyy-MM";

    public static final String EN_SECONDS_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String T_EN_SECONDS_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String EN_SPRIT_FORMAT_PATTERN = "yyyy/MM/dd";

    /**
     * String to Date ，当字符串为空或者格式错误时，返回null
     *
     * @param dateStr       字符串
     * @param formatPattern 格式化规则
     * @return
     */
    public static Date stringToDate(String dateStr, String formatPattern) {
        if (dateStr == null || StringUtils.isBlank(dateStr)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(formatPattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr.trim());
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * String list to Date List
     *
     * @param dateStrList   字符串集合
     * @param formatPattern date集合
     * @return
     */
    public static List<Date> stringToDate(List<String> dateStrList, String formatPattern) {
        List<Date> dateList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat(formatPattern);
        for (String str : dateStrList) {
            if (str == null || StringUtils.isBlank(str)) {
                dateList.add(null);
                continue;
            }
            Date date = null;
            try {
                date = dateFormat.parse(str.trim());
            } catch (ParseException e) {
                dateList.add(null);
                continue;
            }
            dateList.add(date);
        }
        return dateList;
    }

    /**
     * 日期转字符串
     *
     * @param date          需要转换的日期
     * @param formatPattern 转换规则
     * @return
     */
    public static String dateToString(Date date, String formatPattern) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(formatPattern);
        String dateStr = format.format(date);
        return dateStr;
    }

    /**
     * 比较两日期相差天数
     *
     * @param date        日期
     * @param anotherDate 另一个日期
     * @return
     */
    public static int dateDiffer(Date date, Date anotherDate) {
        return Math.abs((int) (date.getTime() - anotherDate.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * @return 当前日期 （字符串形式）
     * @Desc 获取当前日期
     */
    public static String getCurDate() {
        StringBuilder stringBuilder = new StringBuilder();
        // 获取年 月 日
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        return stringBuilder.append(year).append("-").append(month).append("-").append(day).toString().trim();
    }

    /**
     * String 转 Calender
     *
     * @param str
     * @return
     */
    public static Calendar stringTurnCalender(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(EN_FORMAT_PATTERN);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


    /**
     * 计算两个时间相差多少个年
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int yearsBetween(Calendar startDate, Calendar endDate) {
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }

    /**
     * 计算两个时间相差多少个月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int monthsBetween(Calendar startDate, Calendar endDate) {
        int result = yearsBetween(startDate, endDate) * 12 + endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
        return Math.abs(result);
    }


    /**
     * 获取当天的开始时间
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取昨天的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    /**
     * 获取昨天的结束时间
     *
     * @return
     */
    public static Date getEndDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取本月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    /**
     * 获取本月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 把秒数转为 hh:mm:ss 格式
     *
     * @param seconds
     * @return
     */
    public static String secToTime(int seconds) {
        int temp;
        StringBuffer sb = new StringBuffer();
        temp = seconds / 3600;
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");

        temp = seconds % 3600 / 60;
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");

        temp = seconds % 3600 % 60;
        sb.append((temp < 10) ? "0" + temp : "" + temp);
        return sb.toString();
    }

    /**
     * 把00：00：00：00时间格式转为秒
     *
     * @param time
     * @return
     */
    public static Integer timeToSeconds(String time) {
        if (StringUtils.isBlank(time)){
            return 0;
        }
        String[] times = time.split(":");
        Integer secondCount = 0;
        int length = times.length;
        if (length <= 0) {
            return 0;
        }
        int second = Integer.parseInt(times[length - 1]);
        secondCount += second;
        if (length <= 1) {
            return secondCount;
        }
        int minute = Integer.parseInt(times[length - 2]);
        secondCount += minute * 60;
        if (length <= 2) {
            return secondCount;
        }
        int hour = Integer.parseInt(times[length - 3]);
        secondCount += hour * 60 * 60;
        if (length <= 3) {
            return secondCount;
        }
        int day = Integer.parseInt(times[length - 4]);
        secondCount += day * 24 * 60 * 60;
        if (length <= 4) {
            return secondCount;
        }
        return secondCount;
    }
}



