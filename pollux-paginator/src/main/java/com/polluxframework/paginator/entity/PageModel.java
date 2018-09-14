package com.polluxframework.paginator.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.polluxframework.paginator.support.PageModelSerializer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 统一的页面对象
 *
 * @author zhumin0508
 * created in  2017/12/18 13:25
 * modified By:
 */
@JsonSerialize(using = PageModelSerializer.class)
public class PageModel<E> extends ArrayList<E> {

    /**
     * 当前页码数
     */
    private int pageNo = 1;
    /**
     * 每页显示的条数
     */
    private int pageSize = 10;
    /**
     * 总记录数
     */
    private int total = 0;
    /**
     * 总页数
     */
    private int totalPage = 0;

    public PageModel() {
    }

    public PageModel(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 获取当前页码数
     *
     * @return 返回当前页码
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码数
     *
     * @param pageNo 当前页码
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo <= 0 ? 1 : pageNo;
    }

    /**
     * 获取当前页显示的条数
     *
     * @return 当前页显示的条数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置当前页显示的条数
     *
     * @param pageSize 当前页显示的条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 1 : pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 优先保障设置了总条数的情况
     *
     * @return 总页码数
     */
    public int getTotalPage() {
        if (total > 0 && pageSize > 0) {
            return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<E> getRows(){
        return new ArrayList<>(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("pageNo:").append(pageNo).append(',');
        sb.append("pageSize:").append(pageSize).append(',');
        sb.append("total:").append(total).append(',');
        sb.append("totalPage:").append(totalPage).append(',');
        sb.append("rows:").append('[');
        Iterator<E> it = iterator();
        if (!it.hasNext()) {
            sb.append(']').append('}');
            return sb.toString();
        }

        for (; ; ) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext()) {
                return sb.append(']').append('}').toString();
            }
            sb.append(',').append(' ');
        }
    }
}
