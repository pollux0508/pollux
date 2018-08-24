package com.polluxframework.datasource;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/8/17 9:50
 * modified By:
 */
public class PxDataSource  extends AbstractRoutingDataSource {
	private static final Logger LOGGER = LoggerFactory.getLogger(PxDataSource.class);

	private Map<Object, Object> targetCache = Maps.newHashMap();

	private DataSource defaultTargetDataSource;

	public PxDataSource(DataSource defaultTargetDataSource) {
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
