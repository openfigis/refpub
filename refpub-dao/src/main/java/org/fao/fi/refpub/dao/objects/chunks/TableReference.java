package org.fao.fi.refpub.dao.objects.chunks;

public class TableReference {
	private int id;
	private String name;
	private String primaryKey;
	private String metaColumn;
	private String groupColumn;
	private String memberColumn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getMetaColumn() {
		return metaColumn;
	}
	public void setMetaColumn(String metaColumn) {
		this.metaColumn = metaColumn;
	}
	public String getGroupColumn() {
		return groupColumn;
	}
	public void setGroupColumn(String groupColumn) {
		this.groupColumn = groupColumn;
	}
	public String getMemberColumn() {
		return memberColumn;
	}
	public void setMemberColumn(String memberColumn) {
		this.memberColumn = memberColumn;
	}
}
