package com.polluxframework.exception;

/**
 * @author zhumin0508
 * created in  2018/8/8 9:11
 * modified By:
 */
public class BaseException extends Exception implements IException {
	/**
	 * 异常代码
	 */
	private final String code;
	/**
	 * 异常信息
	 */
	private final String msg;

	public BaseException(String code, String msg) {
		this.code = code;
		this.msg = msg;
		initCause(new Throwable(msg));
	}

	public BaseException(String code, String msg, Exception e) {
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
