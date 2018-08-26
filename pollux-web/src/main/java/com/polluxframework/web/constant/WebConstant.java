package com.polluxframework.web.constant;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:09
 * modified By:
 */
public class WebConstant {
	private WebConstant(){

	}

	/**
	 * 统一错误页面字符串
	 */
	public static final String ERROR_PAGE = "pollux/error";
	/**
	 * //错误字符串
	 */
	public static final String ERROR = "error";
	/**
	 * 响应默认错误码字符串
	 */
	public static final String DEFAULT_ERROR_CODE = "PXRNWB000";
	/**
	 * 响应默认错误信息
	 */
	public static final String DEFAULT_ERROR_MESSAGE = "系统异常,请联系系统管理员";
	/**
	 * 响应未知错误信息
	 */
	public static final String UNKNOWN_ERROR = "未知错误，请联系系统管理员";
	/**
	 * 响应状态 成功
	 */
	public static final int RESPONSE_STATUS_SUCCESS = 200;
	/**
	 * 响应状态 失败
	 */
	public static final int RESPONSE_STATUS_FAIL = 600;
	/**
	 * 用户未登录错误码
	 */
	public static final String NOT_LOGGED_CODE = "PXRNWB001";
	/**
	 * 用户未登录错误信息
	 */
	public static final String NOT_LOGGED_MESSAGE = "用户未登录或登录后长时间未操作,请重新登录";

	public static final String CONTENT_TYPE = "Content-type";
	public static final String JSON_RESPONSE_CONTENT = "application/json;charset=UTF-8";
	public static final String JSON_RESPONSE_CONTENT_TEXT = "text/html;charset=UTF-8";
	/**
	 * JSON解析格式
	 */
	public static final String JSON_CHARSET = "UTF-8";
}
