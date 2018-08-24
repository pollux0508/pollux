package com.polluxframework.commons.utils;

import com.polluxframework.commons.constant.Constants;
import com.polluxframework.commons.entity.IPagination;
import com.polluxframework.commons.entity.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhumin0508
 * created in  2018/5/8 13:45
 * modified By:
 */
public class SessionUtils {
	/**
	 * 用于缓存分页情况，便于表格复原
	 */
	private static final String PAGE_SESSION = "currentPagination";

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

	/**
	 * 缓存用户分页信息
	 *
	 * @param request    请求
	 * @param pagination 分页信息
	 */
	public static void setPagination(HttpServletRequest request, Pagination pagination) {
		HttpSession session = request.getSession();
		session.setAttribute(PAGE_SESSION, pagination);
	}

	/**
	 * 清楚用户分页信息
	 *
	 * @param request 请求
	 */
	public static void clearPagination(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(PAGE_SESSION);
	}

	/**
	 * 获取缓存中的分页信息
	 *
	 * @param request 请求
	 * @return 分页信息Map集合
	 */
	public static Pagination getPagination(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(PAGE_SESSION);
		if (attribute instanceof Pagination) {
			return (Pagination) attribute;
		}
		return null;
	}

	/**
	 * 根据页面 Key 获取缓存中的分页新
	 *
	 * @param request 请求
	 * @return 对应Key的分页信息
	 */
	public static Pagination getPagination(HttpServletRequest request, IPagination pagination) {
		Pagination result = getPagination(request);
		if (result != null && result.getPageKey().equals(pagination.getPageKey())) {
			return result;
		}
		return new Pagination(pagination.getPageKey());
	}
}
