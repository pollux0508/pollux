package com.polluxframework.commons.utils;

import com.polluxframework.commons.constant.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/22 8:39
 * modified By:
 */
public class BaseUtilsTest {
	static final String ROOT_PATH = FileUtilsTest.class.getResource("/").getPath();

	@Test
	public void checkNextCharIndex() {
		char[] source = {'a', 'b', 'c', 'a', 'c', 'a', 'd', 'a', 'b'};
		char[] target = {'a', 'b'};
		int index = BaseUtils.checkNextCharIndex(source, 1, target);
		Assert.assertEquals("数组target在source从第二个位置开始再次出现的位置为7", 7, index);
		char[] cs = {'a', 'b', 'c', 'd'};
		index = BaseUtils.checkNextCharIndex(target, 0, cs);
		Assert.assertEquals("数组cs在target从第一个位置开始再次出现的位置为-1", -1, index);
		index = BaseUtils.checkNextCharIndex(source, 0, cs);
		Assert.assertEquals("数组cs在source从第一个位置开始再次出现的位置为-1", -1, index);
		char[] empty = new char[0];
		index = BaseUtils.checkNextCharIndex(source, 0, empty);
		Assert.assertEquals("数组empty在source从第一个位置开始再次出现的位置为-1", -1, index);
		char b = 'b';
		index = BaseUtils.checkNextCharIndex(source, 2, b);
		Assert.assertEquals("字符b在source从第二个位置开始再次出现的位置为8", 8, index);
	}


	@Test
	public void compareCharArray() {
		char[] source = {'a', 'b', 'c', 'a', 'c', 'a', 'd', 'a', 'b'};
		char[] target = {'a', 'b'};
		Assert.assertTrue("字符数组target在字符数组source中", BaseUtils.compareCharArray(source, 0, target));
		char[] cs = {'a', 'b', 'c', 'd'};
		Assert.assertFalse("字符数组cs不在字符数组source中", BaseUtils.compareCharArray(source, 0, cs));
		Assert.assertFalse("字符数组cs不在字符数组target中", BaseUtils.compareCharArray(target, 0, cs));
	}


	@Test
	public void checkNextStrIndex() {
		char[] source = {'a', 'b', 'c', 'a', 'c', 'a', 'd', 'a', 'b'};
		String target = "ab";
		int index = BaseUtils.checkNextStrIndex(source, 1, target);
		Assert.assertEquals("字符target在source从第2个位置开始再次出现的位置为7", 7, index);

		index = BaseUtils.checkNextStrIndex(source, -1, target);
		Assert.assertEquals("字符target在source从第1个位置开始再次出现的位置为-1", -1, index);

		index = BaseUtils.checkNextStrIndex(source, 0, null);
		Assert.assertEquals("null在source从第1个位置开始再次出现的位置为-1", -1, index);

		index = BaseUtils.checkNextStrIndex(source, 0, "");
		Assert.assertEquals("空字符串在source从第1个位置开始再次出现的位置为-1", -1, index);

		index = BaseUtils.checkNextStrIndex(source, -1, null);
		Assert.assertEquals("null在source从第1个位置开始再次出现的位置为-1", -1, index);

		index = BaseUtils.checkNextStrIndex(source, -1, "");
		Assert.assertEquals("空字符串在source从第1个位置开始再次出现的位置为-1", -1, index);
	}

	@Test
	public void checkUpStrIndex() {
		char[] source = {'a', 'b', 'c', 'a', 'c', 'a', 'd', 'a', 'b'};
		String target = "ab";
		int index = BaseUtils.checkUpStrIndex(source, 7, target);
		Assert.assertEquals("字符串target在source从第8个位置开始上一次出现的位置为0", 0, index);
		index = BaseUtils.checkUpStrIndex(source, 7, null);
		Assert.assertEquals("null在source从第8个位置开始上一次出现的位置为0", -1, index);
		index = BaseUtils.checkUpStrIndex(source, 7, "");
		Assert.assertEquals("空字符串在source从第8个位置开始上一次出现的位置为0", -1, index);
		target = "abcd";
		index = BaseUtils.checkUpStrIndex(source, 7, target);
		Assert.assertEquals("数组target在source从第8个位置开始上一次出现的位置为-1", -1, index);
		index = BaseUtils.checkUpStrIndex(source, 3, target);
		Assert.assertEquals("数组target在source从第4个位置开始上一次出现的位置为-1", -1, index);
		index = BaseUtils.checkUpStrIndex(source, 0, target);
		Assert.assertEquals("数组target在source从第1个位置开始上一次出现的位置为-1", -1, index);
		index = BaseUtils.checkUpStrIndex(source, -1, target);
		Assert.assertEquals("数组target在source从第1个位置开始上一次出现的位置为-1", -1, index);
		target = "a";
		index = BaseUtils.checkUpStrIndex(source, 1, target);
		Assert.assertEquals("数组target在source从第2个位置开始上一次出现的位置为0", 0, index);
		target = "abcacadab";
		index = BaseUtils.checkUpStrIndex(source, 1, target);
		Assert.assertEquals("数组target在source从第2个位置开始上一次出现的位置为-1", -1, index);
	}

	@Test
	public void checkStrIsInList() {
		String str = "我爱中国";
		List<String> list = new ArrayList<>();
		list.add("我爱中国");
		list.add("我爱湖南");
		list.add("我爱长沙");
		list.add("我爱岳麓区");
		list.add("");
		list.add(null);
		Assert.assertTrue("\"我爱中国\" 这个字符串在数据中存在(不模糊匹配)", BaseUtils.checkStrIsInList(list, str, false));
		Assert.assertTrue("\"我爱中国\" 这个字符串在数据中存在(模糊匹配)", BaseUtils.checkStrIsInList(list, str, true));
		str = "湖南";
		Assert.assertFalse("\"湖南\" 这个字符串不在数据中存在(不模糊匹配)", BaseUtils.checkStrIsInList(list, str, false));
		Assert.assertTrue("\"湖南\" 这个字符串在数据中存在(模糊匹配)", BaseUtils.checkStrIsInList(list, str, true));
		str = "株洲";
		Assert.assertFalse("\"株洲\" 这个字符串不在数据中存在(模糊匹配)", BaseUtils.checkStrIsInList(list, str, true));

		Assert.assertFalse("\"株洲\" 这个字符串不在null中存在(不模糊匹配)", BaseUtils.checkStrIsInList(null, str, false));
		Assert.assertFalse("\"株洲\" 这个字符串不在null中存在(模糊匹配)", BaseUtils.checkStrIsInList(null, str, true));

		Assert.assertFalse("\"株洲\" 这个字符串不在空list中存在(不模糊匹配)", BaseUtils.checkStrIsInList(new ArrayList<>(), str, false));
		Assert.assertFalse("\"株洲\" 这个字符串不在空list中存在(模糊匹配)", BaseUtils.checkStrIsInList(new ArrayList<>(), str, true));

		Assert.assertFalse("空字符串不在数据中存在(不模糊匹配)", BaseUtils.checkStrIsInList(list, "", false));
		Assert.assertFalse("空字符串不在数据中存在(模糊匹配)", BaseUtils.checkStrIsInList(list, "", true));

		Assert.assertFalse("NULL不在数据中存在(不模糊匹配)", BaseUtils.checkStrIsInList(list, null, false));
		Assert.assertFalse("NULL不在数据中存在(模糊匹配)", BaseUtils.checkStrIsInList(list, null, true));
	}

	@Test
	public void replaceAll() {
		String str = null;
		String result = BaseUtils.replaceAll(str, 's', 'b');
		Assert.assertNull("NULL替换后返回的对象为NULL", result);
		str = "";
		result = BaseUtils.replaceAll(str, 's', 'b');
		Assert.assertEquals("空字符串替换返回的对象为空字符串", str, result);
	}

	@Test
	public void replaceCurrentSystemLine() {
		String path = ROOT_PATH + "zhumin.txt";
		if (BaseUtils.getSystemFileSeparatorIsLinux()) {
			Assert.assertTrue("在Linux环境下装换后的字符串包含右划线", BaseUtils.replaceCurrentSystemLine(path).contains(Constants.SLASH));
		} else {
			Assert.assertFalse("在Windows环境下装换后的字符串不包含右划线，只包含双左划线", BaseUtils.replaceCurrentSystemLine(path).contains(Constants.SLASH));
		}

	}

	@Test
	public void getFileHashKey() throws Exception {
		File file = new File(ROOT_PATH + "zhumin.txt");
		Assert.assertNotEquals("采用文件的MD5和文件名的MD5得到的HashKey应该不一致", BaseUtils.getFileHashKey(file, Constants.FILE_HASH_MD5), BaseUtils.getFileHashKey(file, Constants.FILE_NAME_HASH_MD5));
		boolean flag;
		try {
			BaseUtils.getFileHashKey(file, "MD5");
			flag = true;
		} catch (IOException e) {
			flag = false;
		}
		Assert.assertFalse("MD5是不支持的方式，应该使用内部定义的FILE_MD5或FILE_NAME_MD5", flag);
	}

}

class Column {
	/**
	 * 显示的名字
	 */
	private String name;
	/**
	 * 对应的字段
	 */
	private String field;
	/**
	 * 是否显示 默认显示
	 */
	private boolean display = true;
	/**
	 * 单元格应占用的行数 默认1行
	 */
	private int rowspan = 1;
	/**
	 * 单元格应占用的列数 默认1列
	 */
	private int colspan = 1;
	/**
	 * 数据对齐格式 'left'，'right'，'center' 默认居中
	 */
	private String align = "center";
	/**
	 * 表头对齐格式 'left'，'right'，'center' 默认居中
	 */
	private String halign = "center";
	/**
	 * 数据横向对齐格式 'top', 'middle', 'bottom' 默认居中
	 */
	private String valign = "middle";
	/**
	 * 孩子节点，主要用于复杂表头使用
	 */
	private List<Column> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	protected String getHalign() {
		return halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	public String getValign() {
		return valign;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}

	public List<Column> getChildren() {
		return children;
	}

	public void setChildren(List<Column> children) {
		this.children = children;
	}
}