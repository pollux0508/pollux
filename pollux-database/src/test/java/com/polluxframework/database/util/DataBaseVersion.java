package com.polluxframework.database.util;

import com.polluxframework.database.annotation.DBAnnotation;
import com.polluxframework.database.entity.IModuleVersion;

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
	public List<String> prevModules() {
		return null;
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
