package com.polluxframework.util;

/**
 * @author zhumin0508
 * created in  2018/8/8 14:08
 * modified By:
 */
public class DbColumnUtils {
	public static final String CHAR_STR="CHAR";
	public static final String VARCHAR_STR="VARCHAR,VARCHAR2";
	public static final String NO_NEED_SIZE="DATE,DATETIME,TIMESTAMP";

	private DbColumnUtils(){

	}

	public static boolean isChar(String str){
		return CHAR_STR.equalsIgnoreCase(str);
	}

	public static boolean isVarchar(String str){
		return VARCHAR_STR.contains(str.toUpperCase());
	}

	public static boolean isNoNeedSize(String str){
		return NO_NEED_SIZE.contains(str.toUpperCase());
	}
}
