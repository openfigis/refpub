package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.MultilingualType;
import org.fao.fi.refpub.webservice.TextLang;

public class MultilingualTypeDTO {

	public static MultilingualType create(RefPubObject cat, String type) {

		if (type.equalsIgnoreCase("short")) { return MultilingualTypeDTO.getNames(cat); }
		else if (type.equalsIgnoreCase("long")) { return MultilingualTypeDTO.getLongNames(cat); }
		else if (type.equalsIgnoreCase("full")) { return MultilingualTypeDTO.getFullNames(cat); }
		else if (type.equalsIgnoreCase("official")) { return MultilingualTypeDTO.getOfficialNames(cat); }
		else if (type.equalsIgnoreCase("short_desc")) { return MultilingualTypeDTO.getShortDesc(cat); }
		else { return MultilingualTypeDTO.getNames(cat); }
	}
	
	private static MultilingualType getNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getName_a()!=null && !cat.getName_a().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getName_a());
			n.getValues().add(ar);
		}
		
		if (cat.getName_e()!=null && !cat.getName_e().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getName_e());
			n.getValues().add(en);
		}
		
		if (cat.getName_s()!=null && !cat.getName_s().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getName_s());
			n.getValues().add(es);
		}
		
		if (cat.getName_f()!=null && !cat.getName_f().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getName_f());
			n.getValues().add(fr);
		}
		
		if (cat.getName_r()!=null && !cat.getName_r().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getName_r());
			n.getValues().add(ru);
		}
		
		if (cat.getName_c()!=null && !cat.getName_c().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getName_c());
			n.getValues().add(zh);
		}
		
		return n;
	}
	
	private static MultilingualType getFullNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getFull_name_a()!=null && !cat.getFull_name_a().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getFull_name_a());
			n.getValues().add(ar);
		}
		
		if (cat.getFull_name_e()!=null && !cat.getFull_name_e().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getFull_name_e());
			n.getValues().add(en);
		}
		
		if (cat.getFull_name_s()!=null && !cat.getFull_name_s().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getFull_name_s());
			n.getValues().add(es);
		}
		
		if (cat.getFull_name_f()!=null && !cat.getFull_name_f().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getFull_name_f());
			n.getValues().add(fr);
		}
		
		if (cat.getFull_name_r()!=null && !cat.getFull_name_r().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getFull_name_r());
			n.getValues().add(ru);
		}
		
		if (cat.getFull_name_c()!=null && !cat.getFull_name_c().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getFull_name_c());
			n.getValues().add(zh);
		}
		return n;
		
	}
	
	private static MultilingualType getLongNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getLong_name_a()!=null && !cat.getLong_name_a().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getLong_name_a());
			n.getValues().add(ar);
		}
		
		if (cat.getLong_name_e()!=null && !cat.getLong_name_e().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getLong_name_e());
			n.getValues().add(en);
		}
		
		if (cat.getLong_name_s()!=null && !cat.getLong_name_s().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getLong_name_s());
			n.getValues().add(es);
		}
		
		if (cat.getLong_name_f()!=null && !cat.getLong_name_f().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getLong_name_f());
			n.getValues().add(fr);
		}
		
		if (cat.getLong_name_r()!=null && !cat.getLong_name_r().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getLong_name_r());
			n.getValues().add(ru);
		}
		
		if (cat.getLong_name_c()!=null && !cat.getLong_name_c().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getLong_name_c());
			n.getValues().add(zh);
		}
		return n;
	}
	
	private static MultilingualType getOfficialNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getOfficial_name_a()!=null && !cat.getOfficial_name_a().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getOfficial_name_a());
			n.getValues().add(ar);
		}
		
		if (cat.getOfficial_name_e()!=null && !cat.getOfficial_name_e().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getOfficial_name_e());
			n.getValues().add(en);
		}
		
		if (cat.getOfficial_name_s()!=null && !cat.getOfficial_name_s().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getOfficial_name_s());
			n.getValues().add(es);
		}
		
		if (cat.getOfficial_name_f()!=null && !cat.getOfficial_name_f().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getOfficial_name_f());
			n.getValues().add(fr);
		}
		
		if (cat.getOfficial_name_r()!=null && !cat.getOfficial_name_r().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getOfficial_name_r());
			n.getValues().add(ru);
		}
		
		if (cat.getOfficial_name_c()!=null && !cat.getOfficial_name_c().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getOfficial_name_c());
			n.getValues().add(zh);
		}
		return n;
	}
	
	private static MultilingualType getShortDesc(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getShort_desc_a()!=null && !cat.getShort_desc_a().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getShort_desc_a());
			n.getValues().add(ar);
		}
		
		if (cat.getShort_desc_e()!=null && !cat.getShort_desc_e().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getShort_desc_e());
			n.getValues().add(en);
		}
		
		if (cat.getShort_desc_s()!=null && !cat.getShort_desc_s().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getShort_desc_s());
			n.getValues().add(es);
		}
		
		if (cat.getShort_desc_f()!=null && !cat.getShort_desc_f().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getShort_desc_f());
			n.getValues().add(fr);
		}
		
		if (cat.getShort_desc_r()!=null && !cat.getShort_desc_r().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getShort_desc_r());
			n.getValues().add(ru);
		}
		
		if (cat.getShort_desc_c()!=null && !cat.getShort_desc_c().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getShort_desc_c());
			n.getValues().add(zh);
		}
		
		return n;
	}

}
