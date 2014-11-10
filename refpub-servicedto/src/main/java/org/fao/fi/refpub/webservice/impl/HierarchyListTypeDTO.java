package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.Child;
import org.fao.fi.refpub.webservice.Childrens;
import org.fao.fi.refpub.webservice.HierarchyList;
import org.fao.fi.refpub.webservice.Parent;
import org.fao.fi.refpub.webservice.Parents;
import org.fao.fi.refpub.webservice.ValueAttrName;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class HierarchyListTypeDTO {

	public static HierarchyList create(List<RefPubObject> parents, List<RefPubObject> childrens) {
		HierarchyList hl = new HierarchyList();
		
		Childrens chl = new Childrens();
		for (RefPubObject refPubObject : childrens) {
			Child child = new Child();
			if (refPubObject.getName_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_a());
				van.setType("name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getName_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_f());
				van.setType("name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getName_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_e());
				van.setType("name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getName_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_s());
				van.setType("name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getName_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_r());
				van.setType("name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getName_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_c());
				van.setType("name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			
			if (refPubObject.getFull_name_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_a());
				van.setType("full_name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getFull_name_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_f());
				van.setType("full_name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getFull_name_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_e());
				van.setType("full_name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getFull_name_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_s());
				van.setType("full_name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getFull_name_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_r());
				van.setType("full_name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getFull_name_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_c());
				van.setType("full_name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getLong_name_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_a());
				van.setType("long_name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getLong_name_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_f());
				van.setType("long_name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getLong_name_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_e());
				van.setType("long_name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getLong_name_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_s());
				van.setType("long_name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getLong_name_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_r());
				van.setType("long_name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getLong_name_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_c());
				van.setType("long_name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getOfficial_name_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_a());
				van.setType("official_name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_f());
				van.setType("official_name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_e());
				van.setType("official_name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_s());
				van.setType("official_name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_r());
				van.setType("official_name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_c());
				van.setType("official_name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				child.getValues().add(van);
			}
			
			//hl.getChildrens().add(child);
			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			
			urlChunks.add(new ResourceKeyValue("concept", refPubObject.getConcept()));
			if (refPubObject.getCodeList() != null) {
				for (CodeListDAO codelist : refPubObject.getCodeList()) {
					if (codelist.getIsDefault() == 1) {
						urlChunks.add(new ResourceKeyValue("codesystem", codelist.getName()));
						urlChunks.add(new ResourceKeyValue("code", ""));
						urlChunks.add(new ResourceKeyValue(codelist.getValue(), refPubObject.getPrimary_key_id()));
					}
				}
			}
			
			child.getLinks().add(LinkRelDTO.create(refPubObject, urlChunks, refPubObject.getGroup_name()));
			chl.getchildren().add(child);
			
		}
		hl.getChildrens().add(chl);
		
		
		
		Parents prt = new Parents();
		for (RefPubObject refPubObject : parents) {
			Parent parent = new Parent();
			if (refPubObject.getName_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_a());
				van.setType("name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getName_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_f());
				van.setType("name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getName_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_e());
				van.setType("name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getName_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_s());
				van.setType("name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getName_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_r());
				van.setType("name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getName_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getName_c());
				van.setType("name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			
			if (refPubObject.getFull_name_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_a());
				van.setType("full_name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getFull_name_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_f());
				van.setType("full_name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getFull_name_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_e());
				van.setType("full_name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getFull_name_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_s());
				van.setType("full_name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getFull_name_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_r());
				van.setType("full_name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getFull_name_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getFull_name_c());
				van.setType("full_name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getLong_name_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_a());
				van.setType("long_name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getLong_name_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_f());
				van.setType("long_name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getLong_name_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_e());
				van.setType("long_name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getLong_name_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_s());
				van.setType("long_name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getLong_name_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_r());
				van.setType("long_name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getLong_name_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getLong_name_c());
				van.setType("long_name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			
			
			
			
			if (refPubObject.getOfficial_name_a() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_a());
				van.setType("official_name");
				van.setLang("ar");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_f() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_f());
				van.setType("official_name");
				van.setLang("fr");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_e() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_e());
				van.setType("official_name");
				van.setLang("en");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_s() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_s());
				van.setType("official_name");
				van.setLang("es");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_r() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_r());
				van.setType("official_name");
				van.setLang("ru");
				van.setName(refPubObject.getGroup_name());
				parent.getValues().add(van);
			}
			if (refPubObject.getOfficial_name_c() != null) {
				ValueAttrName van = new ValueAttrName();
				van.setValue(refPubObject.getOfficial_name_c());
				van.setType("official_name");
				van.setLang("zh");
				van.setName(refPubObject.getGroup_name());
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
						urlChunks.add(new ResourceKeyValue("code", ""));
						urlChunks.add(new ResourceKeyValue(codelist.getValue(), refPubObject.getPrimary_key_id()));
						urlFound = true;
					}
				}
			}
			
			if (!urlFound) {
				urlChunks.add(new ResourceKeyValue("group", Integer.toString(refPubObject.getId())));
			}
			
			parent.getLinks().add(LinkRelDTO.create(refPubObject, urlChunks, refPubObject.getGroup_name()));
			prt.getParents().add(parent);
			
		}
		hl.getParents().add(prt);
		
		return hl;
	}
	
	/*public static ParentList create(List<RefPubObject> parents) {
		ParentList pl = new ParentList();
		
		for (RefPubObject obj : parents) {
			TextTypeValueList txvl = new TextTypeValueList();
			
			txvl.getNames().add(obj.getGroup_name());
			
			if (obj.getScientific_name() != null && !obj.getScientific_name().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("scientific_name");
				txvp.setValue(obj.getScientific_name());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_a() != null && !obj.getName_a().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_a");
				txvp.setValue(obj.getName_a());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_e() != null && !obj.getName_e().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_e");
				txvp.setValue(obj.getName_e());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_f() != null && !obj.getName_f().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_f");
				txvp.setValue(obj.getName_f());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_r() != null && !obj.getName_r().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_r");
				txvp.setValue(obj.getName_r());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_s() != null && !obj.getName_s().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_s");
				txvp.setValue(obj.getName_s());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_c() != null && !obj.getName_c().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_c");
				txvp.setValue(obj.getName_c());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_a() != null && !obj.getLong_name_a().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_c");
				txvp.setValue(obj.getLong_name_a());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_e() != null && !obj.getLong_name_e().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_e");
				txvp.setValue(obj.getLong_name_e());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_f() != null && !obj.getLong_name_f().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_f");
				txvp.setValue(obj.getLong_name_f());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_r() != null && !obj.getLong_name_r().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_r");
				txvp.setValue(obj.getLong_name_r());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_s() != null && !obj.getLong_name_s().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_s");
				txvp.setValue(obj.getLong_name_s());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_c() != null && !obj.getLong_name_c().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_c");
				txvp.setValue(obj.getLong_name_c());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_a() != null && !obj.getFull_name_a().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_c");
				txvp.setValue(obj.getFull_name_a());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_e() != null && !obj.getFull_name_e().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_e");
				txvp.setValue(obj.getFull_name_e());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_f() != null && !obj.getFull_name_f().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_f");
				txvp.setValue(obj.getFull_name_f());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_r() != null && !obj.getFull_name_r().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_r");
				txvp.setValue(obj.getFull_name_r());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_s() != null && !obj.getFull_name_s().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_s");
				txvp.setValue(obj.getFull_name_s());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_c() != null && !obj.getFull_name_c().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_c");
				txvp.setValue(obj.getFull_name_c());
				txvl.getValues().add(txvp);
			}
			
			//pl.getValues().add(txvl);
		}
		
		return pl;
	}*/
}
