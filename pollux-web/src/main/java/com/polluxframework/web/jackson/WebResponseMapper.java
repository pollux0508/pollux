package com.polluxframework.web.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.polluxframework.web.entity.WebResponse;

/**
 * @author zhumin0508
 * created in  2018/9/30 8:23
 * modified By:
 */
public class WebResponseMapper extends ObjectMapper {

	public WebResponseMapper() {
		SimpleModule module = new SimpleModule("WebResponseJsonModule", new Version(1, 0, 0, null, null, null));
		module.addSerializer(WebResponse.class, new WebResponseSerializer(this));
		registerModule(module);
	}
}
