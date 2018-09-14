package com.polluxframework.entity;

/**
 * @author zhumin0508
 * created in  2018/9/12 10:45
 * modified By:
 */
public class Version {
	protected String mainVersion;

	public Version(String mainVersion) {
		this.mainVersion = mainVersion;
	}


	public String getMainVersion() {
		return mainVersion;
	}

	public boolean matches(String version) {
		return version.equals(mainVersion);
	}
}
