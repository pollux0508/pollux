package com.polluxframework.web.controller;

import com.polluxframework.commons.entity.Pagination;
import com.polluxframework.commons.utils.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhumin0508
 * created in  2018/5/11 10:34
 * modified By:
 */
@Controller
@RequestMapping("/pagination")
public class PaginationController extends BaseController {

	@RequestMapping("/save")
	@ResponseBody
	public void save(HttpServletRequest request,Pagination pagination){
		SessionUtils.setPagination(request,pagination);
	}

	@RequestMapping("/clear")
	@ResponseBody
	public void clear(HttpServletRequest request){
		SessionUtils.clearPagination(request);
	}

	@RequestMapping("/getPagination")
	@ResponseBody
	public Pagination getPagination(HttpServletRequest request,Pagination pagination){
		return SessionUtils.getPagination(request,pagination);
	}
}
