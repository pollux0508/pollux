package com.polluxframework.web.controller;

import com.polluxframework.commons.entity.Pagination;
import com.polluxframework.commons.utils.SessionUtils;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhumin0508
 * created in  2018/5/11 10:34
 * modified By:
 */
@Controller
@RequestMapping(value = "/pagination")
public class PaginationController extends BaseController {

    @RequestMapping(value = "/save", name = "分页数据保存", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void save(HttpServletRequest request, Pagination pagination) {
        SessionUtils.setPagination(request, pagination);
    }

    @RequestMapping("/clear")
    @ResponseBody
    public void clear(HttpServletRequest request) {
        SessionUtils.clearPagination(request);
    }

    @RequestMapping("/getPagination")
    @ResponseBody
    public Pagination getPagination(HttpServletRequest request, Pagination pagination) {
        return SessionUtils.getPagination(request, pagination);
    }
}
