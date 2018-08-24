package com.polluxframework.entity;

import com.polluxframework.commons.utils.PxStringUtils;
import com.polluxframework.util.PxDbColumnUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhumin0508
 * created in  2018/8/8 9:59
 * modified By:
 */
public class PxColumn {
	private static final String NO = "NO";
	private static final char BLANK = ' ';
	private static final String ZERO = "0";
	/**
	 * 表名
	 */
	private String table;
	/**
	 * 列名
	 */
	private String column;
	/**
	 * 列类型
	 */
	private String type;
	/**
	 * 列大小
	 */
	private String size;
	/**
	 * 列精确度
	 */
	private String scale;
	/**
	 * 列描述
	 */
	private String comment;
	/**
	 * 默认值
	 */
	private String defaultValue;
	/**
	 * 是否允许空值
	 */
	private String nullAble;
	/**
	 * 是否自动增长
	 */
	private String autoIncrement;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.toUpperCase();
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = PxStringUtils.replaceBlank(comment);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getNullAble() {
		return nullAble;
	}

	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}

	public String getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getTypeAndSize() {
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append(type);
		if (!PxDbColumnUtils.isNoNeedSize(type) && StringUtils.isNotEmpty(size)) {
			stringBuffer.append('(').append(size);
			if (StringUtils.isNotEmpty(scale) && (!ZERO.equals(scale))) {
				stringBuffer.append(',').append(scale);
			}
			stringBuffer.append(')').append(BLANK);
		}
		return stringBuffer.toString();
	}

	public String getDefault() {
		StringBuilder stringBuffer = new StringBuilder();
		if (StringUtils.isNotEmpty(defaultValue)) {
			stringBuffer.append("DEFAULT").append(BLANK);
			if (PxDbColumnUtils.isChar(type)) {
				stringBuffer.append('\'');
			} else if (PxDbColumnUtils.isVarchar(type)) {
				stringBuffer.append('\"');
			}
			stringBuffer.append(defaultValue);

			if (PxDbColumnUtils.isChar(type)) {
				stringBuffer.append('\'');
			} else if (PxDbColumnUtils.isVarchar(type)) {
				stringBuffer.append('\"');
			}
			stringBuffer.append(BLANK);
		}
		return stringBuffer.toString();
	}

	/**
	 * @param dbType 0 Mysql 1 Oracle 2 DB2
	 * @return 返回列标准语法
	 */
	public String getStandard(int dbType) {
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append(column).append(BLANK);
		stringBuffer.append(getTypeAndSize());
		if (StringUtils.isNotEmpty(defaultValue)) {
			stringBuffer.append(getDefault());
		}
		if (StringUtils.isNotEmpty(nullAble) && NO.equals(nullAble)) {
			stringBuffer.append(BLANK);
			stringBuffer.append("NOT NULL");
			stringBuffer.append(BLANK);
		}
		if (dbType == 0 && StringUtils.isNotEmpty(comment)) {
			stringBuffer.append(BLANK);
			stringBuffer.append("COMMENT");
			stringBuffer.append(BLANK);
			stringBuffer.append('\'');
			stringBuffer.append(comment);
			stringBuffer.append('\'');
			stringBuffer.append(BLANK);
		}
		return stringBuffer.toString();
	}

	/**
	 * @return 返回注释语句
	 */
	public String getAlertComment() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(comment)) {
			sb.append("COMMENT ON COLUMN ");
			sb.append(table);
			sb.append('.');
			sb.append(column);
			sb.append(" IS ");
			sb.append('\'');
			sb.append(comment);
			sb.append('\'');
		}
		return sb.toString();
	}
}
