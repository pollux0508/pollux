package com.polluxframework.entity;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/8 9:59
 * modified By:
 */
public class PxTable {
	private String schema;
	private String table;
	private String comment;
	private List<PxColumn> columns;
	private List<PxIndex> primaryKeys;

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

	public List<PxColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<PxColumn> columns) {
		this.columns = columns;
	}

	public List<PxIndex> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<PxIndex> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public String getCreateTable(int dbType) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ");
		sb.append(table);
		sb.append('(');
		int length = columns.size();
		for (int i = 0; i < length; i++) {
			PxColumn column = columns.get(i);
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
				PxIndex index = primaryKeys.get(i);
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
