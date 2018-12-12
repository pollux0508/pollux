package com.polluxframework.swagger.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/11/26 15:45
 * modified By:
 */
public class Operation {
	private Long id;

	private String name;

	private String remark;

	private List<String> mappings;

	private List<String> requestMethods;

	private SuperMapping superMapping;

	private List<ApiParameter> parameters;

	private List<Response> responses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getMappings() {
		return mappings;
	}

	public void setMappings(List<String> mappings) {
		this.mappings = mappings;
	}

	public List<String> getRequestMethods() {
		return requestMethods;
	}

	public void setRequestMethods(List<String> requestMethods) {
		this.requestMethods = requestMethods;
	}

	public SuperMapping getSuperMapping() {
		return superMapping;
	}

	public void setSuperMapping(SuperMapping superMapping) {
		this.superMapping = superMapping;
	}

	public List<ApiParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ApiParameter> parameters) {
		this.parameters = parameters;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
}
