package com.polluxframework.web.entity;

import com.polluxframework.web.constant.WebConstant;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:22
 * modified By:
 */
public final class WebResponse {
	private int status;
	/**
	 * 返回的代码
	 */
	private String code;
	/**
	 * 返回的信息
	 */
	private String msg;
	/**
	 * 返回的数据
	 */
	private Object data;

	private WebResponse() {
	}

	public static WebResponse success() {
		return success(WebConstant.DEFAULT_SUCCESS_MESSAGE);
	}

	public static WebResponse success(String msg) {
		return success(msg, null);
	}

	public static WebResponse success(String msg, Object data) {
		WebResponse result = new WebResponse();
		result.setStatus(WebConstant.DEFAULT_SUCCESS_STATUS);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	public static WebResponse error() {
		return error(WebConstant.DEFAULT_ERROR_CODE, WebConstant.DEFAULT_ERROR_MESSAGE);
	}

	public static WebResponse error(String msg) {
		return error(WebConstant.DEFAULT_ERROR_CODE, msg);
	}

	public static WebResponse error(String code, String msg) {
		return error(code, msg, null);
	}

	public static WebResponse error(String code, String msg, Object data) {
		WebResponse result = new WebResponse();
		result.setStatus(WebConstant.DEFAULT_FAIL_STATUS);
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
