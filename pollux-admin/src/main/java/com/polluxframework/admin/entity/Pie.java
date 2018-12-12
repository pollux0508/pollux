package com.polluxframework.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Echart饼图
 * @author zhumin0508
 * created in  2018/11/22 16:22
 * modified By:
 */
@ApiModel("Echart饼图")
public class Pie {
	/**
	 * 每个数据项的名称
	 */
	@ApiModelProperty("每个数据项的名称")
	private String name;
	/**
	 * 每个数据项的值
	 */
	@ApiModelProperty("每个数据项的值")
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
