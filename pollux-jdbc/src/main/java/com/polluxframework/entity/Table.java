package com.polluxframework.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/8 9:59
 * modified By:
 */
public class Table {
	private String schema;
	private String table;
	private String comment;
	private List<Column> columns;
	private List<Index> primaryKeys;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Index> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<Index> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public String getCreateTable(int dbType) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ");
		sb.append(table);
		sb.append('(');
		int length = columns.size();
		for (int i = 0; i < length; i++) {
			Column column = columns.get(i);
			sb.append(column.getStandard(dbType));
			if (i < (length - 1)) {
				sb.append(',');
			}
		}
		sb.append(')');
		return sb.toString();
	}

	public String getPrimaryKey(int dbType) {
		int length = primaryKeys.size();
		StringBuilder sb = new StringBuilder();
		if (length > 0) {
			sb.append(" ALTER TABLE ");
			sb.append(table);
			sb.append(" ADD ");
			if (dbType != 0) {
				sb.append(" constraint ");
			}
			sb.append(primaryKeys.get(0).getName());
			sb.append(" PRIMARY KEY (");
			for (int i = 0; i < length; i++) {
				Index index = primaryKeys.get(i);
				sb.append(index.getColumn());
				if (i < (length - 1)) {
					sb.append(',');
				}
			}
			sb.append(')');
		}
		return sb.toString();
	}

	public String getDropTable() {
		return "DROP TABLE " + table;
	}
}
