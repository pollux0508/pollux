package com.polluxframework.web.exception;

import com.polluxframework.commons.utils.Bean2MapUtils;
import com.polluxframework.exception.IException;
import com.polluxframework.web.constant.WebConstant;
import com.polluxframework.web.entity.WebResponse;
import com.polluxframework.web.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:14
 * modified By:
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView result;
		WebResponse error = new WebResponse();
		if (exception instanceof IException) {
			IException iException = (IException) exception;
			error.setCode(iException.getCode());
			error.setMsg(iException.getMsg());
		} else {
			error.setCode(WebConstant.DEFAULT_ERROR_CODE);
			error.setMsg(WebConstant.DEFAULT_ERROR_MESSAGE);
		}
		logger.error(exception.getMessage());
		if (RequestUtils.isAjaxRequest(request)) {
			result = new ModelAndView(new MappingJackson2JsonView());
			result.addAllObjects(Bean2MapUtils.beanToMap(error));
		} else {
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw));
			String message = sw.toString();
			if (StringUtils.isEmpty(message)) {
				message = exception.getCause().getMessage();
			}
			error.setData(message);
			result = new ModelAndView(WebConstant.ERROR_PAGE);
			result.addObject(WebConstant.ERROR, Bean2MapUtils.beanToMap(error));
		}
		return result;
	}
}
