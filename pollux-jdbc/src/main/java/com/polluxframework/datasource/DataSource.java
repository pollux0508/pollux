package com.polluxframework.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/8/17 9:50
 * modified By:
 */
public class DataSource extends AbstractRoutingDataSource {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSource.class);

	private Map<Object, Object> targetCache = new HashMap<>();

	private javax.sql.DataSource defaultTargetDataSource;

	public DataSource(javax.sql.DataSource defaultTargetDataSource) {
		this.defaultTargetDataSource = defaultTargetDataSource;
	}

	@PostConstruct
	public void init(){

	}

	@Override
	protected Object determineCurrentLookupKey() {
		return null;
	}

	public Map<Object, Object> getTargetCache() {
		return targetCache;
	}

}
