package com.polluxframework.commons.utils;

import com.pollux.frame.test.AbstractNoTransactionalTest;
import com.pollux.frame.test.utils.WebTestSingleUtils;
import com.polluxframework.commons.entity.Pagination;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


	@Test
	public void setPagination() {
		Pagination pagination = new Pagination();
		pagination.setPageKey("zhumin");
		pagination.setPageNo(20);
		SessionUtils.setPagination(request, pagination);
		Pagination result = SessionUtils.getPagination(request, new Pagination("zhumin"));
		Assert.assertEquals(pagination, result);

		result = SessionUtils.getPagination(request, new Pagination("caocao"));
		Assert.assertNotEquals(pagination, result);
		Assert.assertEquals(Integer.valueOf(10), result.getPageSize());
		Assert.assertEquals(Integer.valueOf(1), result.getPageNo());

		HttpSession session = request.getSession();
		session.setAttribute("currentPagination",1);
		result = SessionUtils.getPagination(request);
		Assert.assertNull(result);

		result = SessionUtils.getPagination(request,new Pagination("caocao"));
		Assert.assertNotEquals(pagination, result);
		Assert.assertEquals(Integer.valueOf(10), result.getPageSize());
		Assert.assertEquals(Integer.valueOf(1), result.getPageNo());
	}

	@Test
	public void clearPagination() {
		SessionUtils.clearPagination(request);
	}
}