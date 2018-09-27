package com.polluxframework.commons.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/15 11:36
 * modified By:
 */
public class Api {
	private static final Response STATUS = new Response("status", "int", "响应状态", true);
	private static final Response CODE = new Response("code", "String", "响应代码", true);
	private static final Response MESSAGE = new Response("msg", "String", "响应消息", true);
	private static final Response DATA = new Response("data", "object", "返回的数据", true);
	private static final Response PAGE_NO = new Response("data>>pageNo", "int", "当前页面数", true);
	private static final Response PAGE_SIZE = new Response("data>>pageSize", "int", "每页条数", true);
	private static final Response TOTAL = new Response("data>>total", "int", "总条数", true);
	private static final Response TOTAL_PAGE = new Response("data>>totalPage", "int", "总页码数", true);
	private static final Response PAGE_ROWS = new Response("data>>rows", "array", "返回的分页数据", true);
	
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
	private String protocol = "http/https";
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
	private List<Parameter> parameters = new ArrayList<>();
	/**
	 * 返回结果对象
	 */
	private List<Response> responses = new ArrayList<>();
	/**
	 * 是否分页
	 */
	private boolean pagination;

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
		String prev = "data>>";
		List<Response> result = new ArrayList<>(16);
		result.add(STATUS);
		result.add(CODE);
		result.add(MESSAGE);
		result.add(DATA);
		if (pagination) {
			prev = "data>>rows>>";
			result.add(PAGE_NO);
			result.add(PAGE_SIZE);
			result.add(TOTAL);
			result.add(TOTAL_PAGE);
			result.add(PAGE_ROWS);
		}
		for (Response response : responses) {
			response.setName(response.getFullName(prev));
			result.add(response);
		}
		return result;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}

	public void addResponse(Response response) {
		responses.add(response);
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
		sb.append(", pagination=").append(pagination);
		sb.append('}');
		return sb.toString();
	}
}
