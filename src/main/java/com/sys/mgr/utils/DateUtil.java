package com.sys.mgr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liangtao on 2018/4/6.
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private final static long ONE_DAY = 24 * 60 * 60 * 1000;

    private final static DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDate(long days) {
        return new Date(System.currentTimeMillis() - days * ONE_DAY);
    }

    private static final ThreadLocal<SimpleDateFormat> timeFormat = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String getStrDataDay(Integer day) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -day);
        Date date = calendar.getTime();
        return sdf2.format(date);
    }

    public static String getStrDataDay(Date date, Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -day);
        return sdf2.format(calendar.getTime());
    }

    /**
     *
     * @param strDate
     * @param day   yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getStrDataDay(String strDate, Integer day) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sdf2.parse(strDate));
        calendar.add(Calendar.DATE, -day);
        return sdf2.format(calendar.getTime());
    }

    public static String getStringDate(Date date) {
        if(date == null){
            return "";
        }
        return timeFormat.get().format(date);
    }

    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 把 XMLGregorianCalendar对象类型转换成java.uitl.Date类型
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public static Date convertToDate(XMLGregorianCalendar cal) throws Exception {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     * Data转String
     *
     * @param date
     * @param data_format
     * @return String
     */
    public static String toString(Date date, String data_format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(data_format);
        return df.format(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param date 日期格式必须为 yyyy-MM-dd HH:mm:ss 或者 Tue May 12 11:20:22 CST 2015 格式，不然报错
     * @return
     */
    public static Date toDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        Date dt = null;
        try {
            dt = sdf.parse(date);
        } catch (Exception e) {
            try {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dt = sdf1.parse(date);
            } catch (Exception e2) {
                LOGGER.error("字符串转换成日期", e);
            }
        }

        return dt;
    }

    /**
     * 计算距当前日期前后的日期 例如传入整型5， 意为前5天的日期 ;如传入整型-5， 意为后5天的日期
     *
     * @param toDay 距今的天数
     * @return
     */
    public static Date getDateAround(int toDay, int hour, int minute, int second) {

        // 转换参数, 传入的正数转换为负数
        if (toDay > 0) {
            toDay = (0 - toDay);
        }

        Calendar strDate = Calendar.getInstance();

        strDate.set(Calendar.HOUR_OF_DAY, hour);
        strDate.set(Calendar.MINUTE, minute);
        strDate.set(Calendar.SECOND, second);

        // 日期减 如果不够减会将月变动
        strDate.add(Calendar.DATE, toDay);

        return strDate.getTime();
    }


    /**
     * @param oldDate     旧的日期
     * @param currentDate 当前日期
     */
    public static long dateDiff(Date oldDate, Date currentDate) {

        String format = "yyyy-MM-dd HH:mm:ss";
        String old = toString(oldDate, format);
        String now = toString(currentDate, format);

        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long diff = 0;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(now).getTime() - sd.parse(old).getTime();
        } catch (Exception e) {
            LOGGER.error("dateDiff", e);
        }

        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long diffSeconds = diff / 1000 % 60;//计算差多少秒//输出结果

        return diffSeconds + diffMinutes * 60 + diffHours * 60 * 60 + diffDays * 24 * 60 * 60;
    }


    /**
     * 得到应用服务器当前日期 按照指定的格式返回。
     *
     * @param pattern 格式类型，通过系统常量中定义，如：CrmConstants.DATE_FORMAT_8
     * @return
     */
    public static String formatDate(String pattern, Integer m) {

        Date date = new Date();
        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);
        String str = dateFormator.format(date);

        return str;
    }

    /**
     * 转换输入日期 按照指定的格式返回。
     *
     * @param date
     * @param pattern 格式类型，通过系统常量中定义，如：CrmConstants.DATE_FORMAT_8
     * @return
     */
    public static String formatDate(Date date, String pattern) {

        if (date == null)
            return "";

        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);
        String str = dateFormator.format(date);

        return str;
    }

    /**
     * 日期操作，当前日期月份操作
     *
     * @param month
     * @return yyyy-MM
     */

    public static String convertDateFormat(int month) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = new Date();

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, -month);//根据正数减去
        Date dt1 = rightNow.getTime();

        return sdf.format(dt1);
    }
    /**
     * 转换输入日期 按照指定的格式返回。
     *
     * @param date
     * @param pattern 格式类型，通过系统常量中定义，如：CrmConstants.DATE_FORMAT_8
     * @param loc     locale
     * @return
     */
    public static String formatDate(Date date, String pattern, Locale loc) {
        if (pattern == null || date == null) {
            return "";
        }
        String newDate = "";
        if (loc == null)
            loc = Locale.getDefault();
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
            newDate = sdf.format(date);
        }
        return newDate;
    }

    /**
     * 将字符时间从一个格式转换成另一个格式。时间的格式，最好通过系统常量定义。 如：
     * String dateStr = "2006-10-9 12:09:08";
     * DateFormatUtils.convertDateFormat(dateStr,
     * CrmConstants.DATE_TIME_FORMAT,
     * CrmConstants.DATE_FORMAT_8);
     *
     * @param patternFrom 格式类型，通过系统常量中定义，如：CrmConstants.DATE_FORMAT_8
     * @param patternTo   格式类型，通过系统常量中定义，如：CrmConstants.DATE_FORMAT_8
     * @return
     */
    public static String convertDateFormat(String dateStr, String patternFrom, String patternTo) {

        if (dateStr == null || patternFrom == null || patternTo == null) {
            return "";
        }

        String newDate = "";

        try {

            Date dateFrom = parseStrToDate(dateStr, patternFrom);
            newDate = formatDate(dateFrom, patternTo);

        } catch (Exception e) {
        }

        return newDate;
    }


    /**
     * 将时间串按照默认格式CrmConstants.DATE_FORMAT，格式化成Date。
     *
     * @param dateStr
     * @return
     */
    public static Date parseStrToDateByFormat(String dateStr, String formatStr) {

        if (null == dateStr || "".equals(dateStr))
            return null;

        SimpleDateFormat dateFormator = new SimpleDateFormat(formatStr);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        return tDate;
    }

    /**
     * 将时间串按照指定格式，格式化成Date。
     *
     * @param pattern 格式类型，通过系统常量中定义，如：DateFormatConstants.DATE_FORMAT_8
     * @return
     */

    public static Date parseStrToDate(String dateStr, String pattern) {
        if (null == dateStr || "".equals(dateStr))
            return null;

        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        return tDate;
    }

    /**
     * 按时间格式相加：
     * 输入要相加的时间基点（字符串型或时间类型），相加的时长（整型），格式（"year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周）
     * 输出相加后的时间（字符串型或时间类型）
     *
     * @param dateStr
     * @param pattern
     * @param step
     * @param type    "year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周
     *                通过常量DateFormatUtils.YEAR等来设置.
     * @return
     */
    public static String addDate(String dateStr, String pattern, int step, String type) {
        if (dateStr == null) {
            return dateStr;
        } else {
            Date date1 = DateUtil.parseStrToDate(dateStr, pattern);

            Locale loc = Locale.getDefault();
            Calendar cal = new GregorianCalendar(loc);
            cal.setTime(date1);

            if (DateUtil.WEEK.equals(type)) {

                cal.add(Calendar.WEEK_OF_MONTH, step);

            } else if (DateUtil.YEAR.equals(type)) {

                cal.add(Calendar.YEAR, step);

            } else if (DateUtil.MONTH.equals(type)) {

                cal.add(Calendar.MONTH, step);

            } else if (DateUtil.DAY.equals(type)) {

                cal.add(Calendar.DAY_OF_MONTH, step);

            } else if (DateUtil.HOUR.equals(type)) {

                cal.add(Calendar.HOUR, step);

            } else if (DateUtil.MINUTE.equals(type)) {

                cal.add(Calendar.MINUTE, step);

            } else if (DateUtil.SECOND.equals(type)) {

                cal.add(Calendar.SECOND, step);

            }

            return DateUtil.formatDate(cal.getTime(), pattern);
        }
    }

    /**
     * 按时间格式相减：
     * 输入要相加的时间基点（字符串型或时间类型），相加的时长（整型），格式（"year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周）
     * 输出相加后的时间（字符串型或时间类型）
     *
     * @param dateStr
     * @param pattern
     * @param step
     * @param type    "year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周
     * @return
     */
    public static String minusDate(String dateStr, String pattern, int step, String type) {

        return addDate(dateStr, pattern, (0 - step), type);

    }

    /**
     * 日期增加天数
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        if (date == null) {
            return date;
        } else {
            Locale loc = Locale.getDefault();
            Calendar cal = new GregorianCalendar(loc);
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, days);
            return cal.getTime();
        }
    }

    /**
     * 日期增加小时
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        if (date == null) {
            return date;
        } else {
            Locale loc = Locale.getDefault();
            Calendar cal = new GregorianCalendar(loc);
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY, hour);
            return cal.getTime();
        }
    }

    public static int getDays(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;
        else
            return (int) ((date2.getTime() - date1.getTime()) / 0x5265c00L);
    }

    /**
     * 日期比较大小
     *
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     */
    public static int compareDate(String dateStr1, String dateStr2, String pattern) {

        Date date1 = DateUtil.parseStrToDate(dateStr1, pattern);
        Date date2 = DateUtil.parseStrToDate(dateStr2, pattern);

        return date1.compareTo(date2);

    }


    /**
     * 根据日期获取到是星期几
     * 此日期的开始标识位是星期日，
     * 星期日 是 1
     * 星期一 是 2 的计算方式往后推算
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取两个日期之间的日期LIST
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> getDateList(Date beginDate, Date endDate){
        List<String> dateList = new ArrayList<String>();
        if (beginDate == null || endDate == null || beginDate.after(endDate)) {
            return dateList;
        }
        dateList.add(DateUtil.formatDate(beginDate,"yyyy-MM-dd"));
        while (getDateYmd(beginDate).before(getDateYmd(endDate))) {
            beginDate = DateUtil.addDay(beginDate,1);
            dateList.add(DateUtil.formatDate(beginDate,"yyyy-MM-dd"));
        }
        return dateList;
    }

    /**
     * 日期格式化（yyyy-MM-dd）
     * @param sourceDate
     * @return
     */
    public static Date getDateYmd(Date sourceDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    public static boolean checkDateFormat(String sourceDate, String format) {
        if (sourceDate == null) {
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(sourceDate);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 获取上个月的年月yyyy-MM
     * @return
     */
    public static String getLastMonth(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date ());
        rightNow.add(Calendar.MONTH, -1);//日期-1个月
        Date lastMontch = rightNow.getTime();
        return sdf.format(lastMontch);
    }

    /**
     * 两个日期相差天数
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysBetween(Date fDate, Date oDate) {
        int days = (int) ((oDate.getTime() - fDate.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 日期比较大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) throws Exception {
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    public static String getDateWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        return year+"-"+weekOfMonth;
    }


    public static String getMonthFirstDay(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        System.out.println("本月第一天 " + firstday );
        return firstday;
    }

    public static String getMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String firstday;
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        firstday = format.format(cale.getTime());
        System.out.println("本月 " + firstday );
        return firstday;
    }

    public static String getMonthLastDay(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastday;
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月最后一天分别是 ： " + lastday);
        return lastday;
    }

    public  static void main(String []args) {
        System.out.println(getMonth(new Date()));
    }


    public final static String YEAR = "year";

    public final static String MONTH = "month";

    public final static String DAY = "day";

    public final static String HOUR = "hour";

    public final static String MINUTE = "minute";

    public final static String SECOND = "second";

    public final static String WEEK = "week";

}


