package com.polluxframework.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.polluxframework.common.constant.DateEnum;
import com.polluxframework.exception.BaseRuntimeException;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @author zhumin0508
 * created in  2018/10/31 17:20
 * modified By:
 */
public class JacksonMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[\\d]*$");

	public JacksonMapper() {
		this(JsonInclude.Include.NON_NULL);
	}

	public JacksonMapper(JsonInclude.Include include) {
		// 设置输出时包含属性的风格
		if (include != null) {
			this.setSerializationInclusion(include);
		}
		init();
	}

	private void init() {
		// 允许单引号、允许不带引号的字段名称
		this.enableSimple();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>() {
					@Override
					public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
						jsonGenerator.writeString(StringEscapeUtils.unescapeHtml4(value));
					}
				}).addDeserializer(Date.class, new JsonDeserializer<Date>() {
					@Override
					public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
						try {
							String text = jsonParser.getText();
							if (NUMBER_PATTERN.matcher(text).matches()) {
								long time = Long.valueOf(text);
								return new Date(time);
							} else {
								return DateUtils.parseDate(text, DateEnum.getAllDateFormat());
							}
						} catch (IOException e) {
							throw new BaseRuntimeException("500", "获取参数有误");
						} catch (ParseException e1) {
							throw new BaseRuntimeException("500", "参数传递有误，这里需要一个日期类型的字符串！");
						}

					}
				})
		);
		// 设置时区
		this.setTimeZone(TimeZone.getDefault());
	}

	public void enableSimple() {
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
}
