package com.polluxframework.paginator.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.polluxframework.paginator.entity.PageModel;

import java.io.IOException;

/**
 * @author zhumin0508
 * create 2018/09/12 20:01
 * modified By
 * description 统一分页的特殊序列号
 */
public class PageModelSerializer extends JsonSerializer<PageModel> {

    @Override
    public void serialize(PageModel value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"pageNo\":").append(value.getPageNo()).append(',');
        sb.append("\"pageSize\":").append(value.getPageSize()).append(',');
        sb.append("\"total\":").append(value.getTotal()).append(',');
        sb.append("\"totalPage\":").append(value.getTotalPage()).append(',');
        sb.append("\"rows\":");
        jsonGenerator.writeRawValue(sb.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String rowText = objectMapper.writeValueAsString(value.getRows());
        jsonGenerator.writeRawValue(rowText);
        jsonGenerator.writeRawValue("}");
    }
}
