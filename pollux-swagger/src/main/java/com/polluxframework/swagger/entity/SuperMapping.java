package com.polluxframework.swagger.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/11/26 16:06
 * modified By:
 */
public class SuperMapping {
	private Integer id;
	private String controlCls;
	private String name;
	private List<String> mappings;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getControlCls() {
		return controlCls;
	}

	public void setControlCls(String controlCls) {
		this.controlCls = controlCls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMappings() {
		return mappings;
	}

	public void setMappings(List<String> mappings) {
		this.mappings = mappings;
	}
}
