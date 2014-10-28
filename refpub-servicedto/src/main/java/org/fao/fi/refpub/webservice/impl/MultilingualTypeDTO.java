package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.MultilingualType;
import org.fao.fi.refpub.webservice.TextType;

public class MultilingualTypeDTO {

	public static MultilingualType create(RefPubObject cat, String type) {

		if (type.equalsIgnoreCase("short")) { return MultilingualTypeDTO.getNames(cat); }
		else if (type.equalsIgnoreCase("long")) { return MultilingualTypeDTO.getLongNames(cat); }
		else if (type.equalsIgnoreCase("full")) { return MultilingualTypeDTO.getFullNames(cat); }
		else if (type.equalsIgnoreCase("official")) { return MultilingualTypeDTO.getOfficialNames(cat); }
		else { return MultilingualTypeDTO.getNames(cat); }
	}
	
	private static MultilingualType getNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getName_a()!=null && !cat.getName_a().equalsIgnoreCase("")) {
			TextType ar = new TextType();
			ar.setLang("ar");
			ar.setValue(cat.getName_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getName_e()!=null && !cat.getName_e().equalsIgnoreCase("")) {
			TextType en = new TextType();
			en.setLang("en");
			en.setValue(cat.getName_e());
			n.getTexts().add(en);
		}
		
		if (cat.getName_s()!=null && !cat.getName_s().equalsIgnoreCase("")) {
			TextType es = new TextType();
			es.setLang("es");
			es.setValue(cat.getName_s());
			n.getTexts().add(es);
		}
		
		if (cat.getName_f()!=null && !cat.getName_f().equalsIgnoreCase("")) {
			TextType fr = new TextType();
			fr.setLang("fr");
			fr.setValue(cat.getName_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getName_r()!=null && !cat.getName_r().equalsIgnoreCase("")) {
			TextType ru = new TextType();
			ru.setLang("ru");
			ru.setValue(cat.getName_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getName_c()!=null && !cat.getName_c().equalsIgnoreCase("")) {
			TextType zh = new TextType();
			zh.setLang("zh");
			zh.setValue(cat.getName_c());
			n.getTexts().add(zh);
		}
		
		return n;
	}
	
	private static MultilingualType getFullNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getFull_name_a()!=null && !cat.getFull_name_a().equalsIgnoreCase("")) {
			TextType ar = new TextType();
			ar.setLang("ar");
			ar.setValue(cat.getFull_name_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getFull_name_e()!=null && !cat.getFull_name_e().equalsIgnoreCase("")) {
			TextType en = new TextType();
			en.setLang("en");
			en.setValue(cat.getFull_name_e());
			n.getTexts().add(en);
		}
		
		if (cat.getFull_name_s()!=null && !cat.getFull_name_s().equalsIgnoreCase("")) {
			TextType es = new TextType();
			es.setLang("es");
			es.setValue(cat.getFull_name_s());
			n.getTexts().add(es);
		}
		
		if (cat.getFull_name_f()!=null && !cat.getFull_name_f().equalsIgnoreCase("")) {
			TextType fr = new TextType();
			fr.setLang("fr");
			fr.setValue(cat.getFull_name_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getFull_name_r()!=null && !cat.getFull_name_r().equalsIgnoreCase("")) {
			TextType ru = new TextType();
			ru.setLang("ru");
			ru.setValue(cat.getFull_name_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getFull_name_c()!=null && !cat.getFull_name_c().equalsIgnoreCase("")) {
			TextType zh = new TextType();
			zh.setLang("zh");
			zh.setValue(cat.getFull_name_c());
			n.getTexts().add(zh);
		}
		return n;
		
	}
	
	private static MultilingualType getLongNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getLong_name_a()!=null && !cat.getLong_name_a().equalsIgnoreCase("")) {
			TextType ar = new TextType();
			ar.setLang("ar");
			ar.setValue(cat.getLong_name_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getLong_name_e()!=null && !cat.getLong_name_e().equalsIgnoreCase("")) {
			TextType en = new TextType();
			en.setLang("en");
			en.setValue(cat.getLong_name_e());
			n.getTexts().add(en);
		}
		
		if (cat.getLong_name_s()!=null && !cat.getLong_name_s().equalsIgnoreCase("")) {
			TextType es = new TextType();
			es.setLang("es");
			es.setValue(cat.getLong_name_s());
			n.getTexts().add(es);
		}
		
		if (cat.getLong_name_f()!=null && !cat.getLong_name_f().equalsIgnoreCase("")) {
			TextType fr = new TextType();
			fr.setLang("fr");
			fr.setValue(cat.getLong_name_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getLong_name_r()!=null && !cat.getLong_name_r().equalsIgnoreCase("")) {
			TextType ru = new TextType();
			ru.setLang("ru");
			ru.setValue(cat.getLong_name_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getLong_name_c()!=null && !cat.getLong_name_c().equalsIgnoreCase("")) {
			TextType zh = new TextType();
			zh.setLang("zh");
			zh.setValue(cat.getLong_name_c());
			n.getTexts().add(zh);
		}
		return n;
	}
	
	private static MultilingualType getOfficialNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getOfficial_name_a()!=null && !cat.getOfficial_name_a().equalsIgnoreCase("")) {
			TextType ar = new TextType();
			ar.setLang("ar");
			ar.setValue(cat.getOfficial_name_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getOfficial_name_e()!=null && !cat.getOfficial_name_e().equalsIgnoreCase("")) {
			TextType en = new TextType();
			en.setLang("en");
			en.setValue(cat.getOfficial_name_e());
			n.getTexts().add(en);
		}
		
		if (cat.getOfficial_name_s()!=null && !cat.getOfficial_name_s().equalsIgnoreCase("")) {
			TextType es = new TextType();
			es.setLang("es");
			es.setValue(cat.getOfficial_name_s());
			n.getTexts().add(es);
		}
		
		if (cat.getOfficial_name_f()!=null && !cat.getOfficial_name_f().equalsIgnoreCase("")) {
			TextType fr = new TextType();
			fr.setLang("fr");
			fr.setValue(cat.getOfficial_name_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getOfficial_name_r()!=null && !cat.getOfficial_name_r().equalsIgnoreCase("")) {
			TextType ru = new TextType();
			ru.setLang("ru");
			ru.setValue(cat.getOfficial_name_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getOfficial_name_c()!=null && !cat.getOfficial_name_c().equalsIgnoreCase("")) {
			TextType zh = new TextType();
			zh.setLang("zh");
			zh.setValue(cat.getOfficial_name_c());
			n.getTexts().add(zh);
		}
		return n;
	}

}
