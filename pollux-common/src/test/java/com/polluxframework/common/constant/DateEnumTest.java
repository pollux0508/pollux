package com.polluxframework.common.constant;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhumin0508
 * created in  2018/8/22 17:11
 * modified By:
 */
public class DateEnumTest {

	@Test
	public void getBeeline() {
		Assert.assertEquals("yyyy-MM", DateEnum.YEAR_MONTH.getBeeline());
		Assert.assertEquals("yyyy-MM-dd", DateEnum.YEAR_MONTH_DAY.getBeeline());
		Assert.assertEquals("yyyy-MM-dd HH", DateEnum.YEAR_MONTH_DAY_HOUR.getBeeline());
		Assert.assertEquals("yyyy-MM-dd HH:mm", DateEnum.YEAR_MONTH_DAY_HOUR_MIN.getBeeline());
		Assert.assertEquals("yyyy-MM-dd HH:mm:ss", DateEnum.FULL_DATETIME.getBeeline());
	}

	@Test
	public void getSlantLine() {
		Assert.assertEquals("yyyy/MM", DateEnum.YEAR_MONTH.getSlantLine());
		Assert.assertEquals("yyyy/MM/dd", DateEnum.YEAR_MONTH_DAY.getSlantLine());
		Assert.assertEquals("yyyy/MM/dd HH", DateEnum.YEAR_MONTH_DAY_HOUR.getSlantLine());
		Assert.assertEquals("yyyy/MM/dd HH:mm", DateEnum.YEAR_MONTH_DAY_HOUR_MIN.getSlantLine());
		Assert.assertEquals("yyyy/MM/dd HH:mm:ss", DateEnum.FULL_DATETIME.getSlantLine());
	}

	@Test
	public void getBlankSpace() {
		Assert.assertEquals("yyyy MM", DateEnum.YEAR_MONTH.getBlankSpace());
		Assert.assertEquals("yyyy MM dd", DateEnum.YEAR_MONTH_DAY.getBlankSpace());
		Assert.assertEquals("yyyy MM dd HH", DateEnum.YEAR_MONTH_DAY_HOUR.getBlankSpace());
		Assert.assertEquals("yyyy MM dd HH:mm", DateEnum.YEAR_MONTH_DAY_HOUR_MIN.getBlankSpace());
		Assert.assertEquals("yyyy MM dd HH:mm:ss", DateEnum.FULL_DATETIME.getBlankSpace());
	}

	@Test
	public void getDot() {
		Assert.assertEquals("yyyy.MM", DateEnum.YEAR_MONTH.getDot());
		Assert.assertEquals("yyyy.MM.dd", DateEnum.YEAR_MONTH_DAY.getDot());
		Assert.assertEquals("yyyy.MM.dd HH", DateEnum.YEAR_MONTH_DAY_HOUR.getDot());
		Assert.assertEquals("yyyy.MM.dd HH:mm", DateEnum.YEAR_MONTH_DAY_HOUR_MIN.getDot());
		Assert.assertEquals("yyyy.MM.dd HH:mm:ss", DateEnum.FULL_DATETIME.getDot());
	}

	@Test
	public void getAllDateFormat() {
		Assert.assertEquals(20,DateEnum.getAllDateFormat().length);
	}
}