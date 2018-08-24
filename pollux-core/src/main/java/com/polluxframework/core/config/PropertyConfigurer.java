package com.polluxframework.core.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author zhumin0508
 * created in  2018/5/10 10:40
 * modified By:
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	private Properties properties;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props){
		super.processProperties(beanFactoryToProcess, props);
		properties = new Properties();
		properties.putAll(props);
	}

	/**
	 * 获取Key对应配置值
	 *
	 * @param key 获取值的key
	 * @return 取出String类型的配置值，以System的Property优先 ,取不到返回空字符串
	 */
	public String getValue(String key) {
		String result = System.getProperty(key);
		if (result != null) {
			return result;
		}
		if (properties.containsKey(key)) {
			return properties.getProperty(key);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 获取Key对应配置值
	 *
	 * @param key 获取值的key
	 * @return 取出String类型的配置值，以System的Property优先
	 */
	public String getProperty(String key) {
		return getValue(key);
	}


	/**
	 *  获取Key对应配置值，如果为空，返回默认值
	 *
	 * @param key          获取值的key
	 * @param defaultValue 默认值
	 * @return 取出String类型的配置值，以System的Property优先.如果都为空字符串则返回默认值
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return StringUtils.isEmpty(value) ? defaultValue : value;
	}
}
