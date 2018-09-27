package com.polluxframework.commons.utils;

import com.polluxframework.commons.entity.Api;
import com.polluxframework.commons.entity.Parameter;
import com.polluxframework.commons.entity.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.compile;

/**
 * 获取类中的注释信息
 *
 * @author zhumin0508
 * created in  2018/9/15 8:09
 * modified By:
 */
public class AnnotationUtils {
	private static final String SLANT = "/";
	private static Pattern pattern = compile("(/\\*.*?\\*/.*?@RequestMapping\\(.*?\\))", DOTALL);
	private static final String CONTROLLER_REGEX = "@Controller";

	public static List<Api> getMethodAnnotation(File file) throws IOException {
		List<Api> result = new ArrayList<>();
		if (!file.exists()) {
			return result;
		}
		String str = replaceBlank(FileUtils.readFileToStr(file, "UTF-8"));
		if (!str.contains(CONTROLLER_REGEX)) {
			return result;
		}
		str = str.substring(str.indexOf(CONTROLLER_REGEX));
		String controller = AnnotationUtils.getControllerUrl(str);

		List<String> api = new ArrayList<>();
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String temp = str.substring(matcher.start(), matcher.end());
			api.add(temp);
		}

		for (String bean : api) {
			Api api1 = new Api();
			api1.setUrl(controller + AnnotationUtils.getRequestMappingValue(bean));
			api1.setMethod(AnnotationUtils.getRequestMappingMethod(bean));
			Pattern p = compile("api=\\{(.*?)\\}", Pattern.DOTALL);
			Matcher m = p.matcher(bean);
			if (m.find()) {
				String tem = bean.substring(m.start() + 5, m.end());
				api1.setName(AnnotationUtils.getCommentField("name", tem));
				api1.setModule(AnnotationUtils.getCommentField("module", tem));
				api1.setDescription(AnnotationUtils.getCommentField("description", tem));
				api1.setDomain(AnnotationUtils.getCommentField("domain", tem));
			}
			p = compile("@return(.*?)responsePage", Pattern.DOTALL);
			m = p.matcher(bean);
			if (m.find()) {
				api1.setPagination(true);
			} else {
				api1.setPagination(false);
			}
			p = compile("parameter=\\{(.*?)\\}", Pattern.DOTALL);
			m = p.matcher(bean);
			while (m.find()) {
				String tem = bean.substring(m.start() + 10, m.end());
				Parameter parameter = AnnotationUtils.getParameter(tem);
				api1.addParameter(parameter);
			}
			p = compile("response=\\{(.*?)\\}", Pattern.DOTALL);
			m = p.matcher(bean);
			while (m.find()) {
				String tem = bean.substring(m.start() + 9, m.end());
				Parameter parameter = AnnotationUtils.getParameter(tem);
				api1.addResponse(new Response(parameter));
			}
			result.add(api1);
		}
		return result;
	}

	/**
	 * @param controller 带有controller的注释
	 * @return 返回请求中的value
	 */
	public static String getControllerUrl(String controller) {
		String result = "";
		Pattern p = compile("(@Controller.*?@RequestMapping.*?class)", Pattern.DOTALL);
		Matcher m = p.matcher(controller);
		if (m.find()) {
			result = getRequestMappingValue(controller.substring(m.start(), m.end()));
		}
		return manageUrlSlant(result);
	}

	/**
	 * @param mapping 请求注释
	 * @return 返回请求中的value
	 */
	public static String getRequestMappingValue(String mapping) {
		String result = "";
		Pattern pattern = compile("(@RequestMapping\\(\".*?\"\\))", DOTALL);
		Matcher matcher = pattern.matcher(mapping);
		if (matcher.find()) {
			result = mapping.substring(matcher.start() + 17, matcher.end() - 2);
		} else {
			pattern = compile("(@RequestMapping\\(.*?value=\".*?\".*?\\))", Pattern.DOTALL);
			matcher = pattern.matcher(mapping);
			if (matcher.find()) {
				mapping = mapping.substring(matcher.start(), matcher.end());
				pattern = compile("(value=\".*?\")", Pattern.DOTALL);
				matcher = pattern.matcher(mapping);
				if (matcher.find()) {
					result = mapping.substring(matcher.start() + 7, matcher.end() - 1);
				}
			}
		}
		return manageUrlSlant(result);
	}

	public static String getRequestMappingMethod(String mapping) {
		String result = "";
		Pattern pattern = compile("(@RequestMapping\\(.*?method=\".*?\".*?\\))", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(mapping);
		if (matcher.find()) {
			mapping = mapping.substring(matcher.start(), matcher.end());
			pattern = compile("(method=\\{.*?\\})", Pattern.DOTALL);
			matcher = pattern.matcher(mapping);
			if (matcher.find()) {
				result = mapping.substring(matcher.start() + 8, matcher.end() - 1);
			}
		}
		if (result.isEmpty()) {
			result = "get/post";
		} else {
			String[] temp = result.split(",");
			for (String bean : temp) {
				if ("RequestMethod.GET".equals(temp)) {
					result += "/get";
				}
				if ("RequestMethod.POST".equals(temp)) {
					result += "/post";
				}
			}
			if (!result.isEmpty()) {
				result = result.substring(1);
			}
		}
		return result;
	}

	public static String getCommentField(String field, String comment) {
		String result = "";
		Pattern pattern = Pattern.compile(field + "=\"(.*?)\"", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(comment);
		if (matcher.find()) {
			result = comment.substring(matcher.start() + field.length() + 2, matcher.end() - 1);
		}
		return result;
	}

	public static Parameter getParameter(String comment) {
		Parameter parameter = new Parameter();
		parameter.setName(AnnotationUtils.getCommentField("name", comment));
		parameter.setType(AnnotationUtils.getCommentField("type", comment));
		parameter.setDescription(AnnotationUtils.getCommentField("description", comment));
		Pattern pattern = compile("required=(true|false)", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(comment);
		if (matcher.find()) {
			parameter.setRequired(Boolean.valueOf(comment.substring(matcher.start() + 9, matcher.end())));
		}
		return StringUtils.isBlank(parameter.getName()) ? null : parameter;
	}


	private static String manageUrlSlant(String url) {
		if (StringUtils.isBlank(url)) {
			return "";
		}
		String result = url;
		if (!result.startsWith(SLANT)) {
			result = SLANT + result;
		}
		if (result.endsWith(SLANT)) {
			result.substring(0, result.length() - 1);
		}
		return result;
	}

	private static String replaceBlank(String str) {
		str = str.replaceAll("\r\n", "");
		str = str.replaceAll("\t", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll(" ", "");
		return str;
	}
}
