package com.polluxframework;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.polluxframework.paginator.entity.PageList;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author zhumin0508
 * created in  2018/9/30 8:28
 * modified By:
 */
public class PageModelMapper extends ObjectMapper {
	public PageModelMapper() {
		this(JsonInclude.Include.NON_NULL);
	}

	public PageModelMapper(boolean notNull) {
		if (notNull) {
			this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		} else {
			this.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		}

		this.init();
	}

	public PageModelMapper(JsonInclude.Include include) {
		if (include != null) {
			this.setSerializationInclusion(include);
		}

		this.init();
	}

	private void init() {
		SimpleModule module = new SimpleModule("WebResponseModule");

		module.addSerializer(PageList.class, new JsonSerializer<PageList>() {
			@Override
			public void serialize(PageList value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				Map<String, Object> map = new HashMap<>(8);
				map.put("pageNo", value.getPageNo());
				map.put("pageSize", value.getPageSize());
				map.put("total", value.getTotal());
				map.put("totalPage", value.getTotalPage());
				map.put("rows", new ArrayList(value));
				gen.writeObject(map);
			}
		});
		module.addSerializer(String.class, new JsonSerializer<String>() {
			@Override
			public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
				jsonGenerator.writeString(StringEscapeUtils.unescapeHtml4(value));
			}
		});
		List<Module> modules = findModules();
		modules.add(module);
		this.registerModules(modules);

		this.enableSimple();
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
				jsonGenerator.writeString("");
			}
		});
		this.setTimeZone(TimeZone.getDefault());
	}

	public void enableSimple() {
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
}