package org.fao.fi.refpub.dao.objects;

public class ClassInitXML {
	private String ref_table;

	public String getRef_table() {
		return ref_table;
	}

	public void setRef_table(String ref_table) {
		this.ref_table = ref_table.replace("Table=", "");
	}
}
