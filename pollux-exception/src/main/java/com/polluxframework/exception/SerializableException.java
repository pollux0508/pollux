package com.polluxframework.exception;

/**
 * 序列化异常,不可以被继承
 * @author zhumin0508
 * created in  2018/5/10 9:37
 * modified By:
 */
public final class SerializableException extends BaseRuntimeException {

	private static final String CODE = "POLLUX000";

	public SerializableException() {
		super(CODE, "对象未实现序列化，请联系管理人员确认!");
	}
}
