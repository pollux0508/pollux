package com.polluxframework.service.impl;

import com.polluxframework.entity.User;
import com.polluxframework.mapper.UserMapper;
import com.polluxframework.paginator.entity.PageBounds;
import com.polluxframework.paginator.entity.PageModel;
import com.polluxframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhumin0508
 * created in  2018/8/29 11:55
 * modified By:
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageModel<User> getUserList(User user) {
		PageBounds pageBounds = new PageBounds(2, 20);
		PageModel<User> list = userMapper.getUserList(pageBounds);
		return list;
	}
}
