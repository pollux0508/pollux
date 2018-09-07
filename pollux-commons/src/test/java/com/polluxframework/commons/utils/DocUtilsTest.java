package com.polluxframework.commons.utils;

import com.polluxframework.commons.entity.DocLabel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhumin0508
 * created in  2018/9/7 10:24
 * modified By:
 */
public class DocUtilsTest {

	@Test
	public void getDocLabelPosition() {
		String str="曹操是枭雄，曹操是奸雄！曹操是可爱的奸雄。";
		StringBuffer stringBuffer = new StringBuffer(str);
		DocLabel label = new DocLabel();
		label.setIndexPos(0);
		label.setStartSign("@");
		label.setSourceSign("！");
		label.setEndSign("。");
		label.setHasFind(false);
		DocUtils.getDocLabelPosition(stringBuffer,label);
		Assert.assertFalse("此处应该未找到",label.isHasFind());

		label.setStartSign("，");
		label.setSourceSign("@");
		label.setEndSign("。");
		label.setHasFind(false);
		DocUtils.getDocLabelPosition(stringBuffer,label);
		Assert.assertFalse("此处应该未找到",label.isHasFind());


		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign("@");
		label.setHasFind(false);
		DocUtils.getDocLabelPosition(stringBuffer,label);
		Assert.assertFalse("此处应该未找到",label.isHasFind());

		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign("。");
		label.setHasFind(false);
		DocUtils.getDocLabelPosition(stringBuffer,label);
		Assert.assertTrue("此处应该找到",label.isHasFind());

		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign(null);
		label.setHasFind(false);
		DocUtils.getDocLabelPosition(stringBuffer,label);
		Assert.assertTrue("不需要找结尾节点，此处应该找到",label.isHasFind());


		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign("");
		label.setHasFind(false);
		DocUtils.getDocLabelPosition(stringBuffer,label);
		Assert.assertTrue("不需要找结尾节点，此处应该找到",label.isHasFind());

		label.setHasFind(false);
		DocUtils.getDocLabelPosition(null,label);
		Assert.assertFalse("此处应该未找到",label.isHasFind());

		label.setHasFind(false);
		DocUtils.getDocLabelPosition(new StringBuffer(),label);
		Assert.assertFalse("此处应该未找到",label.isHasFind());
	}
}