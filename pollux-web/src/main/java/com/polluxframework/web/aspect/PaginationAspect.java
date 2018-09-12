package com.polluxframework.web.aspect;

import com.polluxframework.commons.entity.Pagination;
import com.polluxframework.commons.utils.SessionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhumin0508
 * created in  2018/5/11 11:00
 * modified By:
 */
@Aspect
public class PaginationAspect {
	private static final Logger logger = LoggerFactory.getLogger(PaginationAspect.class);

	@Pointcut("@annotation(com.polluxframework.commons.annotation.PageAnnotation)")
	public void pagination() {
		//只是为了声明切入点
	}

	@After("pagination()")
	public void doBefore(JoinPoint point) {
		logger.debug("先获取参数中的分页参数!");
		Object[] args = point.getArgs();
		Pagination pagination = new Pagination();
		for (Object obj : args) {
			if (obj instanceof Pagination) {
				pagination = (Pagination) obj;
			}
		}
		logger.debug("将分页数据保存到session中");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SessionUtils.setPagination(request, pagination);
	}
}
