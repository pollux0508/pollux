package com.polluxframework.web.utils;

import javax.servlet.http.HttpServletRequest;
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
		if ((request.getHeader(ACCEPT) != null && request.getHeader(ACCEPT).contains(JSON_ACCEPT)) || JSON_REQUEST_VAL.equals(request.getHeader(JSON_REQUEST_HEADER))) {
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
}
