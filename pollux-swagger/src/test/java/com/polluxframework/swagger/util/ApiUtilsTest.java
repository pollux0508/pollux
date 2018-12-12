package com.polluxframework.swagger.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.swagger.entity.Operation;
import com.polluxframework.web.support.JacksonMapper;
import org.junit.Test;

import java.util.List;


/**
 * @author zhumin0508
 * created in  2018/11/26 15:04
 * modified By:
 */
public class ApiUtilsTest {

	@Test
	public void getModels() throws Exception {
		ApiUtils.getModels("com");
		ObjectMapper objectMapper = new JacksonMapper();
		System.out.println(objectMapper.writeValueAsString(ApiUtils.getModelMap()));

	}

	@Test
	public void getAPIs() throws Exception {
		ApiUtils.getModels("com");
		List<Operation> operations =  ApiUtils.getAPIs("com");
		ObjectMapper objectMapper = new JacksonMapper();
		System.out.println(objectMapper.writeValueAsString(operations));
	}
}