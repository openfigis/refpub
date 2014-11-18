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
		
		if (cat.getNAME_A()!=null && !cat.getNAME_A().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getNAME_A());
			n.getValues().add(ar);
		}
		
		if (cat.getNAME_E()!=null && !cat.getNAME_E().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getNAME_E());
			n.getValues().add(en);
		}
		
		if (cat.getNAME_S()!=null && !cat.getNAME_S().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getNAME_S());
			n.getValues().add(es);
		}
		
		if (cat.getNAME_F()!=null && !cat.getNAME_F().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getNAME_F());
			n.getValues().add(fr);
		}
		
		if (cat.getNAME_R()!=null && !cat.getNAME_R().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getNAME_R());
			n.getValues().add(ru);
		}
		
		if (cat.getNAME_C()!=null && !cat.getNAME_C().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getNAME_C());
			n.getValues().add(zh);
		}
		
		return n;
	}
	
	private static MultilingualType getFullNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getFULL_NAME_A()!=null && !cat.getFULL_NAME_A().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getFULL_NAME_A());
			n.getValues().add(ar);
		}
		
		if (cat.getFULL_NAME_E()!=null && !cat.getFULL_NAME_E().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getFULL_NAME_E());
			n.getValues().add(en);
		}
		
		if (cat.getFULL_NAME_S()!=null && !cat.getFULL_NAME_S().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getFULL_NAME_S());
			n.getValues().add(es);
		}
		
		if (cat.getFULL_NAME_F()!=null && !cat.getFULL_NAME_F().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getFULL_NAME_F());
			n.getValues().add(fr);
		}
		
		if (cat.getFULL_NAME_R()!=null && !cat.getFULL_NAME_R().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getFULL_NAME_R());
			n.getValues().add(ru);
		}
		
		if (cat.getFULL_NAME_C()!=null && !cat.getFULL_NAME_C().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getFULL_NAME_C());
			n.getValues().add(zh);
		}
		return n;
		
	}
	
	private static MultilingualType getLongNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getLONG_NAME_A()!=null && !cat.getLONG_NAME_A().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getLONG_NAME_A());
			n.getValues().add(ar);
		}
		
		if (cat.getLONG_NAME_E()!=null && !cat.getLONG_NAME_E().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getLONG_NAME_E());
			n.getValues().add(en);
		}
		
		if (cat.getLONG_NAME_S()!=null && !cat.getLONG_NAME_S().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getLONG_NAME_S());
			n.getValues().add(es);
		}
		
		if (cat.getLONG_NAME_F()!=null && !cat.getLONG_NAME_F().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getLONG_NAME_F());
			n.getValues().add(fr);
		}
		
		if (cat.getLONG_NAME_R()!=null && !cat.getLONG_NAME_R().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getLONG_NAME_R());
			n.getValues().add(ru);
		}
		
		if (cat.getLONG_NAME_C()!=null && !cat.getLONG_NAME_C().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getLONG_NAME_C());
			n.getValues().add(zh);
		}
		return n;
	}
	
	private static MultilingualType getOfficialNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getOFFICIAL_NAME_A()!=null && !cat.getOFFICIAL_NAME_A().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getOFFICIAL_NAME_A());
			n.getValues().add(ar);
		}
		
		if (cat.getOFFICIAL_NAME_E()!=null && !cat.getOFFICIAL_NAME_E().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getOFFICIAL_NAME_E());
			n.getValues().add(en);
		}
		
		if (cat.getOFFICIAL_NAME_S()!=null && !cat.getOFFICIAL_NAME_S().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getOFFICIAL_NAME_S());
			n.getValues().add(es);
		}
		
		if (cat.getOFFICIAL_NAME_F()!=null && !cat.getOFFICIAL_NAME_F().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getOFFICIAL_NAME_F());
			n.getValues().add(fr);
		}
		
		if (cat.getOFFICIAL_NAME_R()!=null && !cat.getOFFICIAL_NAME_R().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getOFFICIAL_NAME_R());
			n.getValues().add(ru);
		}
		
		if (cat.getOFFICIAL_NAME_C()!=null && !cat.getOFFICIAL_NAME_C().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getOFFICIAL_NAME_C());
			n.getValues().add(zh);
		}
		return n;
	}
	
	private static MultilingualType getShortDesc(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getSHORT_DESC_A()!=null && !cat.getSHORT_DESC_A().equalsIgnoreCase("")) {
			TextLang ar = new TextLang();
			ar.setLang("ar");
			ar.setValue(cat.getSHORT_DESC_A());
			n.getValues().add(ar);
		}
		
		if (cat.getSHORT_DESC_E()!=null && !cat.getSHORT_DESC_E().equalsIgnoreCase("")) {
			TextLang en = new TextLang();
			en.setLang("en");
			en.setValue(cat.getSHORT_DESC_E());
			n.getValues().add(en);
		}
		
		if (cat.getSHORT_DESC_S()!=null && !cat.getSHORT_DESC_S().equalsIgnoreCase("")) {
			TextLang es = new TextLang();
			es.setLang("es");
			es.setValue(cat.getSHORT_DESC_S());
			n.getValues().add(es);
		}
		
		if (cat.getSHORT_DESC_F()!=null && !cat.getSHORT_DESC_F().equalsIgnoreCase("")) {
			TextLang fr = new TextLang();
			fr.setLang("fr");
			fr.setValue(cat.getSHORT_DESC_F());
			n.getValues().add(fr);
		}
		
		if (cat.getSHORT_DESC_R()!=null && !cat.getSHORT_DESC_R().equalsIgnoreCase("")) {
			TextLang ru = new TextLang();
			ru.setLang("ru");
			ru.setValue(cat.getSHORT_DESC_R());
			n.getValues().add(ru);
		}
		
		if (cat.getSHORT_DESC_C()!=null && !cat.getSHORT_DESC_C().equalsIgnoreCase("")) {
			TextLang zh = new TextLang();
			zh.setLang("zh");
			zh.setValue(cat.getSHORT_DESC_C());
			n.getValues().add(zh);
		}
		
		return n;
	}

}