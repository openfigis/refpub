package org.fao.fi.refpub.webservice.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.MultilingualType;

public class MultilingualTypeDTO {

	public static MultilingualType create(RefPubObject cat, String type) {

		if (type.equalsIgnoreCase("short")) { return MultilingualTypeDTO.getNames(cat); }
		else if (type.equalsIgnoreCase("long")) { return MultilingualTypeDTO.getLongNames(cat); }
		else if (type.equalsIgnoreCase("full")) { return MultilingualTypeDTO.getFullNames(cat); }
		else if (type.equalsIgnoreCase("official")) { return MultilingualTypeDTO.getOfficialNames(cat); }
		else if (type.equalsIgnoreCase("short_desc")) { return MultilingualTypeDTO.getShortDesc(cat); }
		else { return MultilingualTypeDTO.getNames(cat); }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static MultilingualType getNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getNAME_A()!=null && !cat.getNAME_A().equalsIgnoreCase("")) {
			n.setAR(new JAXBElement(new QName("", "AR"), String.class, cat.getNAME_A().trim()));
		}
		
		if (cat.getNAME_E()!=null && !cat.getNAME_E().equalsIgnoreCase("")) {
			n.setEN(new JAXBElement(new QName("", "EN"), String.class, cat.getNAME_E().trim()));
		}
		
		if (cat.getNAME_S()!=null && !cat.getNAME_S().equalsIgnoreCase("")) {
			n.setES(new JAXBElement(new QName("", "ES"), String.class, cat.getNAME_S().trim()));
		}
		
		if (cat.getNAME_F()!=null && !cat.getNAME_F().equalsIgnoreCase("")) {
			n.setFR(new JAXBElement(new QName("", "FR"), String.class, cat.getNAME_F().trim()));
		}
		
		if (cat.getNAME_R()!=null && !cat.getNAME_R().equalsIgnoreCase("")) {
			n.setRU(new JAXBElement(new QName("", "RU"), String.class, cat.getNAME_R().trim()));
		}
		
		if (cat.getNAME_C()!=null && !cat.getNAME_C().equalsIgnoreCase("")) {
			n.setZH(new JAXBElement(new QName("", "ZH"), String.class, cat.getNAME_C().trim()));
		}
		
		return n;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static MultilingualType getFullNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getFULL_NAME_A()!=null && !cat.getFULL_NAME_A().equalsIgnoreCase("")) {
			n.setAR(new JAXBElement(new QName("", "AR"), String.class, cat.getFULL_NAME_A().trim()));
		}
		
		if (cat.getFULL_NAME_E()!=null && !cat.getFULL_NAME_E().equalsIgnoreCase("")) {
			n.setEN(new JAXBElement(new QName("", "EN"), String.class, cat.getFULL_NAME_E().trim()));
		}
		
		if (cat.getFULL_NAME_S()!=null && !cat.getFULL_NAME_S().equalsIgnoreCase("")) {
			n.setES(new JAXBElement(new QName("", "ES"), String.class, cat.getFULL_NAME_S().trim()));
		}
		
		if (cat.getFULL_NAME_F()!=null && !cat.getFULL_NAME_F().equalsIgnoreCase("")) {
			n.setFR(new JAXBElement(new QName("", "FR"), String.class, cat.getFULL_NAME_F().trim()));
		}
		
		if (cat.getFULL_NAME_R()!=null && !cat.getFULL_NAME_R().equalsIgnoreCase("")) {
			n.setRU(new JAXBElement(new QName("", "RU"), String.class, cat.getFULL_NAME_R().trim()));
		}
		
		if (cat.getFULL_NAME_C()!=null && !cat.getFULL_NAME_C().equalsIgnoreCase("")) {
			n.setZH(new JAXBElement(new QName("", "ZH"), String.class, cat.getFULL_NAME_C().trim()));
		}
				
		/*if (cat.getFULL_NAME_A()!=null && !cat.getFULL_NAME_A().equalsIgnoreCase("")) {
			n.getARS().add(cat.getFULL_NAME_A().trim());
		}
		
		if (cat.getFULL_NAME_E()!=null && !cat.getFULL_NAME_E().equalsIgnoreCase("")) {
			n.getENS().add(cat.getFULL_NAME_E().trim());
		}
		
		if (cat.getFULL_NAME_S()!=null && !cat.getFULL_NAME_S().equalsIgnoreCase("")) {
			n.getES().add(cat.getFULL_NAME_S().trim());
		}
		
		if (cat.getFULL_NAME_F()!=null && !cat.getFULL_NAME_F().equalsIgnoreCase("")) {
			n.getFRS().add(cat.getFULL_NAME_F().trim());
		}
		
		if (cat.getFULL_NAME_R()!=null && !cat.getFULL_NAME_R().equalsIgnoreCase("")) {
			n.getRUS().add(cat.getFULL_NAME_R().trim());
		}
		
		if (cat.getFULL_NAME_C()!=null && !cat.getFULL_NAME_C().equalsIgnoreCase("")) {
			n.getZHS().add(cat.getFULL_NAME_C().trim());
		}*/
		
		return n;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static MultilingualType getLongNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getLONG_NAME_A()!=null && !cat.getLONG_NAME_A().equalsIgnoreCase("")) {
			n.setAR(new JAXBElement(new QName("", "AR"), String.class, cat.getLONG_NAME_A().trim()));
		}
		
		if (cat.getLONG_NAME_E()!=null && !cat.getLONG_NAME_E().equalsIgnoreCase("")) {
			n.setEN(new JAXBElement(new QName("", "EN"), String.class, cat.getLONG_NAME_E().trim()));
		}
		
		if (cat.getLONG_NAME_S()!=null && !cat.getLONG_NAME_S().equalsIgnoreCase("")) {
			n.setES(new JAXBElement(new QName("", "ES"), String.class, cat.getLONG_NAME_S().trim()));
		}
		
		if (cat.getLONG_NAME_F()!=null && !cat.getLONG_NAME_F().equalsIgnoreCase("")) {
			n.setFR(new JAXBElement(new QName("", "FR"), String.class, cat.getLONG_NAME_F().trim()));
		}
		
		if (cat.getLONG_NAME_R()!=null && !cat.getLONG_NAME_R().equalsIgnoreCase("")) {
			n.setRU(new JAXBElement(new QName("", "RU"), String.class, cat.getLONG_NAME_R().trim()));
		}
		
		if (cat.getLONG_NAME_C()!=null && !cat.getLONG_NAME_C().equalsIgnoreCase("")) {
			n.setZH(new JAXBElement(new QName("", "ZH"), String.class, cat.getLONG_NAME_C().trim()));
		}
				
		/*if (cat.getLONG_NAME_A()!=null && !cat.getLONG_NAME_A().equalsIgnoreCase("")) {
			n.getARS().add(cat.getLONG_NAME_A().trim());
		}
		
		if (cat.getLONG_NAME_E()!=null && !cat.getLONG_NAME_E().equalsIgnoreCase("")) {
			n.getENS().add(cat.getLONG_NAME_E().trim());
		}
		
		if (cat.getLONG_NAME_S()!=null && !cat.getLONG_NAME_S().equalsIgnoreCase("")) {
			n.getES().add(cat.getLONG_NAME_S().trim());
		}
		
		if (cat.getLONG_NAME_F()!=null && !cat.getLONG_NAME_F().equalsIgnoreCase("")) {
			n.getFRS().add(cat.getLONG_NAME_F().trim());
		}
		
		if (cat.getLONG_NAME_R()!=null && !cat.getLONG_NAME_R().equalsIgnoreCase("")) {
			n.getRUS().add(cat.getLONG_NAME_R().trim());
		}
		
		if (cat.getLONG_NAME_C()!=null && !cat.getLONG_NAME_C().equalsIgnoreCase("")) {
			n.getZHS().add(cat.getLONG_NAME_C().trim());
		}*/
		return n;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static MultilingualType getOfficialNames(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
		
		if (cat.getOFFICIAL_NAME_A()!=null && !cat.getOFFICIAL_NAME_A().equalsIgnoreCase("")) {
			n.setAR(new JAXBElement(new QName("", "AR"), String.class, cat.getOFFICIAL_NAME_A().trim()));
		}
		
		if (cat.getOFFICIAL_NAME_E()!=null && !cat.getOFFICIAL_NAME_E().equalsIgnoreCase("")) {
			n.setEN(new JAXBElement(new QName("", "EN"), String.class, cat.getOFFICIAL_NAME_E().trim()));
		}
		
		if (cat.getOFFICIAL_NAME_S()!=null && !cat.getOFFICIAL_NAME_S().equalsIgnoreCase("")) {
			n.setES(new JAXBElement(new QName("", "ES"), String.class, cat.getOFFICIAL_NAME_S().trim()));
		}
		
		if (cat.getOFFICIAL_NAME_F()!=null && !cat.getOFFICIAL_NAME_F().equalsIgnoreCase("")) {
			n.setFR(new JAXBElement(new QName("", "FR"), String.class, cat.getOFFICIAL_NAME_F().trim()));
		}
		
		if (cat.getOFFICIAL_NAME_R()!=null && !cat.getOFFICIAL_NAME_R().equalsIgnoreCase("")) {
			n.setRU(new JAXBElement(new QName("", "RU"), String.class, cat.getOFFICIAL_NAME_R().trim()));
		}
		
		if (cat.getOFFICIAL_NAME_C()!=null && !cat.getOFFICIAL_NAME_C().equalsIgnoreCase("")) {
			n.setZH(new JAXBElement(new QName("", "ZH"), String.class, cat.getOFFICIAL_NAME_C().trim()));
		}

		return n;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static MultilingualType getShortDesc(RefPubObject cat) {
		MultilingualType n = new MultilingualType();
			
		if (cat.getSHORT_DESC_A()!=null && !cat.getSHORT_DESC_A().equalsIgnoreCase("")) {
			n.setAR(new JAXBElement(new QName("", "AR"), String.class, cat.getSHORT_DESC_A().trim()));
		}
		
		if (cat.getSHORT_DESC_E()!=null && !cat.getSHORT_DESC_E().equalsIgnoreCase("")) {
			n.setEN(new JAXBElement(new QName("", "EN"), String.class, cat.getSHORT_DESC_E().trim()));
		}
		
		if (cat.getSHORT_DESC_S()!=null && !cat.getSHORT_DESC_S().equalsIgnoreCase("")) {
			n.setES(new JAXBElement(new QName("", "ES"), String.class, cat.getSHORT_DESC_S().trim()));
		}
		
		if (cat.getSHORT_DESC_F()!=null && !cat.getSHORT_DESC_F().equalsIgnoreCase("")) {
			n.setFR(new JAXBElement(new QName("", "FR"), String.class, cat.getSHORT_DESC_F().trim()));
		}
		
		if (cat.getSHORT_DESC_R()!=null && !cat.getSHORT_DESC_R().equalsIgnoreCase("")) {
			n.setRU(new JAXBElement(new QName("", "RU"), String.class, cat.getSHORT_DESC_R().trim()));
		}
		
		if (cat.getSHORT_DESC_C()!=null && !cat.getSHORT_DESC_C().equalsIgnoreCase("")) {
			n.setZH(new JAXBElement(new QName("", "ZH"), String.class, cat.getSHORT_DESC_C().trim()));
		}

		return n;
	}

}