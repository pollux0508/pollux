package com.polluxframework.commons.utils;

import com.polluxframework.commons.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhumin0508
 * created in  2018/5/8 13:45
 * modified By:
 */
public class SessionUtils {

	private SessionUtils() {
	}

	/**
	 * 从Session获取当前用户的用户ID
	 *
	 * @param request 请求
	 * @return 当前用户的用户ID
	 */
	public static String getCurUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute(Constants.CURRENT_USER_ID).toString();
	}

	/**
	 * 设置当天用户Id到Session中
	 *
	 * @param request 请求
	 * @param userId  用户ID
	 */
	public static void setCurUserID(HttpServletRequest request, String userId) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.CURRENT_USER_ID, userId);
	}
}
