package com.Web_CSGO.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lianggs
 * @version 2017年06月26日 16时02分
 */
public final class DateUtil {
    private DateUtil() {
    }

    /**
     * 秒的毫秒数
     */
    public final static long SECOND_MILLIS = 1000;

    /**
     * 分钟的毫秒数
     */
    public final static long MIN_MILLIS = 60 * SECOND_MILLIS;

    /**
     * 小时的毫秒数
     */
    public final static long HOUR_MILLIS = 60 * MIN_MILLIS;

    /**
     * 天的毫秒数
     */
    public final static long DAY_MILLIS = 24 * HOUR_MILLIS;

    /**
     * 格式:yyyy-MM-dd
     */
    public static final String PATTERN_YYYY_MM = "yyyy-MM";

    /**
     * 格式:yyyy-MM-dd
     */
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 格式:yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式:yyyy-MM-dd HH:mm
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 格式:yyyyMMddHHmm
     */
    public static final String PATTERN_YYYYMMDDHHMM = "yyyyMMddHHmm";

    /**
     * 格式:HH:mm
     */
    public static final String PATTERN_HHMM = "HH:mm";

    /**
     * 格式:yyyyMMddHHmmss
     */
    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static Date getNow() {
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();
        return now;
    }

//    public static Date getNow() {
//        Date d = new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        return new Date();
//    }


    private static SimpleDateFormat format = new SimpleDateFormat(PATTERN_YYYY_MM_DD_HH_MM_SS);

    private static SimpleDateFormat getSimpleDateFormat(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            return format;
        }
        return new SimpleDateFormat(pattern);
    }

    /**
     * 格式化日期
     *
     * @param currentTimeMillis
     * @return
     */
    public static final String format(long currentTimeMillis) {
        Date date = new Date(currentTimeMillis);
        return format(date, PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static final String format(Object date) {
        return format(date, PATTERN_YYYY_MM_DD);
    }

    /**
     * 格式化日期
     *
     * @param timeMillis
     * @param pattern
     * @return
     */
    public static final String format(long timeMillis, String pattern) {
        if (pattern == null) {
            return format(timeMillis);
        }
        return getSimpleDateFormat(pattern).format(new Date(timeMillis));
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Object date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        return getSimpleDateFormat(pattern).format(date);
    }

    /**
     * 字符串转换为日期
     *
     * @param dateStr
     * @return
     */
    public static final Date stringToDate(String dateStr) {
        return stringToDate(dateStr, PATTERN_YYYY_MM_DD);
    }

    /**
     * 字符串转换为日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static final Date stringToDate(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        Date date = null;
        try {
            date = getSimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static final String stringToString(String dateStr, String dateFormat, String stringFormat) {
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = PATTERN_YYYY_MM_DD_HH_MM_SS;
        }

        if (StringUtils.isEmpty(stringFormat)) {
            stringFormat = PATTERN_YYYY_MM_DD_HH_MM_SS;
        }
        Date date = stringToDate(dateStr, dateFormat);
        return format(date, stringFormat);
    }

    /**
     * 时间差
     *
     * @param startTimeMillis
     * @param endTimeMillis
     * @return
     */
    public static long getMinuteBetween(long startTimeMillis, long endTimeMillis) {
        Date startDate = new Date(startTimeMillis);
        Date endDate = new Date(endTimeMillis);
        return getMinuteBetween(startDate, endDate);
    }

    /**
     * 间隔分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final long getMinuteBetween(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / MIN_MILLIS;
    }

    /**
     * 间隔小时数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final long getHourBetween(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / HOUR_MILLIS;
    }

    /**
     * 间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final long getDayBetween(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / DAY_MILLIS;
    }

    /**
     * 间隔月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        return n;
    }

    /**
     * 获取某年某月最大天数
     *
     * @param dateStr
     * @return
     */
    public static final Integer getActualMaximum(String dateStr) {
        Date date = DateUtil.stringToDate(dateStr, "yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        Date startDate = stringToDate("2018-03-16 10:00:00", PATTERN_YYYY_MM_DD_HH_MM_SS);
        System.out.println(getHourBetween(new Date(), startDate));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime(String pattern) {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat(pattern);
        String s = outFormat.format(now);
        return s;
    }

    /**
     *今天剩余秒数
     */
    public static int gettodayss() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        int minuteBetween = (int) ((instance.getTimeInMillis() - System.currentTimeMillis()) / 1000);
        return minuteBetween;
    }

    public static final String dateToString(Date date){
        String dateStr = "";
        if(null != date){
            //得到年份
            String nian = date.getYear()+1900+"";
            //得到月份
            String yue = date.getMonth()+1+"";
            //得到日期
            String ri = date.getDate()+"";
            System.out.println(date.getYear()+1900);
            System.out.println(date.getMonth()+1);
            System.out.println(date.getDate());
            dateStr = nian+"年"+yue+"月"+ri+"日";
        }
        return dateStr;
    }

    /**
     * 判断某个时间是否小于今天，只比较天数，不比较时间
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean CompareTheSizeOfTime(Date date) throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

       Date today = sf.parse(sf.format(new Date()));
       Date  date1= sf.parse(sf.format(date));
       if( date1.getTime()< today.getTime()){
           return true;
         }else{
           return false;
       }

    }
}
