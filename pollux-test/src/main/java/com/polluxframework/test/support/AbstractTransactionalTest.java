package com.polluxframework.test.support;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author zhumin0508
 * created in  2018/5/8 15:05
 * modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/spring-*.xml"})
@WebAppConfiguration
public abstract class AbstractTransactionalTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	WebApplicationContext webApplicationContext;

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

}
