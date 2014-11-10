package org.fao.fi.refpub.dao.objects;

import java.util.List;

public class RefPubObject {
	private int id;
	
	private String primary_key_id;

	private String Concept;
	
	public String getConcept() {
		return Concept;
	}
	public void setConcept(String concept) {
		Concept = concept;
	}
	private String fic_sys_item;
	private String fic_item;
	private String name_e;
	private String long_name_e;
	private String full_name_e;
	
	private String name_f;
	private String long_name_f;
	private String full_name_f;
	
	private String name_s;
	private String long_name_s;
	private String full_name_s;
	
	private String name_a;
	private String long_name_a;
	private String full_name_a;
	
	private String name_c;
	private String long_name_c;
	private String full_name_c;
	
	private String name_r;
	private String long_name_r;
	private String full_name_r;
	
	private String unit;
	private String scientific_name;
	
	private String alpha3code;
	private int meta;
	
	private String entry_status;
	private String faomap_layer_id;
	private String author;
	
	private String area;
	private String un_code;
	private String undp_code;
	private String iso_2_code;
	private String iso_3_code;
	private String min_long;
	private String max_long;
	private String min_lat;
	private String max_lat;
	private String area_size;
	private String official_name_e;
	private String official_name_f;
	private String official_name_s;
	private String official_name_a;
	private String official_name_r;
	private String official_name_c;
	
	private String short_desc_e;
	private String short_desc_f;
	private String short_desc_s;
	private String short_desc_a;
	private String short_desc_r;
	private String short_desc_c;
	
	private String group_name;
	private String cd_gear_type;
	private String isscfg_code;
	private String std_abbrev;
	private String grp_ind;
	private String grp_lev;
	private String entry_date;
	
	
	private List<RefPubObject> parents;
	private List<RefPubObject> childrens;
	
	private List<CodeListDAO> codeList;
	
	private URI currentURI;
	
	private int total;

	public String getPrimary_key_id() {
		return primary_key_id;
	}
	public void setPrimary_key_id(String primary_key_id) {
		this.primary_key_id = primary_key_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFic_sys_item() {
		return fic_sys_item;
	}
	public void setFic_sys_item(String fic_sys_item) {
		this.fic_sys_item = fic_sys_item;
	}
	public String getFic_item() {
		return fic_item;
	}
	public void setFic_item(String fic_item) {
		this.fic_item = fic_item;
	}
	public String getName_e() {
		return name_e;
	}
	public void setName_e(String name_e) {
		this.name_e = name_e;
	}
	public String getLong_name_e() {
		return long_name_e;
	}
	public void setLong_name_e(String long_name_e) {
		this.long_name_e = long_name_e;
	}
	public String getFull_name_e() {
		return full_name_e;
	}
	public void setFull_name_e(String full_name_e) {
		this.full_name_e = full_name_e;
	}
	public String getName_f() {
		return name_f;
	}
	public void setName_f(String name_f) {
		this.name_f = name_f;
	}
	public String getLong_name_f() {
		return long_name_f;
	}
	public void setLong_name_f(String long_name_f) {
		this.long_name_f = long_name_f;
	}
	public String getFull_name_f() {
		return full_name_f;
	}
	public void setFull_name_f(String full_name_f) {
		this.full_name_f = full_name_f;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public String getLong_name_s() {
		return long_name_s;
	}
	public void setLong_name_s(String long_name_s) {
		this.long_name_s = long_name_s;
	}
	public String getFull_name_s() {
		return full_name_s;
	}
	public void setFull_name_s(String full_name_s) {
		this.full_name_s = full_name_s;
	}
	public String getName_a() {
		return name_a;
	}
	public void setName_a(String name_a) {
		this.name_a = name_a;
	}
	public String getLong_name_a() {
		return long_name_a;
	}
	public void setLong_name_a(String long_name_a) {
		this.long_name_a = long_name_a;
	}
	public String getFull_name_a() {
		return full_name_a;
	}
	public void setFull_name_a(String full_name_a) {
		this.full_name_a = full_name_a;
	}
	public String getName_c() {
		return name_c;
	}
	public void setName_c(String name_c) {
		this.name_c = name_c;
	}
	public String getLong_name_c() {
		return long_name_c;
	}
	public void setLong_name_c(String long_name_c) {
		this.long_name_c = long_name_c;
	}
	public String getFull_name_c() {
		return full_name_c;
	}
	public void setFull_name_c(String full_name_c) {
		this.full_name_c = full_name_c;
	}
	public String getName_r() {
		return name_r;
	}
	public void setName_r(String name_r) {
		this.name_r = name_r;
	}
	public String getLong_name_r() {
		return long_name_r;
	}
	public void setLong_name_r(String long_name_r) {
		this.long_name_r = long_name_r;
	}
	public String getFull_name_r() {
		return full_name_r;
	}
	public void setFull_name_r(String full_name_r) {
		this.full_name_r = full_name_r;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getScientific_name() {
		return scientific_name;
	}
	public void setScientific_name(String scientific_name) {
		this.scientific_name = scientific_name;
	}
	public String getAlpha3code() {
		return alpha3code;
	}
	public void setAlpha3code(String alpha3code) {
		this.alpha3code = alpha3code;
	}
	public int getMeta() {
		return meta;
	}
	public void setMeta(int meta) {
		this.meta = meta;
	}
	public String getEntry_status() {
		return entry_status;
	}
	public void setEntry_status(String entry_status) {
		this.entry_status = entry_status;
	}
	public String getFaomap_layer_id() {
		return faomap_layer_id;
	}
	public void setFaomap_layer_id(String faomap_layer_id) {
		this.faomap_layer_id = faomap_layer_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUn_code() {
		return un_code;
	}
	public void setUn_code(String un_code) {
		this.un_code = un_code;
	}
	public String getUndp_code() {
		return undp_code;
	}
	public void setUndp_code(String undp_code) {
		this.undp_code = undp_code;
	}
	public String getIso_2_code() {
		return iso_2_code;
	}
	public void setIso_2_code(String iso_2_code) {
		this.iso_2_code = iso_2_code;
	}
	public String getIso_3_code() {
		return iso_3_code;
	}
	public void setIso_3_code(String iso_3_code) {
		this.iso_3_code = iso_3_code;
	}
	public String getMin_long() {
		return min_long;
	}
	public void setMin_long(String min_long) {
		this.min_long = min_long;
	}
	public String getMax_long() {
		return max_long;
	}
	public void setMax_long(String max_long) {
		this.max_long = max_long;
	}
	public String getMin_lat() {
		return min_lat;
	}
	public void setMin_lat(String min_lat) {
		this.min_lat = min_lat;
	}
	public String getMax_lat() {
		return max_lat;
	}
	public void setMax_lat(String max_lat) {
		this.max_lat = max_lat;
	}
	public String getArea_size() {
		return area_size;
	}
	public void setArea_size(String area_size) {
		this.area_size = area_size;
	}
	public String getOfficial_name_e() {
		return official_name_e;
	}
	public void setOfficial_name_e(String official_name_e) {
		this.official_name_e = official_name_e;
	}
	public String getOfficial_name_f() {
		return official_name_f;
	}
	public void setOfficial_name_f(String official_name_f) {
		this.official_name_f = official_name_f;
	}
	public String getOfficial_name_s() {
		return official_name_s;
	}
	public void setOfficial_name_s(String official_name_s) {
		this.official_name_s = official_name_s;
	}
	public String getOfficial_name_a() {
		return official_name_a;
	}
	public void setOfficial_name_a(String official_name_a) {
		this.official_name_a = official_name_a;
	}
	public String getOfficial_name_r() {
		return official_name_r;
	}
	public void setOfficial_name_r(String official_name_r) {
		this.official_name_r = official_name_r;
	}
	public String getOfficial_name_c() {
		return official_name_c;
	}
	public void setOfficial_name_c(String official_name_c) {
		this.official_name_c = official_name_c;
	}
	public List<CodeListDAO> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<CodeListDAO> codeList) {
		this.codeList = codeList;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getCd_gear_type() {
		return cd_gear_type;
	}
	public void setCd_gear_type(String cd_gear_type) {
		this.cd_gear_type = cd_gear_type;
	}
	public List<RefPubObject> getParents() {
		return parents;
	}
	public void setParents(List<RefPubObject> parents) {
		this.parents = parents;
	}
	public List<RefPubObject> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<RefPubObject> childrens) {
		this.childrens = childrens;
	}
	public URI getCurrentURI() {
		return currentURI;
	}
	public void setCurrentURI(URI currentURI) {
		this.currentURI = currentURI;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getShort_desc_e() {
		return short_desc_e;
	}
	public void setShort_desc_e(String short_desc_e) {
		this.short_desc_e = short_desc_e;
	}
	public String getShort_desc_f() {
		return short_desc_f;
	}
	public void setShort_desc_f(String short_desc_f) {
		this.short_desc_f = short_desc_f;
	}
	public String getShort_desc_s() {
		return short_desc_s;
	}
	public void setShort_desc_s(String short_desc_s) {
		this.short_desc_s = short_desc_s;
	}
	public String getShort_desc_a() {
		return short_desc_a;
	}
	public void setShort_desc_a(String short_desc_a) {
		this.short_desc_a = short_desc_a;
	}
	public String getShort_desc_r() {
		return short_desc_r;
	}
	public void setShort_desc_r(String short_desc_r) {
		this.short_desc_r = short_desc_r;
	}
	public String getShort_desc_c() {
		return short_desc_c;
	}
	public void setShort_desc_c(String short_desc_c) {
		this.short_desc_c = short_desc_c;
	}
	public String getIsscfg_code() {
		return isscfg_code;
	}
	public void setIsscfg_code(String isscfg_code) {
		this.isscfg_code = isscfg_code;
	}
	public String getStd_abbrev() {
		return std_abbrev;
	}
	public void setStd_abbrev(String std_abbrev) {
		this.std_abbrev = std_abbrev;
	}
	public String getGrp_ind() {
		return grp_ind;
	}
	public void setGrp_ind(String grp_ind) {
		this.grp_ind = grp_ind;
	}
	public String getGrp_lev() {
		return grp_lev;
	}
	public void setGrp_lev(String grp_lev) {
		this.grp_lev = grp_lev;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
}
