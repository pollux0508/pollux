package com.polluxframework.web.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.commons.constant.Constants;
import com.polluxframework.web.constant.WebConstant;
import com.polluxframework.web.entity.WebResponse;
import com.polluxframework.web.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.polluxframework.commons.utils.StringUtils.isContains;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:42
 * modified By:
 */
public class LoginFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	private static final String FILTER_FIELDS = "include";
	private static final String UNFILTERED_FIELDS = "ignore";
	private static final String FILTER_ENABLED = "enable";
	private static final String REDIRECT_PATH = "redirect";
	private static final String FILTER_SEPARATOR = ",";
	private static final String DEFAULT_REDIRECT_PATH = "index";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final int DEFAULT_PORT = 80;

	private Set<String> includeSet = new HashSet<>(8);
	private Set<String> ignoreSet = new HashSet<>(8);
	private String redirect;
	private boolean isValid = true;

	@Override
	public void init(FilterConfig filterConfig) {
		ignoreSet.add(DEFAULT_REDIRECT_PATH);
		ignoreSet.add(LOGIN);
		ignoreSet.add(LOGOUT);
		String include = filterConfig.getInitParameter(FILTER_FIELDS);
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(include)) {
			logger.debug("需要拦截的后缀是：{}", include);
			String[] includes = include.split(FILTER_SEPARATOR);
			includeSet=new HashSet<>(Arrays.asList(includes));
		}

		String ignore = filterConfig.getInitParameter(UNFILTERED_FIELDS);
		if (StringUtils.isNotEmpty(ignore)) {
			logger.debug("需要忽略的后缀是：{}", ignore);
			String[] ignores = ignore.split(FILTER_SEPARATOR);
			ignoreSet.addAll(new HashSet<>(Arrays.asList(ignores)));
		}

		redirect = filterConfig.getInitParameter(REDIRECT_PATH);
		if (StringUtils.isEmpty(redirect)) {
			redirect = DEFAULT_REDIRECT_PATH;
			logger.debug("没有配置默认跳转的页面，配置默认跳转页面为：{}", this.redirect);
		}

		String valid = filterConfig.getInitParameter(FILTER_ENABLED);
		if (StringUtils.isNotEmpty(valid)) {
			logger.debug("是否生效：{}", valid);
			isValid = Boolean.parseBoolean(valid);
		}
		logger.debug("初始化登录过滤器结束");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!isValid) {
			chain.doFilter(request, response);
			return;
		}
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			logger.error("仅支持http请求");
			throw new ServletException("仅支持http请求");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//对根目录不进行拦截
		String url = httpRequest.getRequestURL().toString();
		String basePath = getRootPath(httpRequest);
		if (basePath.equalsIgnoreCase(url)) {
			chain.doFilter(request, response);
			return;
		}
		// 只对指定过滤参数后缀进行过滤
		if (!(includeSet.isEmpty() || isContains(url, includeSet))) {
			logger.debug("请求不包含要拦截的字段，不会进行拦截");
			chain.doFilter(request, response);
			return;
		}
		// 只对指定过滤参数后缀不进行过滤
		if (ignoreSet.isEmpty() || isContains(url, ignoreSet)) {
			logger.debug("请求包含要忽略拦截的字段，不会进行拦截");
			chain.doFilter(request, response);
			return;
		}
		//session 验证
		HttpSession session = httpRequest.getSession(false);
		if (session != null) {
			String user = (String) session.getAttribute(Constants.CURRENT_USER_ID);
			if (StringUtils.isEmpty(user)) {
				filterRedirect(httpRequest, (HttpServletResponse) response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			filterRedirect(httpRequest, (HttpServletResponse) response);
		}
	}

	/**
	 * 获取请求的根目录
	 */
	private String getRootPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String port = request.getServerPort() == DEFAULT_PORT ? StringUtils.EMPTY : StringUtils.EMPTY + request.getServerPort();
		return request.getScheme() + Constants.COLON + Constants.DOUBLE_SLASH + request.getServerName() + port + path + Constants.SLASH;
	}

	/**
	 * 跳转到指定页面
	 */
	private void filterRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (RequestUtils.isAjaxRequest(request)) {
			response.setHeader(WebConstant.CONTENT_TYPE, WebConstant.JSON_RESPONSE_CONTENT_TEXT);
			OutputStream out = response.getOutputStream();
			WebResponse pxWebResponse = new WebResponse();
			pxWebResponse.setFailure(WebConstant.NOT_LOGGED_CODE, WebConstant.NOT_LOGGED_MESSAGE);
			ObjectMapper mapper = new ObjectMapper();
			String massage = mapper.writeValueAsString(pxWebResponse);
			logger.debug("需要返回json的请求，检查未通过则返回:{}", massage);
			out.write(massage.getBytes(WebConstant.JSON_CHARSET));
			out.flush();
			out.close();
		} else {
			response.setContentType("text/html; charset=UTF-8");
			String target = getRootPath(request) + redirect;
			logger.debug("即将跳转到:{}", target);
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script language=\"javascript\">");
			out.println("window.top.location.href=" + "\"" + target + "\"");
			out.println("</script>");
			out.println("</html>");
			out.flush();
			out.close();
		}
	}

	@Override
	public void destroy() {
		logger.debug("销毁登录过滤器");
	}
}
