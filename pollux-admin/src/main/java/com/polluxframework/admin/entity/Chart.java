package com.polluxframework.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Echarts图表类通用数据对象
 * @author zhumin0508
 * created in  2018/11/22 16:19
 * modified By:
 */
@ApiModel("Echarts图表类通用数据对象")
public class Chart<T> {
	/**
	 * Echarts图表的图例
	 */
	@ApiModelProperty("Echarts图表的图例")
	private List<String> legend;
	/**
	 * Echarts图表的类目轴(X轴/Y轴的)的取值范围
	 */
	private List<String> axisData;
	/**
	 * Echarts图表的类目轴为坐标对应的值
	 */
	private List<Series<T>> series;

	public List<String> getLegend() {
		return legend;
	}

	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

	public List<String> getAxisData() {
		return axisData;
	}

	public void setAxisData(List<String> axisData) {
		this.axisData = axisData;
	}

	public List<Series<T>> getSeries() {
		return series;
	}

	public void setSeries(List<Series<T>> series) {
		this.series = series;
	}
}
