package com.polluxframework.test.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.test.util.UnitTestUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/11/7 13:16
 * modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/spring-*.xml"})
@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	public static void setObjectMapper(ObjectMapper objectMapper) {
		UnitTestUtils.buildJsonMapper(objectMapper);
	}

	public String post(String url) throws Exception {
		return post(url, null, null);
	}

	public String post(String url, MultiValueMap<String, String> params) throws Exception {
		return post(url, null, params);
	}

	public String post(String url, HttpSession httpSession, MultiValueMap<String, String> params) throws Exception {
		return UnitTestUtils.doPost(webApplicationContext, httpSession, MediaType.ALL, url, params);
	}

	public String post(String url, HttpSession httpSession, MediaType mediaType, MultiValueMap<String, String> params) throws Exception {
		return UnitTestUtils.doPost(webApplicationContext, httpSession, mediaType, url, params);
	}

	public String get(String url) throws Exception {
		return get(url, null, null);
	}

	public String get(String url, MultiValueMap<String, String> params) throws Exception {
		return get(url, null, params);
	}

	public String get(String url, HttpSession httpSession, MultiValueMap<String, String> params) throws Exception {
		return UnitTestUtils.doGet(webApplicationContext, httpSession, MediaType.ALL, url, params);
	}

	public String get(String url, HttpSession httpSession, MediaType mediaType, MultiValueMap<String, String> params) throws Exception {
		return UnitTestUtils.doGet(webApplicationContext, httpSession, mediaType, url, params);
	}

	public String fileUpload(String url, List<MockMultipartFile> files) throws Exception {
		return UnitTestUtils.fileUpload(webApplicationContext, null, url, files, null);
	}

	public String fileUpload(String url, List<MockMultipartFile> files, MultiValueMap<String, String> params) throws Exception {
		return UnitTestUtils.fileUpload(webApplicationContext, null, url, files, params);
	}

	public String fileUpload(String url, HttpSession httpSession, List<MockMultipartFile> files, MultiValueMap<String, String> params) throws Exception {
		return UnitTestUtils.fileUpload(webApplicationContext, httpSession, url, files, params);
	}
}
