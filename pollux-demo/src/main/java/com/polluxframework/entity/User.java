package com.polluxframework.entity;

/**
 * @author zhumin0508
 * created in  2018/8/29 11:47
 * modified By:
 */
public class User {
	private Integer orgId;
	private String userCode;
	private String cnName;
	private String enName;
	private String loginName;
	private String password;
	private String type;

	private String weiXin;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("orgId=").append(orgId);
		sb.append(", userCode='").append(userCode).append('\'');
		sb.append(", cnName='").append(cnName).append('\'');
		sb.append(", enName='").append(enName).append('\'');
		sb.append(", loginName='").append(loginName).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
