package com.polluxframework.commons.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/15 11:36
 * modified By:
 */
public class Api {
	/**
	 * API的请求路径
	 */
	private String url;
	/**
	 * API名称
	 */
	private String name;
	/**
	 * API描述
	 */
	private String description;
	/**
	 * API允许的请求方式 http/https
	 */
	private String protocol;
	/**
	 * API允许的请求模式 get/post ..
	 */
	private String method;
	/**
	 * API所属模块
	 */
	private String module;
	/**
	 * API所属区域
	 */
	private String domain;
	/**
	 * 参数
	 */
	private List<Parameter> parameters;
	/**
	 * 返回结果对象
	 */
	private List<Response> responses;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Api{");
		sb.append("url='").append(url).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", protocol='").append(protocol).append('\'');
		sb.append(", method='").append(method).append('\'');
		sb.append(", module='").append(module).append('\'');
		sb.append(", domain='").append(domain).append('\'');
		sb.append(", parameters=").append(parameters);
		sb.append(", responses=").append(responses);
		sb.append('}');
		return sb.toString();
	}
}
