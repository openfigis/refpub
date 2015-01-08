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

			child.setType(groupName);
			if (refPubObject.getNAME_A() != null || refPubObject.getNAME_E() != null || refPubObject.getNAME_S() != null || refPubObject.getNAME_F() != null || refPubObject.getNAME_R() != null || refPubObject.getNAME_C() != null ) {
				child.setMultilingualName(MultilingualTypeDTO.create(refPubObject, "SHORT"));
			}
			if (refPubObject.getFULL_NAME_A() != null || refPubObject.getFULL_NAME_E() != null || refPubObject.getFULL_NAME_S() != null || refPubObject.getFULL_NAME_F() != null || refPubObject.getFULL_NAME_R() != null || refPubObject.getFULL_NAME_C() != null ) {
				child.setMultilingualFullName(MultilingualTypeDTO.create(refPubObject, "FULL"));
			}
			if (refPubObject.getLONG_NAME_A() != null || refPubObject.getLONG_NAME_E() != null || refPubObject.getLONG_NAME_S() != null || refPubObject.getLONG_NAME_F() != null || refPubObject.getLONG_NAME_R() != null || refPubObject.getLONG_NAME_C() != null ) {
				child.setMultilingualLongName(MultilingualTypeDTO.create(refPubObject, "LONG"));
			}
			if (refPubObject.getOFFICIAL_NAME_A() != null || refPubObject.getOFFICIAL_NAME_E() != null || refPubObject.getOFFICIAL_NAME_S() != null || refPubObject.getOFFICIAL_NAME_F() != null || refPubObject.getOFFICIAL_NAME_R() != null || refPubObject.getOFFICIAL_NAME_C() != null ) {
				child.setMultilingualOfficialName(MultilingualTypeDTO.create(refPubObject, "LONG"));
			}

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
			parent.setType(groupName);
			if (refPubObject.getNAME_A() != null || refPubObject.getNAME_E() != null || refPubObject.getNAME_S() != null || refPubObject.getNAME_F() != null || refPubObject.getNAME_R() != null || refPubObject.getNAME_C() != null ) {
				parent.setMultilingualName(MultilingualTypeDTO.create(refPubObject, "SHORT"));
			}
			if (refPubObject.getFULL_NAME_A() != null || refPubObject.getFULL_NAME_E() != null || refPubObject.getFULL_NAME_S() != null || refPubObject.getFULL_NAME_F() != null || refPubObject.getFULL_NAME_R() != null || refPubObject.getFULL_NAME_C() != null ) {
				parent.setMultilingualFullName(MultilingualTypeDTO.create(refPubObject, "FULL"));
			}
			if (refPubObject.getLONG_NAME_A() != null || refPubObject.getLONG_NAME_E() != null || refPubObject.getLONG_NAME_S() != null || refPubObject.getLONG_NAME_F() != null || refPubObject.getLONG_NAME_R() != null || refPubObject.getLONG_NAME_C() != null ) {
				parent.setMultilingualLongName(MultilingualTypeDTO.create(refPubObject, "LONG"));
			}
			if (refPubObject.getOFFICIAL_NAME_A() != null || refPubObject.getOFFICIAL_NAME_E() != null || refPubObject.getOFFICIAL_NAME_S() != null || refPubObject.getOFFICIAL_NAME_F() != null || refPubObject.getOFFICIAL_NAME_R() != null || refPubObject.getOFFICIAL_NAME_C() != null ) {
				parent.setMultilingualOfficialName(MultilingualTypeDTO.create(refPubObject, "LONG"));
			}
			
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