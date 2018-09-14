package com.polluxframework.commons.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhumin0508
 * created in  2018/9/14 10:54
 * modified By:
 */
public class FileUtilsAPIdocTest {
	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		List<File> collected = new ArrayList<>();
		File file = new File("E:\\Inspect\\hzinfo-inspect\\inspect-ams\\src\\main\\java\\");
		List<String> includes = new ArrayList<>();
		includes.add("java");
		FileUtils.collectFiles(collected,file,includes);
		List<String> api = new ArrayList<>();
		for(File bean:collected) {
			String str= FileUtils.readFileToStr(bean,"UTF-8");
			str=str.replaceAll("\r\n", "");
			str=str.replaceAll("\t", "");
			str=str.replaceAll("\n", "");
			str=str.replaceAll(" ", "");
			Pattern p = Pattern.compile("/\\*.*?\\*/",Pattern.DOTALL);
			Matcher m = p.matcher(str);
			while(m.find()) {
				api.add(str.substring(m.start(),m.end()));
			}
		}


		for(String bean:api){
			Pattern p = Pattern.compile("group=\"(.*?)\"",Pattern.DOTALL);
			Matcher m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}
			p = Pattern.compile("childGroup=\"(.*?)\"",Pattern.DOTALL);
			m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}
			p = Pattern.compile("status=\"(.*?)\"",Pattern.DOTALL);
			m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}
			p = Pattern.compile("protocol=\"(.*?)\"",Pattern.DOTALL);
			m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}
			p = Pattern.compile("method=\"(.*?)\"",Pattern.DOTALL);
			m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}
			p = Pattern.compile("path=\"(.*?)\"",Pattern.DOTALL);
			m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}
			p = Pattern.compile("name=\"(.*?)\"",Pattern.DOTALL);
			m = p.matcher(bean);
			if(m.find()) {
				System.out.println(bean.substring(m.start(),m.end()));
			}

			p = Pattern.compile("parameter=\\{(.*?)\\}",Pattern.DOTALL);
			m = p.matcher(bean);
			while(m.find()) {
				String temp = bean.substring(m.start(),m.end());
				System.out.println(temp);
				Pattern p1 = Pattern.compile("name=\"(.*?)\"",Pattern.DOTALL);
				Matcher m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()));
				}

				p1 = Pattern.compile("type=\"(.*?)\"",Pattern.DOTALL);
				m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()));
				}

				p1 = Pattern.compile("description=\"(.*?)\"",Pattern.DOTALL);
				m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()));
				}
				p1 = Pattern.compile("required=(.*?)}",Pattern.DOTALL);
				m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()-1));
				}
			}

			p = Pattern.compile("response=\\{(.*?)\\}",Pattern.DOTALL);
			m = p.matcher(bean);
			while(m.find()) {
				String temp = bean.substring(m.start(),m.end());
				System.out.println(temp);
				Pattern p1 = Pattern.compile("name=\"(.*?)\"",Pattern.DOTALL);
				Matcher m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()));
				}

				p1 = Pattern.compile("type=\"(.*?)\"",Pattern.DOTALL);
				m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()));
				}

				p1 = Pattern.compile("description=\"(.*?)\"",Pattern.DOTALL);
				m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()));
				}
				p1 = Pattern.compile("required=(.*?)}",Pattern.DOTALL);
				m1 = p1.matcher(temp);
				if(m1.find()) {
					System.out.println(temp.substring(m1.start(),m1.end()-1));
				}
			}
		}

	}
}
