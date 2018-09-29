package com.polluxframework.database.scanner;


import com.polluxframework.database.entity.IModuleVersion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/8/13 10:06
 * modified By:
 */
public final class DataBaseScanner {
	private static Map<String,IModuleVersion> tables= new HashMap<>(64);
	private DataBaseScanner(){
	}

	/**
	 * 将建表脚本的bean添加到tables总表中
	 * @param table 建表脚本的bean
	 */
	public static void addDataBaseScannerTable(IModuleVersion table){
		tables.put(table.getModule(),table);
	}

	/**
	 * 获取所有的建表脚本bean
	 * @return 建表脚本bean
	 */
	public static Map<String,IModuleVersion> getDataBaseScannerTables(){
		return tables;
	}

}
