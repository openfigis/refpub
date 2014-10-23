package org.fao.fi.refpub.dao.objects.chunks;

public class MDCodelist {
	private String table_name;
	private String code_column;
	private String code_name;
	private String rest_concept;
	private int isDefault;
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getCode_column() {
		return code_column;
	}
	public void setCode_column(String code_column) {
		this.code_column = code_column;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getRest_concept() {
		return rest_concept;
	}
	public void setRest_concept(String rest_concept) {
		this.rest_concept = rest_concept;
	}
}
