package com.polluxframework.web.handler;

import com.polluxframework.exception.BaseRuntimeException;
import com.polluxframework.exception.SerializableException;
import com.polluxframework.web.constant.WebConstant;
import com.polluxframework.web.entity.WebResponse;
import com.polluxframework.web.utils.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhumin0508
 * created in  2018/6/29 14:36
 * modified By:
 */

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseRuntimeException.class)
	public WebResponse handlerPXRuntimeException(BaseRuntimeException e) {
		return ExceptionUtils.newFailure(e);
	}

	@ExceptionHandler(SerializableException.class)
	public WebResponse handlerPXSerializableException(SerializableException e) {
		return ExceptionUtils.newFailure(e);
	}

	@ExceptionHandler(Exception.class)
	public WebResponse handlerException(Exception e) {
		return ExceptionUtils.newFailure(WebConstant.DEFAULT_ERROR_CODE, WebConstant.DEFAULT_ERROR_MESSAGE, e.getCause().getMessage());
	}
}
