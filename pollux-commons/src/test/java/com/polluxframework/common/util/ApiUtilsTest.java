package com.polluxframework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.common.entity.Model;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/11/9 15:46
 * modified By:
 */
public class ApiUtilsTest {

	@Test
	public void getModels() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ApiUtils.getModels("com.hnac.hzinfo");
		Map<String,Model> modelMap= ApiUtils.getModelMap();
		for (Map.Entry<String, Model> entry : modelMap.entrySet()) {
			System.out.println(objectMapper.writeValueAsString(entry.getValue()));
		}
	}

	@Test
	public void getAPIs() {
		ApiUtils.getAPIs("com.hnac.hzinfo");
	}
}