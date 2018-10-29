package com.polluxframework.commons.utils;

import com.polluxframework.test.AbstractNoTransactionalTest;
import com.polluxframework.test.utils.WebTestSingleUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;


/**
 * @author zhumin0508
 * created in  2018/5/8 20:39
 * modified By:
 */
public class SessionUtilsTest extends AbstractNoTransactionalTest {
	private HttpServletRequest request;

	@Before
	public void before() {
		request = WebTestSingleUtils.getSingleRequest();
	}

	@Test
	public void getCurUserId() {
		SessionUtils.setCurUserID(request, "zhumin");
		String userId = SessionUtils.getCurUserId(request);
		Assert.assertEquals("zhumin", userId);
	}

}