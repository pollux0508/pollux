package com.polluxframework.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/9/11 8:09
 * modified By:
 */
public class HttpRequestUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

	private HttpRequestUtils() {
	}

	/**
	 * 向指定URL发送GET方法的请求 请求参数为字符串型
	 *
	 * @param url   请求路径 允许携带参数
	 * @param param 请求参数 name1=value1&name2=value2
	 * @return 返回请求结果集
	 */
	public static String sendGet(String url, final String param) {
		return sendGet(url, buildNameValuePair(param));
	}

	/**
	 * 向指定URL发送GET方法的请求 请求参数为Map
	 *
	 * @param url   请求路径 允许携带参数
	 * @param param 请求参数
	 * @return 返回请求结果集
	 */
	public static String sendGet(String url, final Map<String, Object> param) {
		return sendGet(url, buildNameValuePair(param));

	}

	/**
	 * 向指定URL发送GET方法的请求 请求参数为List<NameValuePair>
	 *
	 * @param url    请求路径 允许携带参数
	 * @param params 请求参数
	 * @return 返回请求结果集
	 */
	public static String sendGet(String url, final List<NameValuePair> params) {
		try {
			URIBuilder builder = new URIBuilder(getURI(url));
			if (!params.isEmpty()) {
				builder.setParameters(params);
			}
			HttpGet request = new HttpGet(builder.build());
			return sendRequest(request);
		} catch (URISyntaxException e) {
			logger.warn("获取URI失败", e);
		}

		return null;
	}

	/**
	 * 向指定URL发送POST方法的请求 请求参数为字符串型
	 *
	 * @param url   请求路径 允许携带参数
	 * @param param 请求参数 name1=value1&name2=value2
	 * @return 返回请求结果集
	 */
	public static String sendPost(String url, String param) {
		return sendPost(url, buildNameValuePair(param));
	}

	/**
	 * 向指定URL发送POST方法的请求 请求参数为Map
	 *
	 * @param url   请求路径 允许携带参数
	 * @param param 请求参数
	 * @return 返回请求结果集
	 */
	public static String sendPost(String url, final Map<String, Object> param) {
		return sendPost(url, buildNameValuePair(param));
	}

	/**
	 * 向指定URL发送POST方法的请求 请求参数为List<NameValuePair>
	 *
	 * @param url    请求路径 允许携带参数
	 * @param params 请求参数
	 * @return 返回请求结果集
	 */
	public static String sendPost(String url, final List<NameValuePair> params) {
		try {
			HttpPost request = new HttpPost(getURI(url));
			if (!params.isEmpty()) {
				request.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
			}
			return sendRequest(request);
		} catch (URISyntaxException e) {
			logger.warn("获取URI失败", e);
		}
		return null;
	}

	/**
	 * 根据请求，获取返回结果集
	 *
	 * @param request get或post请求
	 * @return 返回请求结果集
	 */
	public static String sendRequest(HttpUriRequest request) {
		String result = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			setHeader(request);
			CloseableHttpResponse response = httpClient.execute(request);
			if (response != null) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
			} else {
				logger.warn("未获取到正常返回结果");
			}
		} catch (IOException e) {
			logger.error("发送请求响应失败", e);
		}
		return result;
	}

	/**
	 * 将字符串参数装换成HttpClient中能适用的参数列表
	 *
	 * @param param 字符串參數
	 * @return HttpClient中能适用的参数列表
	 */
	public static List<NameValuePair> buildNameValuePair(final String param) {
		List<NameValuePair> pairs = new ArrayList<>(8);
		if (StringUtils.isNotBlank(param)) {
			pairs = URLEncodedUtils.parse(param, StandardCharsets.UTF_8);
		}
		return pairs;

	}

	/**
	 * 将Map参数装换成HttpClient中能适用的参数列表
	 *
	 * @param param map參數
	 * @return HttpClient中能适用的参数列表
	 */
	public static List<NameValuePair> buildNameValuePair(final Map<String, Object> param) {
		List<NameValuePair> pairs = new ArrayList<>(8);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
		}
		return pairs;
	}

	private static URI getURI(String url) throws URISyntaxException {
		url = Args.notNull(url, "URL");
		try {
			URL url1 = new URL(url);
			return new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
		} catch (MalformedURLException e) {
			logger.error("创建URL请求失败", e);
		}
		return new URI(url);
	}

	/**
	 * @param request 给请求设置请求头属性
	 */
	private static void setHeader(HttpRequest request) {
		request.setHeader("accept", "*/*");
		request.setHeader("Connection", "keep-alive");
		request.setHeader("Accept-Charset", "UTF-8");
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
	}
}
