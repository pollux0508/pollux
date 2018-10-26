package com.polluxframework.paginator.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.polluxframework.paginator.entity.PageList;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/30 8:15
 * modified By:
 */
public class PageListMapper extends ObjectMapper {

	public PageListMapper() {
		List<Module> modules = findModules();
		SimpleModule module = new SimpleModule("_pageListJsonModule");
		module.addSerializer(PageList.class, new PageListSerializer(this));
		modules.add(module);
		registerModules(modules);
	}
}
