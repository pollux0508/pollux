package com.polluxframework.commons.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/5/8 13:52
 * modified By:
 */
public class Pagination implements IPagination,Serializable {

	private String pageKey = "";
	private Integer pageNo = 1;
	private Integer pageSize = 10;

	public Pagination() {
	}

	public Pagination(String pageKey) {
		this.pageKey = pageKey;
	}

	private Map<String, Object> queryParams = new HashMap<>();

	@Override
	public String getPageKey() {
		return pageKey;
	}
	@Override
	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}
	@Override
	public Integer getPageNo() {
		return pageNo;
	}
	@Override
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	@Override
	public Integer getPageSize() {
		return pageSize;
	}
	@Override
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}

}
