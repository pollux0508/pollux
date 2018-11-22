package com.polluxframework.exception;

/**
 * @author zhumin0508
 * created in  2018/8/8 9:11
 * modified By:
 */
public class Exception extends java.lang.Exception implements IException {
	/**
	 * 异常代码
	 */
	private final String code;
	/**
	 * 异常信息
	 */
	private final String msg;

	public Exception(String code, String msg) {
		this.code = code;
		this.msg = msg;
		initCause(new Throwable(msg));
	}

	public Exception(String code, String msg, java.lang.Exception e) {
		this.code = code;
		this.msg = msg;
		initCause(e);
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
