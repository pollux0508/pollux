package com.polluxframework.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.commons.entity.Api;
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
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		List<File> collected = new ArrayList<>();
		File file = new File("E:\\Inspect\\hzinfo-inspect\\inspect-ams\\src\\main\\java\\");
		List<String> includes = new ArrayList<>();
		includes.add("java");
		FileUtils.collectFiles(collected, file, includes);

		List<Api> apis = new ArrayList<>();
		for (File file1 : collected) {
			List<String> api = new ArrayList<>();
			String controller = "";
			String str = FileUtils.readFileToStr(file1, "UTF-8");
			str = str.replaceAll("\r\n", "");
			str = str.replaceAll("\t", "");
			str = str.replaceAll("\n", "");
			str = str.replaceAll(" ", "");
			Pattern p = Pattern.compile("(@Controller.*?@RequestMapping\\(\"/.*?\"\\))", Pattern.DOTALL);
			Matcher m = p.matcher(str);
			if (m.find()) {
				String temp = str.substring(m.start(), m.end());
				p = Pattern.compile("(@RequestMapping\\(\"/.*?\"\\))", Pattern.DOTALL);
				m = p.matcher(str);
				if (m.find()) {
					controller = str.substring(m.start() + 18, m.end() - 2);
				}
				str = str.substring(m.end());
			} else {
				continue;
			}
			p = Pattern.compile("/\\*.*?\\*/.*?(@RequestMapping\\(\"/.*?\"\\))", Pattern.DOTALL);
			m = p.matcher(str);
			while (m.find()) {
				String temp = str.substring(m.start(), m.end());
				api.add(temp);
			}


			for (String bean : api) {
				StringBuilder sb=new StringBuilder();
				sb.append('{');
				p = Pattern.compile("(@RequestMapping\\(\"/.*?\"\\))", Pattern.DOTALL);
				m = p.matcher(bean);
				if (m.find()) {
					sb.append("\"url\"=\""+controller + "/"+bean.substring(m.start() + 18, m.end() - 2)+"\"").append(',');
				}

				p = Pattern.compile("module=\"(.*?)\"", Pattern.DOTALL);
				m = p.matcher(bean);
				if (m.find()) {
					sb.append("\"module\":"+bean.substring(m.start()+7, m.end())).append(',');
				}
				p = Pattern.compile("domain=\"(.*?)\"", Pattern.DOTALL);
				m = p.matcher(bean);
				if (m.find()) {
					sb.append("\"domain\":"+bean.substring(m.start()+7, m.end())).append(',');
				}
				p = Pattern.compile("protocol=\"(.*?)\"", Pattern.DOTALL);
				m = p.matcher(bean);
				if (m.find()) {
					sb.append("\"protocol\":"+bean.substring(m.start()+9, m.end())).append(',');
				}
				p = Pattern.compile("method=\"(.*?)\"", Pattern.DOTALL);
				m = p.matcher(bean);
				if (m.find()) {
					sb.append("\"method\":"+bean.substring(m.start()+7, m.end())).append(',');
				}
				p = Pattern.compile("name=\"(.*?)\"", Pattern.DOTALL);
				m = p.matcher(bean);
				if (m.find()) {
					sb.append("\"name\":"+bean.substring(m.start()+5, m.end())).append(',');
				}

				p = Pattern.compile("parameter=\\{(.*?)\\}", Pattern.DOTALL);
				m = p.matcher(bean);
				sb.append("\"parameters\":[");
				while (m.find()) {
					sb.append('{');
					String tem = bean.substring(m.start()+10, m.end());
					Pattern p1 = Pattern.compile("name=\"(.*?)\"", Pattern.DOTALL);
					Matcher m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"name\":"+tem.substring(m1.start()+5, m1.end())).append(',');
					}
					p1 = Pattern.compile("type=\"(.*?)\"", Pattern.DOTALL);
					m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"type\":"+tem.substring(m1.start()+5, m1.end())).append(',');
					}
					p1 = Pattern.compile("description=\"(.*?)\"", Pattern.DOTALL);
					m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"description\":"+tem.substring(m1.start()+12, m1.end())).append(',');
					}
					p1 = Pattern.compile("required=(true|false)", Pattern.DOTALL);
					m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"required\":"+tem.substring(m1.start()+9, m1.end())).append(',');
					}
					sb.delete(sb.length()-1,sb.length());
					sb.append("},");
				}
				sb.delete(sb.length()-1,sb.length());
				sb.append(']');
				sb.append(',');
				p = Pattern.compile("response=\\{(.*?)\\}", Pattern.DOTALL);
				m = p.matcher(bean);
				sb.append("\"responses\":[");
				while (m.find()) {
					sb.append('{');
					String tem = bean.substring(m.start()+9, m.end());
					Pattern p1 = Pattern.compile("name=\"(.*?)\"", Pattern.DOTALL);
					Matcher m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"name\":"+tem.substring(m1.start()+5, m1.end())).append(',');
					}
					p1 = Pattern.compile("type=\"(.*?)\"", Pattern.DOTALL);
					m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"type\":"+tem.substring(m1.start()+5, m1.end())).append(',');
					}
					p1 = Pattern.compile("description=\"(.*?)\"", Pattern.DOTALL);
					m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"description\":"+tem.substring(m1.start()+12, m1.end())).append(',');
					}
					p1 = Pattern.compile("required=(true|false)", Pattern.DOTALL);
					m1 = p1.matcher(tem);
					if (m1.find()) {
						sb.append("\"required\":"+tem.substring(m1.start()+9, m1.end())).append(',');
					}
					sb.delete(sb.length()-1,sb.length());
					sb.append("},");
				}
				sb.delete(sb.length()-1,sb.length());
				sb.append(']');
				sb.append('}');
				String string= sb.toString();
				string=string.replaceAll("=",":");
				System.out.println(string);
				ObjectMapper objectMapper = new ObjectMapper();
				Api api1 = objectMapper.readValue(string,Api.class);
				System.out.println(api1);
			}
		}

	}
}
