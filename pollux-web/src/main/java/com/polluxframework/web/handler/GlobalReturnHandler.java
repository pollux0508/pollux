package com.polluxframework.web.handler;

import com.polluxframework.web.constant.WebConstant;
import com.polluxframework.web.entity.WebResponse;
import com.polluxframework.web.utils.ExceptionUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

/**
 * @author zhumin0508
 * created in  2018/6/29 15:27
 * modified By:
 */
@ControllerAdvice
public class GlobalReturnHandler implements ResponseBodyAdvice<Object> {
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
	}

	@Override
	public Object beforeBodyWrite(Object result, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (result == null) {
			return ExceptionUtils.newFailure(WebConstant.DEFAULT_ERROR_CODE, "获取数据失败");
		}
		if (result instanceof WebResponse || result instanceof String) {
			return result;
		} else if (result instanceof File) {
			return result;
		} else {
			return new WebResponse("获取数据成功",result);
		}
	}
}
