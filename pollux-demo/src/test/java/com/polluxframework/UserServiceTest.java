package com.polluxframework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.entity.User;
import com.polluxframework.paginator.entity.PageModel;
import com.polluxframework.service.UserService;
import com.polluxframework.test.AbstractNoTransactionalTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author zhumin0508
 * created in  2018/8/29 11:56
 * modified By:
 */
public class UserServiceTest extends AbstractNoTransactionalTest {
	@Autowired
	private UserService userService;

	@Test
	public void getUserList() throws IOException {
		ObjectMapper objectMapper = new HZPageModelMapper();
		PageModel<User> list = userService.getUserList(new User());
		System.out.println(objectMapper.writeValueAsString(list));
	}
}