package org.jeecg.common.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

/**
 * 
 * 类描述：时间操作定义类
 * 
 * @Author: 张代浩
 * @Date:2012-12-8 12:15:03
 * @Version 1.0
 */
public class DateUtils extends PropertyEditorSupport {
	/**
	 * 日期标准格式
	 */
	public static final DateTimeFormatter DATE_STANDARD_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
	/**
	 * 时间标准格式
	 */
	public static final DateTimeFormatter DATETIME_STANDARD_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 时间分隔符
	 */
	public static final String COLON = ":";

	/**
	 * 只包含日期的时候，标准格式的长度
	 */
	private static final int ONLY_DATE_LENGTH = 10;

	/**
	 * 包含时间的时候，标准格式的长度
	 */
	private static final int WITH_TIME_LENGTH = 19;

	/**
	 * 日期转换为字符串
	 * 
	 * @param format 日期格式
	 * @return 字符串
	 */
	public static String getDate(String format) {
		return DateTime.now().toString(format);
	}

	/**
	 * 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的标准形式字符串
	 */
	public static String now() {
		return DateTime.now().toString(DATETIME_STANDARD_FORMAT);
	}

	/**
	 * 当前日期，格式 yyyy-MM-dd
	 *
	 * @return 当前日期的标准形式字符串
	 */
	public static String today() {
		return DateTime.now().toString(DATE_STANDARD_FORMAT);
	}



	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal 指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {
		// --------------------return cal.getTimeInMillis();
		return cal.getTime().getTime();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatDate
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日
	 * 
	 * @return 默认日期按“年-月-日“格式显示
	 */
	public static String formatDate() {
		return today();
	}


	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param cal 指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Calendar cal) {
		return new DateTime(cal).toString(DATE_STANDARD_FORMAT);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// parseDate
	// parseCalendar
	// parseTimestamp
	// 将字符串按照一定的格式转化为日期或时间
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src     将要转换的原始字符窜
	 * @param pattern 转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws UnsupportedOperationException IllegalArgumentException
	 */
	public static Date parseDate(String src, String pattern) throws UnsupportedOperationException, IllegalArgumentException {
		return DateTimeFormat.forPattern(pattern).parseDateTime(src).toDate();
	}

	public static Date toStandardDate(String src) throws IllegalArgumentException {
		return DATETIME_STANDARD_FORMAT.parseDateTime(src).toDate();
	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src     将要转换的原始字符窜
	 * @param pattern 转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws UnsupportedOperationException IllegalArgumentException
	 */
	public static Calendar parseCalendar(String src, String pattern) throws UnsupportedOperationException, IllegalArgumentException {
		return DateTimeFormat.forPattern(pattern).parseDateTime(src).toGregorianCalendar();

	}

	/**
	 * String类型 转换为Date, 如果参数长度为10 转换格式”yyyy-MM-dd“ 如果参数长度为19 转换格式”yyyy-MM-dd
	 * HH:mm:ss“ * @param text String类型的时间值
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (!text.contains(COLON) && text.length() == ONLY_DATE_LENGTH) {
					setValue(DATE_STANDARD_FORMAT.parseDateTime(text));
				} else if (text.indexOf(COLON) > 0 && text.length() == WITH_TIME_LENGTH) {
					setValue(DATE_STANDARD_FORMAT.parseDateTime(text));
				} else {
					throw new IllegalArgumentException("Could not parse date, date format is error ");
				}
			} catch (UnsupportedOperationException | IllegalArgumentException ex) {
				throw  new IllegalArgumentException("Could not parse date: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
}