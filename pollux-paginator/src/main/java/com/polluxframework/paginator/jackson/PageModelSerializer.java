package com.polluxframework.paginator.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.polluxframework.paginator.entity.PageModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * create 2018/09/12 20:01
 * modified By
 * description 统一分页的特殊序列号
 */
public class PageModelSerializer extends JsonSerializer<PageModel> {
	ObjectMapper mapper;

	public PageModelSerializer() {
		this.mapper = new ObjectMapper();
	}

	public PageModelSerializer(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void serialize(PageModel value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		Map<String, Object> map = new HashMap<>(8);
		map.put("pageNo", value.getPageNo());
		map.put("pageSize", value.getPageSize());
		map.put("total", value.getTotal());
		map.put("totalPage", value.getTotalPage());
		map.put("rows", new ArrayList(value));
		mapper.writeValue(jsonGenerator, map);
	}
}
