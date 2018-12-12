package com.polluxframework.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 系列列表
 * @author zhumin0508
 * created in  2018/11/22 16:21
 * modified By:
 */
@ApiModel("系列列表")
public class Series<T> {
	/**
	 * 系列名称
	 */
	@ApiModelProperty("系列名称")
	private String name;
	/**
	 * 系列中的数据内容数组
	 */
	@ApiModelProperty("系列中的数据内容数组")
	private List<T> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
