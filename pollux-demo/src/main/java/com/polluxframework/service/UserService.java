package com.polluxframework.service;

import com.polluxframework.entity.User;
import com.polluxframework.paginator.entity.PageModel;

/**
 * @author zhumin0508
 * created in  2018/8/29 11:54
 * modified By:
 */
public interface UserService {

	/**
	 * 分页获取用户信息
	 *
	 * @param user 用户查询字段
	 * @return 分页的用户列表信息
	 */
	PageModel<User> getUserList(User user);
}
