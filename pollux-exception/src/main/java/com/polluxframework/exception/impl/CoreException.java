package com.polluxframework.exception.impl;


import com.polluxframework.exception.IException;

/**
 * @author zhumin0508
 * created in  2018/5/10 9:37
 * modified By:
 */
public class CoreException extends RuntimeException implements IException {
	/**
	 * 异常代码
	 */
	private final String code;
	/**
	 * 异常信息
	 */
	private final String msg;

	public CoreException(String code, String msg) {
		this.code = code;
		this.msg = msg;
		initCause(new Throwable(msg));
	}

	public CoreException(String code, String msg, BusinessException e) {
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
