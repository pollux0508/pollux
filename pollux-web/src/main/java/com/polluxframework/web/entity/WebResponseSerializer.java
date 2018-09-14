package com.polluxframework.web.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 针对WebResponse的Json解析器
 *
 * @author zhumin0508
 * created in  2018/9/13 10:13
 * modified By:
 */
public class WebResponseSerializer extends JsonSerializer<WebResponse> {
	@Override
	public void serialize(WebResponse value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		StringBuilder sb = new StringBuilder();
		sb.append('{');
		sb.append("\"status\":").append(value.getStatus()).append(",");
		sb.append("\"code\":\"").append(value.getCode()).append("\",");
		sb.append("\"msg\":\"").append(value.getMsg()).append("\",");
		sb.append("\"data\":");
		jsonGenerator.writeRawValue(sb.toString());

		String rowText = objectMapper.writeValueAsString(value.getData());

		jsonGenerator.writeRawValue(rowText);
		jsonGenerator.writeRawValue("}");
	}
}
