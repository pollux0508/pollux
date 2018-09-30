package com.polluxframework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.test.AbstractNoTransactionalTest;
import com.polluxframework.test.constant.RequestTypeEnum;
import com.polluxframework.test.entity.TestParam;
import com.polluxframework.test.utils.WebTestSingleUtils;
import org.junit.Test;

/**
 * @author zhumin0508
 * created in  2018/8/29 15:12
 * modified By:
 */

public class UserControllerTest extends AbstractNoTransactionalTest {

	@Test
	public void getUserList() throws Exception {
		TestParam loginName = new TestParam("loginName");
		loginName.addParam("admin");
		ObjectMapper objectMapper = new HZPageModelMapper();
		System.out.println(objectMapper.writeValueAsString(WebTestSingleUtils.doAction(this.getWebApplicationContext(),objectMapper, "/demo/user/getUserList", RequestTypeEnum.POST, loginName)));
	}
}