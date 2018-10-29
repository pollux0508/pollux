package com.polluxframework.web.handler;

import com.polluxframework.exception.BaseRuntimeException;
import com.polluxframework.exception.SerializableException;
import com.polluxframework.web.entity.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhumin0508
 * created in  2018/6/29 14:36
 * modified By:
 */

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getPackage().getName());

	@ExceptionHandler(BaseRuntimeException.class)
	public WebResponse handlerPXRuntimeException(BaseRuntimeException e) {
		logger.error("运行异常,错误码:{}，错误信息：{}", e.getCode(), e.getMsg(), e);
		return WebResponse.error(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(SerializableException.class)
	public WebResponse handlerPXSerializableException(SerializableException e) {
		logger.error("运行异常,错误码:{}，错误信息：{}", e.getCode(), e.getMsg(), e);
		return WebResponse.error(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public WebResponse handlerException(Exception e) {
		logger.error("运行异常", e);
		return WebResponse.error();
	}
}
