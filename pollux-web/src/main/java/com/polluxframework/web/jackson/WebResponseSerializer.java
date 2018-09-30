package com.polluxframework.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.polluxframework.web.entity.WebResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 针对WebResponse的Json解析器
 *
 * @author zhumin0508
 * created in  2018/9/13 10:13
 * modified By:
 */
public class WebResponseSerializer extends JsonSerializer<WebResponse> {
	ObjectMapper mapper;

	public WebResponseSerializer() {
		this.mapper = new ObjectMapper();
	}

	public WebResponseSerializer(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void serialize(WebResponse value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		Map<String, Object> map = new HashMap<>(8);
		map.put("status", value.getStatus());
		map.put("code", value.getCode());
		map.put("msg", value.getMsg());
		map.put("data", value.getData());
		mapper.writeValue(jsonGenerator, map);
	}
}
