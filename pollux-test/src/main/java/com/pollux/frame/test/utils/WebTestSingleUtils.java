package com.pollux.frame.test.utils;

import com.polluxframework.exception.SerializableException;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


/**
 * 用于做单元测试时参数需要一个request或者session时使用
 *
 *
 * @author zhumin0508
 * created in  2018/5/9 7:56
 * modified By:
 */
public class WebTestSingleUtils {
	private WebTestSingleUtils() {

	}

	static MockHttpServletRequest request;

	/**
	 * 获取测试时需要使用的请求
	 * request的是一个单例模式，测试过程这种整个request是同一个请求
	 * @return 返回一个request
	 */
	public static HttpServletRequest getSingleRequest() {
		if (request == null) {
			request = new MockHttpServletRequest();
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(Integer.MAX_VALUE);
		}
		return request;
	}

	/**
	 * 获取测试时需要使用的请求
	 * request的是一个非单例模式，测试过程这种整个request不是同一个请求
	 * @return 返回一个request
	 */
	public static HttpServletRequest getNoSingleRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(Integer.MAX_VALUE);
		return request;
	}

	/**
	 * 设置session值
	 *
	 * @param name      session名
	 * @param attribute session值
	 */
	public static void setAttributeForSession(String name, Object attribute) {
		getSingleRequest();
		if (attribute instanceof Serializable) {
			HttpSession session = request.getSession();
			session.setAttribute(name, attribute);
		}else{
			throw new SerializableException();
		}
	}

	/**
	 * 获取session
	 *
	 * @return 返回session
	 */
	public static HttpSession getSession() {
		getSingleRequest();
		return request.getSession();
	}
}
