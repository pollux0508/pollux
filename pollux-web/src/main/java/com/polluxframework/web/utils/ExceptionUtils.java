package com.polluxframework.web.utils;

import com.polluxframework.exception.IException;
import com.polluxframework.web.entity.WebResponse;

/**
 * @author zhumin0508
 * created in  2018/6/29 14:54
 * modified By:
 */
public class ExceptionUtils {
	private ExceptionUtils(){

	}

	public static WebResponse newFailure(IException e){
		WebResponse result =	new WebResponse(e.getCode(),e.getMsg());
		result.setFailure();
		return result;
	}

	public static WebResponse newFailure(String code, String msg){
		WebResponse result =	new WebResponse(code,msg);
		result.setFailure();
		return result;
	}

	public static WebResponse newFailure(String code, String msg, String detail){
		WebResponse result =	new WebResponse(code,msg);
		result.setFailure();
		result.setData(detail);
		return result;
	}
}
