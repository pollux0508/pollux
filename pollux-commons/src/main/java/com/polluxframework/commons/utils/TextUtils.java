package com.polluxframework.commons.utils;

import com.polluxframework.commons.entity.TextLabel;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhumin0508
 * created in  2018/8/23 11:17
 * modified By:
 */
public class TextUtils {
	private TextUtils() {
	}

	/**
	 * 获取从指定字符串流中指定文本标签的位置信息
	 *
	 * @param stringBuffer 字符串流
	 * @param label        文本标签
	 */
	public static void getTextLabelPosition(final StringBuffer stringBuffer, TextLabel label) {
		if (stringBuffer == null || stringBuffer.length() == 0) {
			return;
		}
		int startLength = label.getStartSign().length();
		int sourceLength = label.getSourceSign().length();
		char[] cas = stringBuffer.toString().toCharArray();
		int index = BaseUtils.checkNextStrIndex(cas, label.getIndexPos(), label.getStartSign());
		if (index == -1) {
			label.setHasFind(false);
			return;
		}
		label.setStartSignPos(index);

		index = BaseUtils.checkNextStrIndex(cas, label.getIndexPos() + startLength, label.getSourceSign());
		if (index == -1) {
			label.setHasFind(false);
			return;
		}
		label.setSourceSignPos(index + sourceLength);
		if (StringUtils.isNotEmpty(label.getEndSign())) {
			index = BaseUtils.checkNextStrIndex(cas, label.getSourceSignPos(), label.getEndSign());
			if (index == -1) {
				label.setHasFind(false);
				return;
			}
			label.setEndSignPos(index);
		}
		label.setHasFind(true);
	}
}
