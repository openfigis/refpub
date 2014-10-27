package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.MultilingualTypeDTO;
import org.fao.fi.refpub.webservice.TextTypeDTO;

public class MultilingualType {

	public static MultilingualTypeDTO create(RefPubObject cat, String type) {

		if (type.equalsIgnoreCase("short")) { return MultilingualType.getNames(cat); }
		else if (type.equalsIgnoreCase("long")) { return MultilingualType.getLongNames(cat); }
		else if (type.equalsIgnoreCase("full")) { return MultilingualType.getFullNames(cat); }
		else if (type.equalsIgnoreCase("official")) { return MultilingualType.getOfficialNames(cat); }
		else { return MultilingualType.getNames(cat); }
	}
	
	private static MultilingualTypeDTO getNames(RefPubObject cat) {
		MultilingualTypeDTO n = new MultilingualTypeDTO();
		
		if (cat.getName_a()!=null && !cat.getName_a().equalsIgnoreCase("")) {
			TextTypeDTO ar = new TextTypeDTO();
			ar.setLang("ar");
			ar.setValue(cat.getName_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getName_e()!=null && !cat.getName_e().equalsIgnoreCase("")) {
			TextTypeDTO en = new TextTypeDTO();
			en.setLang("en");
			en.setValue(cat.getName_e());
			n.getTexts().add(en);
		}
		
		if (cat.getName_s()!=null && !cat.getName_s().equalsIgnoreCase("")) {
			TextTypeDTO es = new TextTypeDTO();
			es.setLang("es");
			es.setValue(cat.getName_s());
			n.getTexts().add(es);
		}
		
		if (cat.getName_f()!=null && !cat.getName_f().equalsIgnoreCase("")) {
			TextTypeDTO fr = new TextTypeDTO();
			fr.setLang("fr");
			fr.setValue(cat.getName_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getName_r()!=null && !cat.getName_r().equalsIgnoreCase("")) {
			TextTypeDTO ru = new TextTypeDTO();
			ru.setLang("ru");
			ru.setValue(cat.getName_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getName_c()!=null && !cat.getName_c().equalsIgnoreCase("")) {
			TextTypeDTO zh = new TextTypeDTO();
			zh.setLang("zh");
			zh.setValue(cat.getName_c());
			n.getTexts().add(zh);
		}
		
		return n;
	}
	
	private static MultilingualTypeDTO getFullNames(RefPubObject cat) {
		MultilingualTypeDTO n = new MultilingualTypeDTO();
		
		if (cat.getFull_name_a()!=null && !cat.getFull_name_a().equalsIgnoreCase("")) {
			TextTypeDTO ar = new TextTypeDTO();
			ar.setLang("ar");
			ar.setValue(cat.getFull_name_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getFull_name_e()!=null && !cat.getFull_name_e().equalsIgnoreCase("")) {
			TextTypeDTO en = new TextTypeDTO();
			en.setLang("en");
			en.setValue(cat.getFull_name_e());
			n.getTexts().add(en);
		}
		
		if (cat.getFull_name_s()!=null && !cat.getFull_name_s().equalsIgnoreCase("")) {
			TextTypeDTO es = new TextTypeDTO();
			es.setLang("es");
			es.setValue(cat.getFull_name_s());
			n.getTexts().add(es);
		}
		
		if (cat.getFull_name_f()!=null && !cat.getFull_name_f().equalsIgnoreCase("")) {
			TextTypeDTO fr = new TextTypeDTO();
			fr.setLang("fr");
			fr.setValue(cat.getFull_name_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getFull_name_r()!=null && !cat.getFull_name_r().equalsIgnoreCase("")) {
			TextTypeDTO ru = new TextTypeDTO();
			ru.setLang("ru");
			ru.setValue(cat.getFull_name_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getFull_name_c()!=null && !cat.getFull_name_c().equalsIgnoreCase("")) {
			TextTypeDTO zh = new TextTypeDTO();
			zh.setLang("zh");
			zh.setValue(cat.getFull_name_c());
			n.getTexts().add(zh);
		}
		return n;
		
	}
	
	private static MultilingualTypeDTO getLongNames(RefPubObject cat) {
		MultilingualTypeDTO n = new MultilingualTypeDTO();
		
		if (cat.getLong_name_a()!=null && !cat.getLong_name_a().equalsIgnoreCase("")) {
			TextTypeDTO ar = new TextTypeDTO();
			ar.setLang("ar");
			ar.setValue(cat.getLong_name_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getLong_name_e()!=null && !cat.getLong_name_e().equalsIgnoreCase("")) {
			TextTypeDTO en = new TextTypeDTO();
			en.setLang("en");
			en.setValue(cat.getLong_name_e());
			n.getTexts().add(en);
		}
		
		if (cat.getLong_name_s()!=null && !cat.getLong_name_s().equalsIgnoreCase("")) {
			TextTypeDTO es = new TextTypeDTO();
			es.setLang("es");
			es.setValue(cat.getLong_name_s());
			n.getTexts().add(es);
		}
		
		if (cat.getLong_name_f()!=null && !cat.getLong_name_f().equalsIgnoreCase("")) {
			TextTypeDTO fr = new TextTypeDTO();
			fr.setLang("fr");
			fr.setValue(cat.getLong_name_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getLong_name_r()!=null && !cat.getLong_name_r().equalsIgnoreCase("")) {
			TextTypeDTO ru = new TextTypeDTO();
			ru.setLang("ru");
			ru.setValue(cat.getLong_name_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getLong_name_c()!=null && !cat.getLong_name_c().equalsIgnoreCase("")) {
			TextTypeDTO zh = new TextTypeDTO();
			zh.setLang("zh");
			zh.setValue(cat.getLong_name_c());
			n.getTexts().add(zh);
		}
		return n;
	}
	
	private static MultilingualTypeDTO getOfficialNames(RefPubObject cat) {
		MultilingualTypeDTO n = new MultilingualTypeDTO();
		
		if (cat.getOfficial_name_a()!=null && !cat.getOfficial_name_a().equalsIgnoreCase("")) {
			TextTypeDTO ar = new TextTypeDTO();
			ar.setLang("ar");
			ar.setValue(cat.getOfficial_name_a());
			n.getTexts().add(ar);
		}
		
		if (cat.getOfficial_name_e()!=null && !cat.getOfficial_name_e().equalsIgnoreCase("")) {
			TextTypeDTO en = new TextTypeDTO();
			en.setLang("en");
			en.setValue(cat.getOfficial_name_e());
			n.getTexts().add(en);
		}
		
		if (cat.getOfficial_name_s()!=null && !cat.getOfficial_name_s().equalsIgnoreCase("")) {
			TextTypeDTO es = new TextTypeDTO();
			es.setLang("es");
			es.setValue(cat.getOfficial_name_s());
			n.getTexts().add(es);
		}
		
		if (cat.getOfficial_name_f()!=null && !cat.getOfficial_name_f().equalsIgnoreCase("")) {
			TextTypeDTO fr = new TextTypeDTO();
			fr.setLang("fr");
			fr.setValue(cat.getOfficial_name_f());
			n.getTexts().add(fr);
		}
		
		if (cat.getOfficial_name_r()!=null && !cat.getOfficial_name_r().equalsIgnoreCase("")) {
			TextTypeDTO ru = new TextTypeDTO();
			ru.setLang("ru");
			ru.setValue(cat.getOfficial_name_r());
			n.getTexts().add(ru);
		}
		
		if (cat.getOfficial_name_c()!=null && !cat.getOfficial_name_c().equalsIgnoreCase("")) {
			TextTypeDTO zh = new TextTypeDTO();
			zh.setLang("zh");
			zh.setValue(cat.getOfficial_name_c());
			n.getTexts().add(zh);
		}
		return n;
	}

}
