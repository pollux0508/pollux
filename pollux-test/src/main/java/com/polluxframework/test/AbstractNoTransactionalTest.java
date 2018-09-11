package com.polluxframework.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhumin0508
 * created in  2018/5/8 15:06
 * modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring-*.xml"})
public class AbstractNoTransactionalTest extends AbstractJUnit4SpringContextTests {
}
