package com.polluxframework.exception;

/**
 * @author zhumin0508
 * created in  2018/5/10 9:48
 * modified By:
 */
public interface IException {
	/**
	 * 获取异常代码
	 *
	 * @return 在框架中对异常代码进行统一编码规范 1-2为统一PX 3-4为开发还是运行时异常 开发：KF  运行：RN  5-6 模块Key  7-9 预留序列号
	 */
	String getCode();

	/**
	 * 获取异常信息
	 *
	 * @return 异常代码的对应信息
	 */
	String getMsg();
}
