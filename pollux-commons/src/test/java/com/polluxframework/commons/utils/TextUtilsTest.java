package com.polluxframework.commons.utils;

import com.polluxframework.commons.entity.TextLabel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhumin0508
 * created in  2018/9/7 10:24
 * modified By:
 */
public class TextUtilsTest {

	@Test
	public void getDocLabelPosition() {
		String str = "曹操是枭雄，曹操是奸雄！曹操是可爱的奸雄。";
		StringBuffer stringBuffer = new StringBuffer(str);
		TextLabel label = new TextLabel();
		label.setIndexPos(0);
		label.setStartSign("@");
		label.setSourceSign("！");
		label.setEndSign("。");
		label.setHasFind(false);
		TextUtils.getTextLabelPosition(stringBuffer, label);
		Assert.assertFalse("此处应该未找到", label.isHasFind());

		label.setStartSign("，");
		label.setSourceSign("@");
		label.setEndSign("。");
		label.setHasFind(false);
		TextUtils.getTextLabelPosition(stringBuffer, label);
		Assert.assertFalse("此处应该未找到", label.isHasFind());


		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign("@");
		label.setHasFind(false);
		TextUtils.getTextLabelPosition(stringBuffer, label);
		Assert.assertFalse("此处应该未找到", label.isHasFind());

		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign("。");
		label.setHasFind(false);
		TextUtils.getTextLabelPosition(stringBuffer, label);
		Assert.assertTrue("此处应该找到", label.isHasFind());

		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign(null);
		label.setHasFind(false);
		TextUtils.getTextLabelPosition(stringBuffer, label);
		Assert.assertTrue("不需要找结尾节点，此处应该找到", label.isHasFind());


		label.setStartSign("，");
		label.setSourceSign("！");
		label.setEndSign("");
		label.setHasFind(false);
		TextUtils.getTextLabelPosition(stringBuffer, label);
		Assert.assertTrue("不需要找结尾节点，此处应该找到", label.isHasFind());

		label.setHasFind(false);
		TextUtils.getTextLabelPosition(null, label);
		Assert.assertFalse("此处应该未找到", label.isHasFind());

		label.setHasFind(false);
		TextUtils.getTextLabelPosition(new StringBuffer(), label);
		Assert.assertFalse("此处应该未找到", label.isHasFind());
	}
}