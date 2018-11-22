package com.polluxframework.common.support;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/11/1 10:16
 * modified By:
 */
@Component
public class Global {

	private final PropertyConfigurer loader;

	private static Global global;

	/**
	 * 获取配置 用Global获取配置文件信息，只是为了加快获取速度，毕竟Properties有很多内容不一定全部需要缓存过来
	 */
	private static Map<String, String> map = Maps.newHashMap();

	@Autowired
	public Global(PropertyConfigurer loader) {
		this.loader = loader;
	}

	@PostConstruct
	public void init() {
		global = this;
	}

	/**
	 * 获取配置
	 */
	public static String getValue(String key) {
		String value = map.get(key);
		if (value == null) {
			value = global.loader.getValue(key);
			map.put(key, value);
		}
		return value;
	}

	/**
	 * 获取配置
	 */
	public static String getValue(String key, String defaultValue) {
		String value = map.get(key);
		if (value == null) {
			value = global.loader.getValue(key, defaultValue);
			map.put(key, value);
		}
		return value;
	}
}
