package com.polluxframework.paginator.entity;

import org.apache.ibatis.session.RowBounds;

/**
 * @author zhumin0508
 * created in  2018/8/30 10:47
 * modified By:
 */
public class PageBounds extends RowBounds {
	/**
	 * 结果集是否包含TotalCount
	 */
	private boolean containsTotalCount = true;
	/**
	 * 是否强制不查询总数
	 */
	private boolean forceNoCount = false;

	public PageBounds(RowBounds rowBounds) {
		super(rowBounds.getOffset(), rowBounds.getLimit());
		if (rowBounds instanceof PageBounds) {
			PageBounds pageBounds = (PageBounds) rowBounds;
			this.containsTotalCount = pageBounds.containsTotalCount;
			this.forceNoCount = pageBounds.forceNoCount;
		}

	}

	public PageBounds(int limit) {
		this(NO_ROW_OFFSET, limit);
	}

	public PageBounds(int pageNo, int limit) {
		this(pageNo, limit, true);
	}

	public PageBounds(int pageNo, int limit, boolean containsTotalCount) {
		super(pageNo > 1 ? 0 : pageNo * limit, limit);
		this.containsTotalCount = containsTotalCount;
	}


	public boolean isContainsTotalCount() {
		return containsTotalCount;
	}

	public Boolean getForceNoCount() {
		return forceNoCount;
	}

	public void setForceNoCount(Boolean forceNoCount) {
		this.forceNoCount = forceNoCount;
		this.containsTotalCount = this.containsTotalCount && (!forceNoCount);
	}

	public int getPageNo() {
		return getOffset() / getLimit() + 1;
	}

	public boolean isNoRow() {
		return getOffset() == RowBounds.NO_ROW_OFFSET && getLimit() == RowBounds.NO_ROW_LIMIT;
	}
}
