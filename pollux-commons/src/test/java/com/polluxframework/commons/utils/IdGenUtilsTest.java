package com.polluxframework.commons.utils;

import com.polluxframework.test.AbstractNoTransactionalTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhumin0508
 * created in  2018/5/8 15:46
 * modified By:
 */
public class IdGenUtilsTest extends AbstractNoTransactionalTest {

	@Test
	public void getNextId() {
		String pre= IdGenUtils.getNextId();
		String next= IdGenUtils.getNextId();
		Assert.assertNotEquals("两次获取的ID不一致",pre,next);
	}
}