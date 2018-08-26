package com.polluxframework.web.handler;

import com.polluxframework.exception.BaseRuntimeException;
import com.polluxframework.exception.SerializableException;
import com.polluxframework.web.constant.WebConstant;
import com.polluxframework.web.entity.WebResponse;
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

    @ExceptionHandler(BaseRuntimeException.class)
    public WebResponse handlerPXRuntimeException(BaseRuntimeException e) {
        WebResponse webResponse = new WebResponse(e.getCode(),e.getMsg());
        webResponse.setStatus(WebConstant.RESPONSE_STATUS_FAIL);
        return webResponse;
    }

    @ExceptionHandler(SerializableException.class)
    public WebResponse handlerPXSerializableException(SerializableException e) {
        WebResponse webResponse = new WebResponse(e.getCode(),e.getMsg());
        webResponse.setStatus(WebConstant.RESPONSE_STATUS_FAIL);
        return webResponse;
    }

    @ExceptionHandler(Exception.class)
    public WebResponse handlerException(Exception e) {
        WebResponse webResponse = new WebResponse(WebConstant.DEFAULT_ERROR_CODE,WebConstant.DEFAULT_ERROR_MESSAGE,e.getCause());
        webResponse.setStatus(WebConstant.RESPONSE_STATUS_FAIL);
        return webResponse;
    }
}
