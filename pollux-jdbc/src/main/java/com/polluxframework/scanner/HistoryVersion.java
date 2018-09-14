package com.polluxframework.scanner;

import com.polluxframework.entity.Version;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/12 11:21
 * modified By:
 */
public final class HistoryVersion {
	public static final String VERSION = "1.0.3";

	private static HistoryVersion instance = new HistoryVersion();

	private HistoryVersion() {
		versions.add(new Version("1.0.0"));
		versions.add(new Version("1.0.1"));
		versions.add(new Version("1.0.2"));
		versions.add(new Version(VERSION));
	}

	private List<Version> versions = new ArrayList<>();

	public static List<Version> getVersions() {
		return instance.versions;
	}
}
