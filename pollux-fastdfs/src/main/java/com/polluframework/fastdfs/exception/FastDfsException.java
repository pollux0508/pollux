package com.polluframework.fastdfs.exception;

import com.polluxframework.exception.BaseException;

/**
 * @author zhumin0508
 * created in  2018/10/31 10:18
 * modified By:
 */
public class FastDfsException extends BaseException {

	public FastDfsException(String code, String msg) {
		super(code, msg);
	}

	public FastDfsException(String code, String msg, Exception e) {
		super(code, msg, e);
	}

}
