package com.polluxframework.test.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.common.constant.Constants;
import com.polluxframework.exception.RuntimeException;
import com.polluxframework.exception.SerializableException;
import com.polluxframework.web.entity.WebResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.TimeZone;

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

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setTimeZone(TimeZone.getDefault());
	}

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


	public static WebResponse doPost(WebApplicationContext applicationContext, String url, MultiValueMap<String, String> params) throws Exception {
		return doPost(applicationContext, null, url, params);
	}

	public static WebResponse doPost(WebApplicationContext applicationContext, HttpSession session, String url, MultiValueMap<String, String> params) throws Exception {
		return doPost(applicationContext, session, MediaType.ALL, url, params);
	}

	public static WebResponse doPost(WebApplicationContext applicationContext, HttpSession session, MediaType mediaType, String url, MultiValueMap<String, String> params) throws Exception {
		return doAction(applicationContext, post(formatUrl(url)), session, mediaType, params);
	}


	public static WebResponse doGet(WebApplicationContext applicationContext, String url, MultiValueMap<String, String> params) throws Exception {
		return doGet(applicationContext, null, url, params);
	}

	public static WebResponse doGet(WebApplicationContext applicationContext, HttpSession session, String url, MultiValueMap<String, String> params) throws Exception {
		return doGet(applicationContext, session, MediaType.ALL, url, params);
	}

	public static WebResponse doGet(WebApplicationContext applicationContext, HttpSession session, MediaType mediaType, String url, MultiValueMap<String, String> params) throws Exception {
		return doAction(applicationContext, get(formatUrl(url)), session, mediaType, params);
	}

	public static WebResponse doAction(WebApplicationContext applicationContext, MockHttpServletRequestBuilder request, HttpSession session, MediaType mediaType, MultiValueMap<String, String> params) throws Exception {
		request.contentType(mediaType);
		setSession(request, session);
		setParameter(request, params);
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		return getResponse(mockMvc, request);
	}


	private static WebResponse getResponse(MockMvc mockMvc, MockHttpServletRequestBuilder request) throws Exception {
		MvcResult mvcResult = mockMvc.perform(request).andReturn();
		if (mvcResult != null) {
			String result = mvcResult.getResponse().getContentAsString();
			if (!StringUtils.isBlank(result)) {
				return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), WebResponse.class);
			}
		}
		throw new RuntimeException("000", "无返回结果集");
	}

	/**
	 * 格式化URL
	 *
	 * @param url URL请求
	 * @return 格式化后的URL
	 */
	public static String formatUrl(String url) {
		String result = url;
		if (StringUtils.isBlank(url)) {
			throw new RuntimeException("000", "请求url不能为空");
		}
		if (!url.startsWith(Constants.SLASH)) {
			result = Constants.SLASH + url;
		}
		return result;
	}

	/**
	 * 给请求build设置session
	 *
	 * @param request 请求build
	 * @param session MockHttpSession
	 */
	private static void setSession(MockHttpServletRequestBuilder request, HttpSession session) {
		if (session != null) {
			request.session((MockHttpSession) session);
		}
	}

	/**
	 * 给文件上传请求设置文件
	 *
	 * @param request 文件上传请求
	 * @param files   文件
	 */
	private static void setMultipartFile(MockMultipartHttpServletRequestBuilder request, List<MockMultipartFile> files) {
		if (files.isEmpty()) {
			throw new RuntimeException("000", "不能传空文件");
		}
		for (MockMultipartFile file : files) {
			request.file(file);
		}
	}

	/**
	 * 给请求设置参数
	 *
	 * @param request 请求
	 * @param params  参数
	 * @throws JsonProcessingException json格式化异常
	 */
	private static void setParameter(MockHttpServletRequestBuilder request, MultiValueMap<String, String> params) throws JsonProcessingException {
		if (params != null) {
			String paramStr = objectMapper.writeValueAsString(params);
			request.content(paramStr);
			if (!params.isEmpty()) {
				request.params(params);
			}
		}
	}


	public static WebResponse fileUpload(WebApplicationContext applicationContext, HttpSession session, String url, List<MockMultipartFile> files, MultiValueMap<String, String> params) throws Exception {
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload(url);
		setMultipartFile(request, files);
		return doAction(applicationContext, request, session, MediaType.MULTIPART_FORM_DATA, params);
	}


	public static MockMultipartFile createMockMultipartFile(String name, String path) throws IOException {
		if (StringUtils.isEmpty(name)) {
			throw new RuntimeException("000", "文件参数名不能为空");
		}
		if (StringUtils.isEmpty(path)) {
			throw new RuntimeException("000", "文件路径不能为空");
		}
		File file = new File(path);
		return createMockMultipartFile(name, file);
	}

	public static MockMultipartFile createMockMultipartFile(String name, File file) throws IOException {
		if (!file.exists()) {
			throw new RuntimeException("000", "文件不存在");
		} else if (file.isDirectory()) {
			throw new RuntimeException("000", "不能是文件目录");
		} else {
			String contentType = Files.probeContentType(file.toPath());
			return new MockMultipartFile(name, file.getName(), contentType, new FileInputStream(file));
		}
	}
}
