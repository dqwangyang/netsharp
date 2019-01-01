package org.netsharp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.netsharp.core.NetsharpException;

public class DateManage {

	public static final SimpleDateFormat STD_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat STD_TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");
	public static final SimpleDateFormat STD_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat STD_SHORT_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat STD_DATE_TIME_FORMATX = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	public static final SimpleDateFormat FILE_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd_HHmmss");
	public static final SimpleDateFormat INT_DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_PWD = "yyyyMMddHHmmss";
	public static final String SIMPLE_DATE = "yyyy-MM-dd";

	private static SimpleDateFormat shortFormate = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat longFormate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dataFileName = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");

	public static int getYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar.get(Calendar.MONTH) + 1;// 国外的月从0到11月
	}

	public static int getDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date fromStdDateTimeXStr(String dtStr) throws ParseException {
		try {
			return STD_DATE_TIME_FORMATX.parse(dtStr);
		} catch (Exception e) {
		}
		return null;
	}

	public static int getWeekofYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getWeekofMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	public static Date addDays(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);

		return calendar.getTime();
	}

	public static Date addMonths(Date date, int months) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);

		return calendar.getTime();
	}

	public static int dayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static Date addYears(Date date, int years) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	public static int getDayofWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

		return dayofWeek;
	}

	public static String getDayofWeekUpper(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

		switch (dayofWeek) {
		case 1:
			return "日";
		case 2:
			return "一";
		case 3:
			return "二";
		case 4:
			return "三";
		case 5:
			return "四";
		case 6:
			return "五";
		case 7:
			return "六";
		}

		throw new NetsharpException("无效的dayofweek值:" + dayofWeek);
	}

	public static int getMonthDiff(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR)
				- startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH)
				- startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	public static Date addDate(Date date, int datePart, int values) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(datePart, values);
		return calendar.getTime();
	}

	// 去掉时分秒
	@SuppressWarnings("deprecation")
	public static Date toDate(Date date) {
		date = DateManage.addDate(date, Calendar.HOUR_OF_DAY,
				0 - date.getHours());
		date = DateManage.addDate(date, Calendar.MINUTE, 0 - date.getMinutes());
		date = DateManage.addDate(date, Calendar.SECOND, 0 - date.getSeconds());

		return date;
	}

	public static String getDateFilter(String propertyName, Date date,
			Date date2) {

		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String str = formate.format(date);
		String str2 = formate.format(date2);

		return propertyName + ">='" + str + " 00:00:00' AND " + propertyName
				+ "<'" + str2 + " 00:00:00'";
	}

	public static String getDateFilter(String propertyName, Date date, int days) {

		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String str = formate.format(date);

		Date date2 = addDays(date, days);
		String str2 = formate.format(date2);

		// return
		// propertyName+">='"+str+" 00:00:00' AND "+propertyName+"<'"+str2+" 00:00:00'";
		return propertyName + ">='" + str + " 00:00:00' AND " + propertyName
				+ "<'" + str2 + " 00:00:00'";
	}

	public static Date parse(String str) {
		try {
			Date d = shortFormate.parse(str);
			return d;
		} catch (ParseException e) {
			throw new NetsharpException(str + "不能转换成日期", e);
		}
	}

	public static String getMonthFilter(String propertyName, Date date,
			int month) {

		return propertyName;

	}

	@SuppressWarnings("deprecation")
	public static boolean dayEquals(Date d1, Date d2) {

		return d1.getDay() == d2.getDay() && d1.getMonth() == d2.getMonth()
				&& d1.getYear() == d2.getYear();
	}

	public static String toString(Date date) {
		if (date == null) {
			return "";
		}

		return shortFormate.format(date);
	}

	public static String toLongString(Date date) {
		if (date == null) {
			return "";
		}
		return longFormate.format(date);
	}
	
	public static String toMillisecondsString(Date date) {
		if (date == null) {
			return "";
		}
		return STD_DATE_TIME_FORMATX.format(date);
	}

	public static Long toLong(Date date) {
		return date == null ? null : Long
				.valueOf(LONG_DATE_FORMAT.format(date));
	}

	public static String toFileName(Date date) {
		return dataFileName.format(date);
	}

	public static String toString(Date date, String format) {
		SimpleDateFormat shortFormate = new SimpleDateFormat(format);
		return shortFormate.format(date);
	}

	public static boolean equals(Date date1, Date date2) {
		if (date1 == null) {
			return date2 == null;
		} else {
			return date1.equals(date2);
		}
	}

	public static Date get(int year, int month, int date) {
		return DateManage.get(year, month, date, 0, 0, 0, 0);
	}

	public static Date get(int year, int month, int date, int hourOfDay,
			int minute, int second, int millSecond) {
		year = narrow(year, 0, 9999);
		month = narrow(month, 1, 12);
		switch (month) {
		case 2:
			date = isLeapYear(year) ? narrow(date, 1, 29) : narrow(date, 1, 28);
			break;
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			date = NumUtil.narrow(date, 1, 31);
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			date = NumUtil.narrow(date, 1, 30);
			break;
		default:
		}
		hourOfDay = NumUtil.narrow(hourOfDay, 0, 23);
		minute = NumUtil.narrow(minute, 0, 59);
		second = NumUtil.narrow(second, 0, 59);
		millSecond = NumUtil.narrow(millSecond, 0, 999);
		//
		Calendar cal = getCalendar(null);
		month = month - 1;
		cal.set(year, month, date, hourOfDay, minute, second);
		cal.set(Calendar.MILLISECOND, millSecond);
		return cal.getTime();
	}

	public static int narrow(int num, int min, int max) {
		return Math.max(min, Math.min(num, max));
	}

	// 默认时区"GMT+08:00"
	public final static TimeZone defaultTimezone = TimeZone
			.getTimeZone("GMT+08:00");

	public static Calendar getCalendar(TimeZone timeZone) {
		if (timeZone == null) {
			timeZone = defaultTimezone;
		}
		return Calendar.getInstance(defaultTimezone);
	}

	public static boolean isLeapYear(int year) {
		return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
	}

	/**
	 * 计算两个日期相差的年数
	 *
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 */
	public static int getDistinceYear(Date beforeDate, Date afterDate) {

		try {
			String beforeYear = DateFormatUtils.format(beforeDate, "yyyy");
			String afterYear = DateFormatUtils.format(afterDate, "yyyy");
			return Math.abs(Integer.parseInt(afterYear)
					- Integer.parseInt(beforeYear));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
