package com.polluxframework.paginator.support;

import com.polluxframework.paginator.constant.DialectEnum;
import com.polluxframework.paginator.dialect.Db2Dialect;
import com.polluxframework.paginator.dialect.Dialect;
import com.polluxframework.paginator.dialect.MySqlDialect;
import com.polluxframework.paginator.dialect.OracleDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhumin0508
 * created in  2018/9/28 15:20
 * modified By:
 */
public final class DialectFactory {
	private static Logger logger = LoggerFactory.getLogger(DialectFactory.class);
	private static MySqlDialect mySqlDialect;
	private static Db2Dialect db2Dialect;
	private static OracleDialect oracleDialect;

	private DialectFactory() {

	}

	public static DialectEnum defaultDialectType(){
		return DialectEnum.MYSQL;
	}

	public static DialectEnum getDialectType(String type){
		String dbType = type.toUpperCase();
		try {
			return DialectEnum.valueOf(dbType);
		} catch (Exception e) {
			logger.debug("适配类型错误，将采用默认类型");
		}
		return defaultDialectType();
	}

	public static Dialect buildDialect(DialectEnum dialectEnum) {
		switch (dialectEnum) {
			case MYSQL:
				return getMySqlDialect();
			case ORACLE:
				return getOracleDialect();
			case DB2:
				return getDB2Dialect();
			default:
				return getMySqlDialect();
		}
	}

	private static Dialect getMySqlDialect() {
		if (mySqlDialect == null) {
			mySqlDialect = new MySqlDialect();
		}
		return mySqlDialect;
	}

	private static Dialect getOracleDialect() {
		if (oracleDialect == null) {
			oracleDialect = new OracleDialect();
		}
		return oracleDialect;
	}

	private static Dialect getDB2Dialect() {
		if (db2Dialect == null) {
			db2Dialect = new Db2Dialect();
		}
		return db2Dialect;
	}
}
