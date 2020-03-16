/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.Web_CSGO.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 默认时间格式
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }
    public static int getHour() {
    	String hour= formatDate(new Date(), "HH");
    	return Integer.parseInt(hour);
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false)
     *
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }



    /**
     * 时间比较，如果s>=e 返回true 否则返回false)
     *
     */
    public static boolean compareDateTime(String s, String e) {
        Date date_s = parseTime(s);
        Date date_e = parseTime(e);
        if (date_s == null || date_e == null) {
            return false;
        }
        return date_s.getTime() >= date_e.getTime();
    }

    /**
     * 时间比较，如果s>=e 返回true 否则返回false)
     *
     */
    public static boolean compareOnlyTime(String s, String e) {
        Date date_s = parseOnlyTime(s);
        Date date_e = parseOnlyTime(e);
        if (date_s == null || date_e == null) {
            return false;
        }
        return date_s.getTime() >= date_e.getTime();
    }

    /**
     * 格式化日期
     */
    public static Date parseDate(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     */
    public static Date parseTimeMinutes(String date) {
        return parse(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 格式化日期
     */
    public static Date parseOnlyTime(String time) {
        return parse(time, "HH:mm:ss");
    }

    /**
     * 格式化日期
     */
    public static Date parseTime(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 格式化日期
     */
    public static Date parseAllTime(String date) {
    	if(date.length()==8) {
    		return parseOnlyTime(date);
    	}else if(date.length()==10){
    		return parseDate(date);
    	}else if(date.length()==16){
    		return parseTimeMinutes(date);
    	}else if(date.length()==16){
    		return parseTimeMinutes(date);
    	}else if(date.length()==19){
    		return parseTime(date);
    	}else {
    		return null;
    	}
    }

    /**
     * 格式化日期
     */
    public static Date parse(String date, String pattern) {
        try {
            return DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期转换为Timestamp
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {

        return getAfterDayDate(days, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days, String patter) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(patter);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 根据日期转抱成星期几
     * @param day 如：2019-08-21
     * @return
     */
    public static String getWeekByDay(String day){
        Date date = DateUtil.parse(day, "yyyy-MM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 两个日期相减,返回秒
     */
    public static Long getsecond(Date d1, Date d2) {
    	Long miao=(d2.getTime()-d1.getTime())/1000;//除以1000是为了转换成秒
    	return miao;
    }
    /**
     * 两个日期相减,返回天
     */
    public static Long minusDay(Date d1, Date d2) {
    	Long miao=(d2.getTime()-d1.getTime())/(24*60*60*1000);
    	return miao;
    }

    /**
     * 把Date 格式化为ISO8601标准时间字符串
     * @param date
     * @return 返回格式 ： 2019-01-31T16:24:18+08:00
     */
    public static String formatISO8601(Date date){
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        //TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    /**
     *
     * @param dateStr 格式 ： 2019-01-31 16:24:18+08
     * @return
     */
    public static Date parseISO8601(String dateStr){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX").parse(dateStr);
            return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param dateStr 格式 ： 2019-01-31 16:24:18+08
     * @return
     */
    public static Date parseISO8601(String dateStr, String patter){
        try {
            Date date = new SimpleDateFormat(patter).parse(dateStr);
            return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

	/**
     * 秒转换为指定格式的日期
     * @param second
     * @param patten
     * @return
     */
    public static String secondToDate(Object second) {
        int t= Integer.parseInt(second.toString());
        String H=(t/60/60)<10?("0"+t/60/60):""+t/60/60;
        String m=(t/60%60)<10?("0"+t/60%60):""+t/60%60;
        String s=t%60%60<10?("0"+t%60%60):""+t%60%60;
        String time=H+":"+m+":"+s;
        return time;
    }

    /**
     * 获取两个日期字符串之间的日期集合
     * @param startTime:String
     * @param endTime:String
     * @return list:yyyy-MM-dd
     */
    public static List<String> getBetweenDate(String startTime, String endTime){
        return getBetweenDate(startTime, endTime, "yyyy-MM-dd");
    }
    public static List<String> getBetweenDate(String startTime, String endTime, String patter){
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()){
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取某月的所有天数集合
     * @param month yyyy-MM
     * @return list  dd
     */
    public static List<String> getDayListOfMonth(String month) {
        Date date = parse(month, "yyyy-MM");
        if (date == null){
            return null;
        }
        List<String> list = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String tempDay = String.valueOf(i);
            if(i<10) {
                tempDay = "0"+ tempDay;
            }
            list.add(tempDay);
        }
        return list;
    }

    /**
     * 获取一自然年的所有月份集合
     * @return
     */
    public static List<String> getMonthListOfYear() {
        List<String> list = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance();
        int monthCount = aCalendar.getActualMaximum(Calendar.MONTH);
        for (int i = 1; i <= monthCount + 1; i++) {
            String month = String.valueOf(i);
            if(i<10) {
                month = "0"+ month;
            }
            list.add(month);
        }
        return list;
    }

    public static void main(String[] args) {
        //List<String> list = getDayListOfMonth("2020-02");
        List<String> list = getMonthListOfYear();
        list.forEach(item ->{
            System.out.println(item);
        });
    }

}
