package com.polluxframework.version.scanner;


import com.polluxframework.version.entity.IModuleVersion;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/8/13 10:06
 * modified By:
 */
public final class DataBaseScanner {
	private static Set<IModuleVersion> tables= new LinkedHashSet<>(64);
	private DataBaseScanner(){
	}

	/**
	 * 将建表脚本的bean添加到tables总表中
	 * @param table 建表脚本的bean
	 */
	public static void addDataBaseScannerTable(IModuleVersion table){
		tables.add(table);
	}

	/**
	 * 获取所有的建表脚本bean
	 * @return 建表脚本bean
	 */
	public static Set<IModuleVersion> getDataBaseScannerTables(){
		return tables;
	}

}