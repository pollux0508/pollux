package com.polluxframework.paginator.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.polluxframework.paginator.entity.PageModel;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/30 8:15
 * modified By:
 */
public class PageModelMapper extends ObjectMapper {

	public PageModelMapper() {
		List<Module> modules = findModules();
		SimpleModule module = new SimpleModule("_pageModelJsonModule");
		module.addSerializer(PageModel.class, new PageModelSerializer(this));
		modules.add(module);
		registerModules(modules);
	}
}
