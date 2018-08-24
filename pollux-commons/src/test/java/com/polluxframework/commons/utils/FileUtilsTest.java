package com.polluxframework.commons.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/22 17:17
 * modified By:
 */
public class FileUtilsTest {
	static final String ROOT_PATH = FileUtilsTest.class.getResource("/").getPath();

	@Test
	public void collectFiles() {
		List<File> collected = new ArrayList<>();
		File file = new File(ROOT_PATH + "zhumin.txt");
		List<String> includes = new ArrayList<>();
		includes.add("html");
		includes.add("txt");
		FileUtils.collectFiles(collected, file, includes);
		Assert.assertEquals(1, collected.size());

		collected = new ArrayList<>();
		file = new File(ROOT_PATH);
		FileUtils.collectFiles(collected, file, includes);
		Assert.assertEquals(6, collected.size());
	}

	@Test
	public void readFileToString() throws IOException {
		File file = new File(ROOT_PATH + "zhumin.txt");
		String[] ary = FileUtils.readFileToString(file, "UTF-8");
		Assert.assertEquals(4, ary.length);

		file = new File(ROOT_PATH + "zhangsan.txt");
		ary = FileUtils.readFileToString(file, "UTF-8");
		Assert.assertEquals(0, ary.length);
		file = new File(ROOT_PATH + "wangwu.txt");
		ary = FileUtils.readFileToString(file, "UTF-8");
		Assert.assertEquals(1, ary.length);
	}

	@Test
	public void getFileLineFeed() throws IOException {
		File file = new File(ROOT_PATH + "zhumin.txt");
		char[] lineFeed = FileUtils.getFileLineFeed(file, "UTF-8");
		Assert.assertEquals(2, lineFeed.length);
		boolean flag;
		try {
			FileUtils.getFileLineFeed(null, "UTF-8");
			flag = true;
		} catch (IOException e) {
			flag = false;

		}
		Assert.assertFalse("文件为null应该要报异常", flag);

		file = new File(ROOT_PATH + "zhangsan.txt");
		lineFeed = FileUtils.getFileLineFeed(file, "UTF-8");
		Assert.assertEquals(0, lineFeed.length);


		Assert.assertEquals(0, FileUtils.getFileLineFeed("").length);
		Assert.assertEquals(0, FileUtils.getFileLineFeed(null).length);

		Assert.assertEquals("\r\n", new String(FileUtils.getFileLineFeed("a\nc\rabc\r\n")));
		Assert.assertEquals("\n", new String(FileUtils.getFileLineFeed("ac\nc\rabc")));
		Assert.assertEquals("\r", new String(FileUtils.getFileLineFeed("ac\rabc")));
		Assert.assertEquals("\n", new String(FileUtils.getFileLineFeed("acabc")));
	}

	@Test
	public void readFileToStr() throws IOException {
		File file = new File(ROOT_PATH + "zhumin.txt");
		FileUtils.readFileToStr(file, "UTF-8");
		file = new File(ROOT_PATH + "zhangsan.txt");
		FileUtils.readFileToStr(file, "UTF-8");
	}

	@Test
	public void writeFile() throws IOException {
		List<String> list = new ArrayList<>();
		list.add("我爱中国");
		list.add("我爱湖南");
		list.add("我爱长沙");
		list.add("我爱岳麓区");
		list.add("");
		list.add(null);
		File file = new File(ROOT_PATH + "zhaoliu.txt");
		FileUtils.writeFile(file, "UTF-8", list);
	}

	@Test
	public void getSystemLineSeparator() {
		String str = FileUtils.getSystemLineSeparator();
		if (BaseUtils.getSystemFileSeparatorIsLinux()) {
			Assert.assertEquals(1, str.toCharArray().length);
			Assert.assertEquals("\n", str);
		} else {
			Assert.assertEquals(2, str.toCharArray().length);
			Assert.assertEquals("\r\n", str);
		}
	}

	@Test
	public void fileChannelCopy() throws IOException {
		File zhumin = new File(ROOT_PATH + "zhumin.txt");
		File caocao = new File(ROOT_PATH + "caocao.txt");
		FileUtils.fileChannelCopy(zhumin, caocao);
	}


	@Test
	public void getSystemFileSeparator() {
		String str = FileUtils.getSystemFileSeparator();
		if (BaseUtils.getSystemFileSeparatorIsLinux()) {
			Assert.assertEquals("/", str);
		} else {
			Assert.assertEquals("\\", str);
		}
	}

	@Test
	public void clearBlankLines() {
		String str = "曹操是枭雄\r\n刘备是流氓\r\n\r\n孙权是奸雄";
		StringBuilder stringBuilder = new StringBuilder(str);
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("曹操是枭雄\r\n刘备是流氓\r\n孙权是奸雄", stringBuilder.toString());

		stringBuilder = null;
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertNull(stringBuilder);

		stringBuilder = new StringBuilder("");
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("", stringBuilder.toString());

		stringBuilder = new StringBuilder("曹操是枭雄,刘备是流氓,孙权是奸雄");
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("曹操是枭雄,刘备是流氓,孙权是奸雄", stringBuilder.toString());

		stringBuilder = new StringBuilder("a");
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("a", stringBuilder.toString());

		stringBuilder = new StringBuilder("\t");
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("\t", stringBuilder.toString());

		stringBuilder = new StringBuilder("\n\r\n");
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("\n\r\n", stringBuilder.toString());

		stringBuilder = new StringBuilder("\r\n\r\n");
		FileUtils.clearBlankLines(stringBuilder);
		Assert.assertEquals("\r\n", stringBuilder.toString());
	}

	@Test
	public void getFilePathDirectory() {
		String path = ROOT_PATH;
		path = BaseUtils.replaceCurrentSystemLine(path);
		path += "zhumin.txt";
		Assert.assertEquals(path.substring(0, path.length() - 11), FileUtils.getFilePathDirectory(path));
		path = "";
		Assert.assertEquals(path, FileUtils.getFilePathDirectory(path));
		path = null;
		Assert.assertNull(FileUtils.getFilePathDirectory(path));
		path = "E:zhumin.txt";
		Assert.assertEquals(path, FileUtils.getFilePathDirectory(path));
	}
}