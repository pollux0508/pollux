package com.polluxframework.version.entity;

import com.polluxframework.version.annotation.DBAnnotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/26 15:18
 * modified By:
 */
@DBAnnotation
public class DataBaseVersion implements IModuleVersion {
	protected static final List<String> HISTORY_VERSIONS = new ArrayList<>();
	protected static final String ROOT_PATH = "com/polluxframework/db";

	static {
		HISTORY_VERSIONS.add("1.0.0");
		HISTORY_VERSIONS.add("1.0.1");
		HISTORY_VERSIONS.add("1.0.2");
	}

	@Override
	public String getSQLDirectory() {
		return ROOT_PATH;
	}

	@Override
	public String getModule() {
		return "base";
	}

	@Override
	public String curVersion() {
		return "1.0.2";
	}

	@Override
	public List<String> getHistoryVersion() {
		return HISTORY_VERSIONS;
	}

}
