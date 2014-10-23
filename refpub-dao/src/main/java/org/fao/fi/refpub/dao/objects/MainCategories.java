package org.fao.fi.refpub.dao.objects;

public class MainCategories {
	
	private int id;
	private int parent;
	private String name_e;
	private String name_f;
	private String name_s;
	private String name_a;
	private String name_r;
	private String name_c;
	private int codelist_id;
	private String codelist_name;
	private String ref_table;
	private String ref_table_grp;
	private String meta_column;
	private String concept_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getName_e() {
		return name_e;
	}
	public void setName_e(String name_e) {
		this.name_e = name_e;
	}
	public String getName_f() {
		return name_f;
	}
	public void setName_f(String name_f) {
		this.name_f = name_f;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public String getName_a() {
		return name_a;
	}
	public void setName_a(String name_a) {
		this.name_a = name_a;
	}
	public String getName_r() {
		return name_r;
	}
	public void setName_r(String name_r) {
		this.name_r = name_r;
	}
	public String getName_c() {
		return name_c;
	}
	public void setName_c(String name_c) {
		this.name_c = name_c;
	}
	public int getCodelist_id() {
		return codelist_id;
	}
	public void setCodelist_id(int codelist_id) {
		this.codelist_id = codelist_id;
	}
	public String getCodelist_name() {
		return codelist_name;
	}
	public void setCodelist_name(String codelist_name) {
		this.codelist_name = codelist_name;
	}
	public String getRef_table() {
		return ref_table;
	}
	public void setRef_table(String ref_table) {
		this.ref_table = ref_table;
	}
	public String getMeta_column() {
		return meta_column;
	}
	public void setMeta_column(String meta_column) {
		this.meta_column = meta_column;
	}
	public String getRef_table_grp() {
		return ref_table_grp;
	}
	public void setRef_table_grp(String ref_table_grp) {
		this.ref_table_grp = ref_table_grp;
	}
	public String getConcept_name() {
		return concept_name;
	}
	public void setConcept_name(String concept_name) {
		this.concept_name = concept_name;
	}

}
