package com.polluxframework.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础的entity类
 * @author zhumin0508
 * created in  2018/8/29 10:33
 * modified By:
 */
@ApiModel(value="实体基础类",description = "实体基础类")
public class BaseEntity implements Serializable {
	@ApiModelProperty(value = "ID",notes = "唯一标识",dataType="Integer")
	private Integer id;
	@ApiModelProperty(value = "创建人",notes = "创建人",dataType="Integer")
	private Integer creator;
	@ApiModelProperty(value = "创建时间",notes = "创建时间",dataType="Date")
	private Date createTime;
	@ApiModelProperty(value = "最后更新人",notes = "最后更新人",dataType="Integer")
	private Integer lastUpdator;
	@ApiModelProperty(value = "最后更新时间",notes = "最后更新时间",dataType="Date")
	private Date lastUpdateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLastUpdator() {
		return lastUpdator;
	}

	public void setLastUpdator(Integer lastUpdator) {
		this.lastUpdator = lastUpdator;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
