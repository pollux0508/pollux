package com.polluxframework.common.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author zhumin0508
 * created in  2018/11/1 10:17
 * modified By:
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	private Properties properties;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
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
		if (StringUtils.isEmpty(result) && properties.containsKey(key)) {
			result = properties.getProperty(key);
		}
		return result == null ? StringUtils.EMPTY : result;
	}

	public String getValue(String key, String defaultValue) {
		String result = getValue(key);
		return StringUtils.isEmpty(result) ? defaultValue : result;
	}
}
