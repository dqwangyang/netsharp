package org.netsharp.platform.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name = "sys_basic_date", header = "日期档案")
public class BizDate extends BizEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6165389408326529567L;

	@Column(name = "year", header = "年")
	private int year;

	@Column(name = "month", header = "月")
	private int month;
	
	@Column(name = "day", header = "日")
	private int day;
	
	@Column(name = "week", header = "周")
	private DayWeek week;
	
	@Column(name = "week_year_index", header = "在年当中第几周")
	private int weekYearIndex;
	
	@Column(name = "holidays", header = "节假日")
	private boolean holidays = false;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public DayWeek getWeek() {
		return week;
	}

	public void setWeek(DayWeek week) {
		this.week = week;
	}

	public boolean isHolidays() {
		return holidays;
	}

	public void setHolidays(boolean holidays) {
		this.holidays = holidays;
	}

	public int getWeekYearIndex() {
		return weekYearIndex;
	}

	public void setWeekYearIndex(int weekYearIndex) {
		this.weekYearIndex = weekYearIndex;
	}
}
