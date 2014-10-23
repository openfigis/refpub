package org.fao.fi.refpub.dao.objects.db;

public class TableInfo {
	private String table_name;
	private String primary_key;
	private String position;
	private String status;
	private String owner;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getPrimary_key() {
		return primary_key;
	}
	public void setPrimary_key(String primary_key) {
		this.primary_key = primary_key;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
