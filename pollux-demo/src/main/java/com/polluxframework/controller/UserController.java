package com.polluxframework.controller;

import com.polluxframework.entity.User;
import com.polluxframework.service.UserService;
import com.polluxframework.web.controller.BaseController;
import com.polluxframework.web.entity.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhumin0508
 * created in  2018/8/29 15:08
 * modified By:
 */
@Controller
@RequestMapping("demo/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getUserList")
	@ResponseBody
	public WebResponse getUserList(User user) {
		return new WebResponse("查询成功", userService.getUserList(user));
	}

}
