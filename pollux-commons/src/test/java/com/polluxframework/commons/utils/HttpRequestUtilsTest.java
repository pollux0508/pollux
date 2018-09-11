package com.polluxframework.commons.utils;

import org.junit.Assert;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/9/11 9:59
 * modified By:
 */
public class HttpRequestUtilsTest {

	@Test
	public void sendGet() {
		String city = URLEncoder.encode("北京");
		String url = "https://www.apiopen.top/weatherApi?city=" + city;
		Assert.assertNotNull(HttpRequestUtils.sendGet(url, ""));

		url = "https://www.apiopen.top/weatherApi";
		Assert.assertNotNull(HttpRequestUtils.sendGet(url, "city=" + city));

		Map<String, Object> param = new HashMap<>();
		url = "https://www.apiopen.top/weatherApi?city=" + city;
		Assert.assertNotNull(HttpRequestUtils.sendGet(url, param));

		url = "https://www.apiopen.top/weatherApi";
		param.put("city", "北京");
		Assert.assertNotNull(HttpRequestUtils.sendGet(url, param));

		url = "http://ping.pinyin.sogou.com/gender.gif?prob0=0.500000&prob1=0.500000&ppversion=3.1.0.2102&passport=&h=DB25D66ACCCE27F2597B12965F264385&v=9.1.0.2589&r=6990_sogou_pinyin_8.7.0.1657_6990";
		Assert.assertEquals("", HttpRequestUtils.sendGet(url, ""));

		url = "pinyin_8.7.0.1657_6990";
		Assert.assertNull(HttpRequestUtils.sendGet(url, ""));

		url = "http:localhost s: 8080";
		Assert.assertNull(HttpRequestUtils.sendGet(url, ""));
	}

	@Test
	public void sendPost() {
		String url = "https://www.apiopen.top/login";
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, "key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123456"));

		url = "https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123456";
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, ""));

		url = "https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5";
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, "phone=13594347817&passwd=123456"));

		Map<String, Object> param = new HashMap<>();
		url = "https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123456";
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, param));

		url = "https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817";
		param = new HashMap<>();
		param.put("passwd", "123456");
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, param));

		url = "https://www.apiopen.top/login";
		param = new HashMap<>();
		param.put("phone", "13594347817");
		param.put("key", "00d91e8e0cca2b76f515926a36db68f5");
		param.put("passwd", "123456");
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, param));

		url = "http://api.map.baidu.com/rectify/v1/track?rectify_option=need_mapmatch:0|transport_mode:auto|denoise_grade:1|vacuate_grade:1";
		param = new HashMap<>();
		param.put("coord_type_output", "bd09ll");
		param.put("key", "00d91e8e0cca2b76f515926a36db68f5");
		param.put("ak", "apkGsCG8954cnS6BgnolN5bs375NLM4O");
		param.put("point_list", "[{\"longitude\":112.873316,\"latitude\":28.256236,\"loc_time\":1536506401,\"coord_type_input\":\"bd09ll\"},\n" +
				"{\"longitude\":112.877846,\"latitude\":28.232756,\"loc_time\":1536507869,\"coord_type_input\":\"bd09ll\"}]");
		Assert.assertNotNull(HttpRequestUtils.sendPost(url, param));

		url = "http:localhost s: 8080";
		Assert.assertNull(HttpRequestUtils.sendPost(url, ""));

		url = "http://www.abc.com/selectusr.php";
		Assert.assertNull(HttpRequestUtils.sendPost(url, ""));

	}
}