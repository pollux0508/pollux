package com.polluxframework.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.exception.SerializableException;
import com.polluxframework.test.constant.RequestTypeEnum;
import com.polluxframework.test.entity.TestParam;
import com.polluxframework.web.controller.BaseController;
import com.polluxframework.web.entity.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/**
 * 用于做单元测试时参数需要一个request或者session时使用
 *
 * @author zhumin0508
 * created in  2018/5/9 7:56
 * modified By:
 */
public class WebTestSingleUtils {
	private static final Logger logger = LoggerFactory.getLogger(WebTestSingleUtils.class);

	private WebTestSingleUtils() {

	}

	static MockHttpServletRequest request;

	/**
	 * 获取测试时需要使用的请求
	 * request的是一个单例模式，测试过程这种整个request是同一个请求
	 *
	 * @return 返回一个request
	 */
	public static HttpServletRequest getSingleRequest() {
		if (request == null) {
			request = new MockHttpServletRequest();
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(Integer.MAX_VALUE);
		}
		return request;
	}

	/**
	 * 获取测试时需要使用的请求
	 * request的是一个非单例模式，测试过程这种整个request不是同一个请求
	 *
	 * @return 返回一个request
	 */
	public static HttpServletRequest getNoSingleRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(Integer.MAX_VALUE);
		return request;
	}

	/**
	 * 设置session值
	 *
	 * @param name      session名
	 * @param attribute session值
	 */
	public static void setAttributeForSession(String name, Object attribute) {
		getSingleRequest();
		if (attribute instanceof Serializable) {
			HttpSession session = request.getSession();
			session.setAttribute(name, attribute);
		} else {
			throw new SerializableException();
		}
	}

	/**
	 * 获取session
	 *
	 * @return 返回session
	 */
	public static HttpSession getSession() {
		getSingleRequest();
		return request.getSession();
	}


	/**
	 * 测试Controller层的工具
	 *
	 * @param controller 控制层
	 * @param url        要测试的URL
	 * @param param      测试需要提供的参数
	 * @return 返回控制层数据
	 * @throws Exception 交易异常
	 */
	public static String doAction(BaseController controller, String url, Map<String, Object> param) throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post(url);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			if (entry.getValue() != null) {
				Object temp = entry.getValue();
				if (temp instanceof String) {
					mockHttpServletRequestBuilder.param(entry.getKey(), (String) temp);
				} else if (temp instanceof List) {
					List<Object> list = (List<Object>) temp;
					String[] values = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Object bean = list.get(i);
						if (bean instanceof String) {
							values[i] = (String) bean;
						} else {
							values[i] = bean.toString();
						}
					}
					mockHttpServletRequestBuilder.param(entry.getKey(), values);
				} else {
					mockHttpServletRequestBuilder.param(entry.getKey(), temp.toString());
				}
			}
		}
		MvcResult mvcResult = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
		if (mvcResult != null) {
			return mvcResult.getResponse().getContentAsString();
		} else {
			return "";
		}
	}

	public static WebResponse doAction(WebApplicationContext applicationContext, ObjectMapper objectMapper, String url, RequestTypeEnum typeEnum, TestParam... params) throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) applicationContext).build();
		return doAction(mockMvc, objectMapper, url, typeEnum, params);
	}

	private static WebResponse doAction(MockMvc mockMvc, ObjectMapper objectMapper, String url, RequestTypeEnum typeEnum, TestParam... params) throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder;
		switch (typeEnum) {
			case GET:
				mockHttpServletRequestBuilder = get(url);
				break;
			case POST:
				mockHttpServletRequestBuilder = post(url);
				break;
			default:
				mockHttpServletRequestBuilder = post(url);
		}
		if (params != null && params.length > 0) {
			for (TestParam param : params) {
				mockHttpServletRequestBuilder.param(param.getName(), param.getValues());
			}
		}
		MvcResult mvcResult = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
		if (mvcResult != null) {
			return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), WebResponse.class);
		} else {
			return null;
		}
	}
}
