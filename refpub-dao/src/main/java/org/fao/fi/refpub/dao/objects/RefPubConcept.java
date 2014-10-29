package org.fao.fi.refpub.dao.objects;

import java.util.Map;

public class RefPubConcept {
	private String meta;
	private String name;
	private String name_a;
	private String name_c;
	private String name_e;
	private String name_f;
	private String name_r;
	private String name_s;
	private String table_name;
	private String table_grp_name;
	private Map<String, String> codelists;
	private URI currentURI;
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_a() {
		return name_a;
	}
	public void setName_a(String name_a) {
		this.name_a = name_a;
	}
	public String getName_c() {
		return name_c;
	}
	public void setName_c(String name_c) {
		this.name_c = name_c;
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
	public String getName_r() {
		return name_r;
	}
	public void setName_r(String name_r) {
		this.name_r = name_r;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_grp_name() {
		return table_grp_name;
	}
	public void setTable_grp_name(String table_grp_name) {
		this.table_grp_name = table_grp_name;
	}
	public Map<String, String> getCodelists() {
		return codelists;
	}
	public void setCodelists(Map<String, String> codelists) {
		this.codelists = codelists;
	}
	public URI getCurrentURI() {
		return currentURI;
	}
	public void setCurrentURI(URI currentURI) {
		this.currentURI = currentURI;
	}
}
