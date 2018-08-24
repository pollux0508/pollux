package com.polluxframework.core.config;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/5/10 10:37
 * modified By:
 */
@Component
public class Global {
	@Autowired
	private PropertyConfigurer loader;
	private static Global global;
	private static Map<String, String> map = Maps.newHashMap();

	@PostConstruct
	public void init() {
		global = this;
		global.loader = this.loader;
	}

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = global.loader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}


	/**
	 * 获取配置 如果都为空字符串则返回默认值
	 */
	public static String getConfig(String key,String defaultValue) {
		String value = map.get(key);
		if (value == null) {
			value = global.loader.getProperty(key,defaultValue);
			map.put(key, value);
		}
		return value;
	}
}
