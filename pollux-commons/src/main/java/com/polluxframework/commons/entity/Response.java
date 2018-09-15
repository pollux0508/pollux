package com.polluxframework.commons.entity;

/**
 * @author zhumin0508
 * created in  2018/9/15 11:24
 * modified By:
 */
public class Response extends Parameter {

	@Override
	public String getName() {
		return "data>>" + super.getName();
	}
}
