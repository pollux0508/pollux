package com.polluxframework.web.utils;

import com.polluxframework.core.local.I18NEnum;
import com.polluxframework.core.local.Local;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:17
 * modified By:
 */
public class RequestUtils {
	private static final String ACCEPT = "accept";
	private static final String JSON_ACCEPT = "application/json";
	private static final String JSON_REQUEST_VAL = "XMLHttpRequest";
	private static final String JSON_REQUEST_HEADER = "X-Requested-With";
	private static final String JSON_STR = "json";
	private static final String AJAX_STR = "ajax";
	private static final int LOCALE_LEN = 2;

	private static final String LOCALE_I18N = "locale_i18n";

	private RequestUtils() {

	}

	/**
	 * 判断该请求是否是ajax请求
	 *
	 * @param request 请求
	 * @return 判断该请求是否是ajax请求
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		//优先判断请求头是否是json头判断
		String accept = request.getHeader(ACCEPT);
		if (StringUtils.isNotEmpty(accept) && accept.contains(JSON_ACCEPT)) {
			return true;
		}

		if (JSON_REQUEST_VAL.equals(request.getHeader(JSON_REQUEST_HEADER))) {
			return true;
		}
		//如果请求路径中带json字段，则认为是ajax请求
		String url = request.getRequestURI();
		if (url.contains(JSON_STR) || url.contains(AJAX_STR)) {
			return true;
		}
		//如果请求参数中有json字段，则认为是ajax请求
		Map param = request.getParameterMap();
		return param.containsValue(JSON_STR) || param.containsValue(AJAX_STR);
	}

	/**
	 * 获取请求对应的国际化语言(优先从session中获取当前用户的国际化语言-->从请求中的参数中获取-->从请求带的头信息中获取-->一切都没有时则从服务器部署部署的本地环境获取)
	 *
	 * @param request 请求
	 * @return 国际化语言
	 */
	public static I18NEnum getRequestLocal(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object locale = session.getAttribute(LOCALE_I18N);
		if (locale instanceof I18NEnum) {
			return (I18NEnum) locale;
		}
		String parameter = request.getParameter("locale");
		if (StringUtils.isNotBlank(parameter)) {
			String[] parameters = parameter.split("_");
			if (parameters.length >= LOCALE_LEN) {
				return Local.getLocale(parameters[0], parameters[1]);
			}
		}
		//Accept-Language对应的格式为：zh-CN,zh;q=0.9,en;q=0.8
		parameter = request.getHeader("Accept-Language");
		if (StringUtils.isNotBlank(parameter)) {
			String[] parameters = parameter.split(";");
			parameter = parameters[0];
			parameters = parameter.split(",");
			parameter = parameters[0];
			parameters = parameter.split("-");
			if (parameters.length >= LOCALE_LEN) {
				return Local.getLocale(parameters[0], parameters[1]);
			}
		}
		return Local.getDefaultLocale();
	}

	/**
	 * 获取请求对应的国际化语言(优先从session中获取当前用户的国际化语言-->从请求中的参数中获取-->从请求带的头信息中获取-->一切都没有时则从服务器部署部署的本地环境获取)
	 *
	 * @return 国际化语言
	 */
	public static I18NEnum getRequestLocal() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getRequestLocal(request);
	}
}
