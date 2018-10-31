package com.polluxframework.common.constant;

/**
 * @author zhumin0508
 * created in  2018/5/8 12:00
 * modified By:
 */
public enum DateEnum {
	//年月
	YEAR_MONTH("yyyy-MM", "yyyy/MM", "yyyy MM", "yyyy.MM"),
	//年月日
	YEAR_MONTH_DAY("yyyy-MM-dd", "yyyy/MM/dd", "yyyy MM dd", "yyyy.MM.dd"),
	//年月日时
	YEAR_MONTH_DAY_HOUR("yyyy-MM-dd HH", "yyyy/MM/dd HH", "yyyy MM dd HH", "yyyy.MM.dd HH"),
	//年月日时分
	YEAR_MONTH_DAY_HOUR_MIN("yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy MM dd HH:mm", "yyyy.MM.dd HH:mm"),
	//年月日时分秒
	FULL_DATETIME("yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy MM dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss");

	/**
	 * 直线
	 */
	private String beeline;
	/**
	 * 斜线
	 */
	private String slantLine;
	/**
	 * 空格
	 */
	private String blankSpace;
	/**
	 * 点
	 */
	private String dot;

	DateEnum(String beeline, String slantLine, String blankSpace, String dot) {
		this.beeline = beeline;
		this.slantLine = slantLine;
		this.blankSpace = blankSpace;
		this.dot = dot;
	}

	public String getBeeline() {
		return beeline;
	}

	public String getSlantLine() {
		return slantLine;
	}

	public String getBlankSpace() {
		return blankSpace;
	}

	public String getDot() {
		return dot;
	}

	/**
	 * 生成所有的日期格式
	 */
	private static final String[] ALL_DATA_FORMAT = new String[]{
			YEAR_MONTH.getBeeline(), YEAR_MONTH.getSlantLine(), YEAR_MONTH.getBlankSpace(), YEAR_MONTH.getDot(),
			YEAR_MONTH_DAY.getBeeline(), YEAR_MONTH_DAY.getSlantLine(), YEAR_MONTH_DAY.getBlankSpace(), YEAR_MONTH_DAY.getDot(),
			YEAR_MONTH_DAY_HOUR.getBeeline(), YEAR_MONTH_DAY_HOUR.getSlantLine(), YEAR_MONTH_DAY_HOUR.getBlankSpace(), YEAR_MONTH_DAY_HOUR.getDot(),
			YEAR_MONTH_DAY_HOUR_MIN.getBeeline(), YEAR_MONTH_DAY_HOUR_MIN.getSlantLine(), YEAR_MONTH_DAY_HOUR_MIN.getBlankSpace(), YEAR_MONTH_DAY_HOUR_MIN.getDot(),
			FULL_DATETIME.getBeeline(), FULL_DATETIME.getSlantLine(), FULL_DATETIME.getBlankSpace(), FULL_DATETIME.getDot()};

	/**
	 * 获取所有的日期格式
	 * @return 日期格式
	 */
	public static String[] getAllDateFormat() {
		return ALL_DATA_FORMAT.clone();
	}
}
