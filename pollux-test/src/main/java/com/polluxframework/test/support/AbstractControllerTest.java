package com.polluxframework.test.support;

import com.polluxframework.test.utils.WebTestSingleUtils;
import com.polluxframework.web.entity.WebResponse;
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


	public WebResponse post(String url) throws Exception {
		return post(url, null, null);
	}

	public WebResponse post(String url, MultiValueMap<String, String> params) throws Exception {
		return post(url, null, params);
	}

	public WebResponse post(String url, HttpSession httpSession, MultiValueMap<String, String> params) throws Exception {
		return WebTestSingleUtils.doPost(webApplicationContext, httpSession, url, params);
	}

	public WebResponse post(String url, HttpSession httpSession, MediaType mediaType, MultiValueMap<String, String> params) throws Exception {
		return WebTestSingleUtils.doPost(webApplicationContext, httpSession, mediaType, url, params);
	}

	public WebResponse get(String url) throws Exception {
		return get(url, null, null);
	}

	public WebResponse get(String url, MultiValueMap<String, String> params) throws Exception {
		return get(url, null, params);
	}

	public WebResponse get(String url, HttpSession httpSession, MultiValueMap<String, String> params) throws Exception {
		return WebTestSingleUtils.doGet(webApplicationContext, httpSession, url, params);
	}

	public WebResponse get(String url, HttpSession httpSession, MediaType mediaType, MultiValueMap<String, String> params) throws Exception {
		return WebTestSingleUtils.doGet(webApplicationContext, httpSession, mediaType, url, params);
	}

	public WebResponse fileUpload(String url, List<MockMultipartFile> files) throws Exception {
		return WebTestSingleUtils.fileUpload(webApplicationContext, null, url, files, null);
	}

	public WebResponse fileUpload(String url, List<MockMultipartFile> files, MultiValueMap<String, String> params) throws Exception {
		return WebTestSingleUtils.fileUpload(webApplicationContext, null, url, files, params);
	}

	public WebResponse fileUpload(String url, HttpSession httpSession, List<MockMultipartFile> files, MultiValueMap<String, String> params) throws Exception {
		return WebTestSingleUtils.fileUpload(webApplicationContext, httpSession, url, files, params);
	}
}
