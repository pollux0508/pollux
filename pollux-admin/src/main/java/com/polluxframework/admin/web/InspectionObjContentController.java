package com.polluxframework.admin.web;

import com.polluxframework.admin.entity.Chart;
import com.polluxframework.web.controller.BaseController;
import com.polluxframework.web.entity.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author h w
 * created in  2018/9/04 16:09
 * modified By:
 */
@Api("巡检内容")
@Controller
@RequestMapping(value = {"/inspection/obj/content", "/inspection/obj/content1"})
public class InspectionObjContentController extends BaseController {

	/**
	 * 通过唯一标识获取巡检对象内容信息
	 *
	 * @param id 巡检对象内容的实例（唯一标识有ID CODE）
	 * @return 巡检对象内容实例
	 */
	@RequestMapping("/getInspectionObjContent")
	@ResponseBody
	@ApiOperation("通过唯一标识获取巡检对象内容信息")
	@ApiImplicitParam(value = "id", name = "巡检内容ID")
	public WebResponse getInspectionObjContent(@RequestBody Integer id) {
		return WebResponse.success();
	}

	/**
	 * 通过唯一标识获取巡检对象内容信息
	 *
	 * @param id 巡检对象内容的实例（唯一标识有ID CODE）
	 * @return 巡检对象内容实例
	 */
	@RequestMapping("/getInspectionObj")
	@ResponseBody
	@ApiOperation("通过机构获取巡检对象内容信息")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "id", name = "巡检机构ID", dataType = "java.lang.Integer"),
			@ApiImplicitParam(value = "chart", name = "图表", dataType = "com.polluxframework.admin.entity.Chart")}
	)
	public WebResponse getInspectionObj(@RequestBody Integer id, Chart chart) {
		return WebResponse.success();
	}
}
