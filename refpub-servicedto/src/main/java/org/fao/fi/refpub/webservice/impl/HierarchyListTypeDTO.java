package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.Child;
import org.fao.fi.refpub.webservice.Children;
import org.fao.fi.refpub.webservice.HierarchyList;
import org.fao.fi.refpub.webservice.Parent;
import org.fao.fi.refpub.webservice.Parents;
import org.fao.fi.refpub.webservice.ValueAttrName;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class HierarchyListTypeDTO {

	public static HierarchyList create(List<RefPubObject> parents, List<RefPubObject> childrens) {
		HierarchyList hl = new HierarchyList();
		
		Children chl = new Children();
		for (RefPubObject refPubObject : childrens) {
			Child child = new Child();
			String groupName = "";
			if (refPubObject.getGroup_name() != null && !refPubObject.getGroup_name().equalsIgnoreCase("null")) {
				groupName = refPubObject.getGroup_name();
			}
			if (refPubObject.getNAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_A());
				van.setType("name");
				van.setLang("ar");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getNAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_F());
				van.setType("name");
				van.setLang("fr");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getNAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_E());
				van.setType("name");
				van.setLang("en");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getNAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_S());
				van.setType("name");
				van.setLang("es");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getNAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_R());
				van.setType("name");
				van.setLang("ru");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getNAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_C());
				van.setType("name");
				van.setLang("zh");
				van.setName(groupName);
				child.getValues().add(van);
			}
			
			if (refPubObject.getFULL_NAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_A());
				van.setType("full_name");
				van.setLang("ar");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_F());
				van.setType("full_name");
				van.setLang("fr");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_E());
				van.setType("full_name");
				van.setLang("en");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_S());
				van.setType("full_name");
				van.setLang("es");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_R());
				van.setType("full_name");
				van.setLang("ru");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_C());
				van.setType("full_name");
				van.setLang("zh");
				van.setName(groupName);
				child.getValues().add(van);
			}
			
			
			if (refPubObject.getLONG_NAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_A());
				van.setType("long_name");
				van.setLang("ar");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_F());
				van.setType("long_name");
				van.setLang("fr");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_E());
				van.setType("long_name");
				van.setLang("en");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_S());
				van.setType("long_name");
				van.setLang("es");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_R());
				van.setType("long_name");
				van.setLang("ru");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_C());
				van.setType("long_name");
				van.setLang("zh");
				van.setName(groupName);
				child.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getOFFICIAL_NAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_A());
				van.setType("official_name");
				van.setLang("ar");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_F());
				van.setType("official_name");
				van.setLang("fr");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_E());
				van.setType("official_name");
				van.setLang("en");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_S());
				van.setType("official_name");
				van.setLang("es");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_R());
				van.setType("official_name");
				van.setLang("ru");
				van.setName(groupName);
				child.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_C());
				van.setType("official_name");
				van.setLang("zh");
				van.setName(groupName);
				child.getValues().add(van);
			}
			
			
			if (refPubObject.getNAME() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME());
				van.setType("scientific_name");
				van.setLang("");
				van.setName(groupName);
				child.getValues().add(van);
			}
			
			//hl.getChildrens().add(child);
			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			
			urlChunks.add(new ResourceKeyValue("concept", refPubObject.getConcept()));
			if (refPubObject.getCodeList() != null) {
				for (CodeListDAO codelist : refPubObject.getCodeList()) {
					if (codelist.getIsDefault() == 1) {
						urlChunks.add(new ResourceKeyValue("codesystem", codelist.getName()));
						urlChunks.add(new ResourceKeyValue("code", refPubObject.getPKID()));
						//urlChunks.add(new ResourceKeyValue(codelist.getValue(), refPubObject.getPKID()));
					}
				}
			}
			child.getLinks().add(LinkRelDTO.create(refPubObject, urlChunks, groupName));
			chl.getchildren().add(child);
			
		}
		hl.getChildrens().add(chl);
		
		
		
		Parents prt = new Parents();
		for (RefPubObject refPubObject : parents) {
			Parent parent = new Parent();
			String groupName = "";
			if (refPubObject.getGroup_name() != null && !refPubObject.getGroup_name().equalsIgnoreCase("null")) {
				groupName = refPubObject.getGroup_name();
			}
			if (refPubObject.getNAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_A());
				van.setType("name");
				van.setLang("ar");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getNAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_F());
				van.setType("name");
				van.setLang("fr");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getNAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_E());
				van.setType("name");
				van.setLang("en");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getNAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_S());
				van.setType("name");
				van.setLang("es");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getNAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_R());
				van.setType("name");
				van.setLang("ru");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getNAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getNAME_C());
				van.setType("name");
				van.setLang("zh");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			
			if (refPubObject.getFULL_NAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_A());
				van.setType("full_name");
				van.setLang("ar");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_F());
				van.setType("full_name");
				van.setLang("fr");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_E());
				van.setType("full_name");
				van.setLang("en");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_S());
				van.setType("full_name");
				van.setLang("es");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_R());
				van.setType("full_name");
				van.setLang("ru");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getFULL_NAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFULL_NAME_C());
				van.setType("full_name");
				van.setLang("zh");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getLONG_NAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_A());
				van.setType("long_name");
				van.setLang("ar");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_F());
				van.setType("long_name");
				van.setLang("fr");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_E());
				van.setType("long_name");
				van.setLang("en");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_S());
				van.setType("long_name");
				van.setLang("es");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_R());
				van.setType("long_name");
				van.setLang("ru");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getLONG_NAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLONG_NAME_C());
				van.setType("long_name");
				van.setLang("zh");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getOFFICIAL_NAME_A() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_A());
				van.setType("official_name");
				van.setLang("ar");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_F() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_F());
				van.setType("official_name");
				van.setLang("fr");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_E() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_E());
				van.setType("official_name");
				van.setLang("en");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_S() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_S());
				van.setType("official_name");
				van.setLang("es");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_R() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_R());
				van.setType("official_name");
				van.setLang("ru");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			if (refPubObject.getOFFICIAL_NAME_C() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOFFICIAL_NAME_C());
				van.setType("official_name");
				van.setLang("zh");
				van.setName(groupName);
				parent.getValues().add(van);
			}
			
			//hl.getParents().add(parent);
			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			
			urlChunks.add(new ResourceKeyValue("concept", refPubObject.getConcept()));
			
			boolean urlFound = false;
			if (refPubObject.getCodeList() != null) {
				for (CodeListDAO codelist : refPubObject.getCodeList()) {
					if (codelist.getIsDefault() == 1) {
						urlChunks.add(new ResourceKeyValue("codesystem", codelist.getName()));
						urlChunks.add(new ResourceKeyValue("code", refPubObject.getPKID()));
						//urlChunks.add(new ResourceKeyValue(codelist.getValue(), refPubObject.getPKID()));
						urlFound = true;
					}
				}
			}
			
			if (!urlFound) {
				urlChunks.add(new ResourceKeyValue("group", refPubObject.getPKID()));
			}
			
			parent.getLinks().add(LinkRelDTO.create(refPubObject, urlChunks, groupName));
			prt.getParents().add(parent);
			
		}
		hl.getParents().add(prt);
		
		return hl;
	}	
}