package com.gdcable.epm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期的实用方法类.
 *
 */
public class DateUtil
{
	/**
	 * 取得格式化效果的系统日期！
	 * 格式如：yyyy-MM-dd kk:mm:ss
	 */
	public final static String getFormateDate(String formate)
	{
		SimpleDateFormat f = new SimpleDateFormat(formate, Locale.US);
		return f.format(new Date());
	}
	
	/**
	 * 获取默认格式的日期和时间.形如：2007-7-8- 12:23:54
	 * @return
	 */
	public final static String getDateTime()
	{
		return getFormateDate("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取默认格式的日期.形如：2007-7-8
	 * @return
	 */
	public final static String getDate()
	{
		return getFormateDate("yyyy-MM-dd");
	}
	
	/**
	 * 获取默认格式的日期.形如：2007-7-8
	 * @return
	 */
	public final static String getDate(Object date)
	{
		if(date == null) return "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
	
	/**
	 * 获取当前的年份
	 * @return
	 */
	public final static String getYear()
	{
		return getFormateDate("yyyy");
	}
	
	/**
	 * 获取当前的短年份
	 * @return
	 */
	public final static String getShortYear()
	{
		return getFormateDate("yy");
	}
	
	/**
	 * 获取当前的月份
	 * @return
	 */
	public final static String getMonth()
	{
		return getFormateDate("MM");
	}
	
	/**
	 * 获取当前的短月份
	 * @return
	 */
	public final static String getShortMonth()
	{
		return getFormateDate("M");
	}
	
	/**
	 * 获取当前的日期
	 * @return
	 */
	public final static String getDay()
	{
		return getFormateDate("dd");
	}
	
	/**
	 * 获取当前的短日期
	 * @return
	 */
	public final static String getShortDay()
	{
		return getFormateDate("d");
	}
	
	/**
	 * 获取默认格式的时间(24小时制).形如：16:23:54
	 * @return
	 */
	public final static String getTime()
	{
		return getFormateDate("HH:mm:ss");
	}
	
	/**
	 * 判断指定的字符串是否是正确的日期时间字符串.<br>
	 * 该方法支持日期或日期时间的判断.
	 * 
	 * @param dateStr
	 */
	public final static boolean isDate(String dateStr)
	{
		Date dt =  parseSimpleDate(dateStr);
		if (dt != null) return true;
		return parseSimpleDateTime(dateStr) != null;
		
	}
	
	/**
	 * 使用指定的模式来判断字符串是否是日期时间字符串.
	 * @param pattern
	 * @param dateStr
	 * @return
	 */
	public final static boolean isDate(String pattern, String dateStr)
	{
		return parseSimpleDT(pattern, dateStr) != null;
	}
	
	/**
	 * 将指定的日期时间格式的字符串转换成日期对象.
	 * @param dateStr
	 * @return
	 */
	public final static Date parseDateTime(String dateStr)
	{
		try {
			return DateFormat.getDateTimeInstance().parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}
	
	/**
	 * 将指定日期格式的字符串转换成日期对象.
	 * @param dateStr
	 * @return
	 */
	public final static Date parseDate(String dateStr)
	{
		try {
			return DateFormat.getDateInstance().parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}
	
	/**
	 * 使用简单化的方式来解析日期时间格式.
	 * @param dateStr
	 * @return
	 */
	public final static Date parseSimpleDateTime(String dateStr)
	{
		return parseSimpleDT("yyyy-MM-dd HH:mm:ss", dateStr);
	}
	
	public final static Date parseSimpleDate(String dateStr)
	{
		return parseSimpleDT("yyyy-MM-dd", dateStr);
	}
	
	public final static Date parseSimpleTime(String timeStr)
	{
		return parseSimpleDT("HH:mm:ss", timeStr);
	}
	
	/**
	 * 使用指定的模式来解析字符串日期时间.
	 * @param pattern
	 * @param dateStr
	 * @return
	 */
	public final static Date parseSimpleDT(String pattern, String dateStr)
	{
		try {
			return new SimpleDateFormat(pattern, Locale.US).parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}
	
	/**
	 * 比较两个日期的大小.返回-1表示date1在date2之前，返回0表示两者相等，返回1
	 * 则表示date1在date2之后.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static int compareDate(Date date1, Date date2)
	{
		if (date1.before(date2)) return -1;
		if (date1.after(date2)) return 1;
		return 0;
	}
	
	/**
	 * 测试日期date1是否在date2之前.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isBefore(Date date1, Date date2)
	{
		if (date1 == null || date2 == null) return false;
		return date1.before(date2);
	}
	
	public final static boolean isBeforeNow(Date date1)
	{
		return isBefore(date1, Calendar.getInstance().getTime());
	}
	
	/**
	 * 测试日期date1是否在date2之后.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isAfter(Date date1, Date date2)
	{
		if (date1 == null || date2 == null) return false;
		return date1.after(date2);
	}
	
	public final static boolean isAfterNow(Date date1)
	{
		return isAfter(date1, Calendar.getInstance().getTime());
	}
	
	/**
	 * 测试日期date1和date2是否相等.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isEquals(Date date1, Date date2)
	{
		if (date1 == null || date2 == null) return false;
		return date1.equals(date2);
	}
	
	public final static boolean isEqualsNow(Date date1)
	{
		return isEquals(date1, Calendar.getInstance().getTime());
	}
	
	/**
	 * 获取当前日期时间，参数表示在此基础上的偏差，参数依次表示年、月、日、时、分、秒。
	 * 为正则表示在此日期上加、为负则表示在此日期上减。
	 * @param deviation
	 * @return
	 */
	public final static Date getNowDate(int... deviation)
	{
		return setDate(new Date(), deviation);
	}
	
	/**
	 * 在某一指定的日期基础上进行日期的偏差设置，参数意义同getNowDate
	 * @param date
	 * @param deviation
	 * @return
	 */
	public final static Date setDate(Date date, int... deviation)
	{
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		if (deviation.length < 1) return cal.getTime();
		final int[] filed = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
				Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND};
		for (int i = 0; i < deviation.length; i ++) {
			cal.add(filed[i], deviation[i]);
		}
		return cal.getTime();
	}
	
	/**
	 * 对日期时间字符串的提示字符串生成方法.<P>
	 * 
	 * 该方法主要是对日期时间字符串的提示,类似:1分钟前,1小时前等.对于大于1天的,则会提示
	 * 1天前,2天前等等这样的提示.
	 * 
	 * @param dateTime 日期时间字符串,必须包含时间
	 * @return
	 */
	public final static String dateTimeTips(Date dt)
	{
		Calendar cal = Calendar.getInstance(); // 获取当前日期时间
		long times = cal.getTimeInMillis() - dt.getTime(); // 获取时间差
		if (times <= 60*1000L) return "1 分钟前";
		else if (times <= 60*60*1000L) return (times/(60*1000)) + " 分钟前";
		else if (times <= 24*60*60*1000L) return (times/(60*60*1000L)) + " 小时前";
		else if (times <= 7*24*60*60*1000L) return (times/(24*60*60*1000L)) + " 天前";
		else if (times <= 30*24*60*60*1000L) return (times/(7*24*60*60*1000L)) + " 星期前";
		else if (times <= 12*30*24*60*60*1000L) return (times/(30*24*60*60*1000L)) + " 个月前";
		return (times/(12*30*24*60*60*1000L)) + " 年前";
	}
	
	public final static String dateTips(String dateStr)
	{
		Date dt = parseSimpleDate(dateStr);
		if (dt == null) return dateStr;
		return dateTimeTips(dt);
	}
	
	public final static String dateTimeTips(String dateTime)
	{
		Date dt = parseSimpleDateTime(dateTime); // 转换成日期时间类型
		if (dt == null) return dateTime;
		return dateTimeTips(dt);
	}
	
	
	/**
	 * 测试.
	 * @param args
	 */
	public static void main(String[] args)
	{
		StringUtil.debug("getDateTime : " + getDateTime());
		StringUtil.debug("getDate : " + getDate());
		StringUtil.debug("getTime : " + getTime());
		StringUtil.debug("getFormateDate : " + getFormateDate("/yyyy/MM/dd/"));
		StringUtil.debug(DateUtil.isDate("2007-8-29 12:56:15"));
		StringUtil.debug(DateUtil.isDate("2007985-29"));
		StringUtil.debug(DateUtil.parseDateTime("2007-9-23 12:45:58.3"));
		StringUtil.debug(DateUtil.parseDate("2009-9-23 12:45:58"));
		StringUtil.debug(DateUtil.getNowDate());
		StringUtil.debug(DateUtil.setDate(DateUtil.parseDateTime("2007-10-29 11:54:34.0"), 
				0, 0, 3));
		StringUtil.debug(DateUtil.isBefore(DateUtil.setDate(DateUtil.parseDateTime("2007-10-29 11:54:34.0"), 
				0, 0, 3), DateUtil.getNowDate()));
		StringUtil.debug(DateUtil.getShortYear() + "/" + DateUtil.getShortMonth()
				+ "/" + DateUtil.getShortDay());
		StringUtil.debug(DateUtil.dateTips("2007-11-8 13:9:20"));
		StringUtil.debug(DateUtil.dateTips("2008-1-8 7:9:20"));
		StringUtil.debug(DateUtil.dateTips("2007-1-8 7:9:20"));
		StringUtil.debug(DateUtil.dateTips("2008@1-8 7:9:20"));
		StringUtil.debug(DateUtil.dateTips("2008-1-8"));
		StringUtil.debug(DateUtil.parseSimpleDateTime("2008-1-8 12:56:52"));
		StringUtil.debug(DateUtil.parseSimpleDate("2008-1-8"));
		StringUtil.debug(DateUtil.parseSimpleTime("22:56:52"));
	}
}
