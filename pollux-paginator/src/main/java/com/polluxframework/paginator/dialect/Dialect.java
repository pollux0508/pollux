package com.polluxframework.paginator.dialect;

import com.polluxframework.paginator.entity.PageBounds;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.*;

/**
 * @author zhumin0508
 * created in  2018/8/30 10:18
 * modified By:
 */
public class Dialect {
	private TypeHandlerRegistry typeHandlerRegistry;
	private MappedStatement mappedStatement;
	private PageBounds pageBounds;
	private Object parameterObject;
	private BoundSql boundSql;
	private List<ParameterMapping> parameterMappings;
	private Map<String, Object> pageParameters = new HashMap<>();

	private String pageSQL;
	private String countSQL;


	public Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
		this.mappedStatement = mappedStatement;
		this.parameterObject = parameterObject;
		this.pageBounds = pageBounds;
		this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
		init();
	}

	public final void init() {
		boundSql = mappedStatement.getBoundSql(parameterObject);
		parameterMappings = new ArrayList<>(boundSql.getParameterMappings());
		if (parameterObject instanceof Map) {
			pageParameters.putAll((Map) parameterObject);
		} else if (parameterObject != null) {
			Class cls = parameterObject.getClass();
			if (cls.isPrimitive() || cls.isArray() ||
					SimpleTypeRegistry.isSimpleType(cls) ||
					Enum.class.isAssignableFrom(cls) ||
					Collection.class.isAssignableFrom(cls)) {
				for (ParameterMapping parameterMapping : parameterMappings) {
					pageParameters.put(parameterMapping.getProperty(), parameterObject);
				}
			} else {
				MetaObject metaObject = mappedStatement.getConfiguration().newMetaObject(parameterObject);
				ObjectWrapper wrapper = metaObject.getObjectWrapper();
				for (ParameterMapping parameterMapping : parameterMappings) {
					PropertyTokenizer prop = new PropertyTokenizer(parameterMapping.getProperty());
					pageParameters.put(parameterMapping.getProperty(), wrapper.get(prop));
				}
			}

		}

		StringBuilder bufferSql = new StringBuilder(boundSql.getSql().trim());
		if (bufferSql.lastIndexOf(";") == bufferSql.length() - 1) {
			bufferSql.deleteCharAt(bufferSql.length() - 1);
		}
		String sql = bufferSql.toString();
		pageSQL = sql;

		countSQL = getCountString(sql);
	}


	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public Object getParameterObject() {
		return pageParameters;
	}


	public String getPageSQL() {
		return pageSQL;
	}

	protected void setPageParameter(String name, Object value, Class type) {
		ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), name, type).build();
		parameterMappings.add(parameterMapping);
		pageParameters.put(name, value);
	}


	public String getCountSQL() {
		return countSQL;
	}

	/**
	 * 将sql变成分页sql语句
	 */
	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql,"__offset",offset,"__limit",limit);
	}
	/**
	 * 将sql变成分页sql语句
	 */
	protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

	/**
	 * 将sql转换为总记录数SQL
	 *
	 * @param sql SQL语句
	 * @return 总记录数的sql
	 */
	protected String getCountString(String sql) {
		return "select count(1) from (" + sql + ") tmp_count";
	}
}
