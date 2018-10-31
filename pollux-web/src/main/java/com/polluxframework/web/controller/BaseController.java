package com.polluxframework.web.controller;

import com.polluxframework.common.constant.DateEnum;
import com.polluxframework.exception.BaseRuntimeException;
import com.polluxframework.web.constant.WebConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * @author zhumin0508
 * created in  2018/5/10 15:00
 * modified By:
 */
public class BaseController {
	private static final String STR_NULL = "null";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				if (StringUtils.isEmpty(text)) {
					setValue(text);
				} else {
					setValue(StringEscapeUtils.escapeHtml4(text.trim()));
				}
			}
		});
		// 自动绑定日期类型，如果传递过来是个空或者空字符串，则将日期类型设置为null
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				text = text.trim();
				if (StringUtils.isNotEmpty(text) && (!STR_NULL.equals(text))) {
					try {
						setValue(DateUtils.parseDate(text, DateEnum.getAllDateFormat()));
					} catch (ParseException e) {
						throw new BaseRuntimeException(WebConstant.DEFAULT_ERROR_CODE, "参数传递有误，这里需要一个日期类型的字符串！");
					}
				} else {
					setValue(null);
				}
			}
		});
	}
}
