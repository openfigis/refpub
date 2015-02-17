package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.AttributesType;
import org.fao.fi.refpub.webservice.Child;
import org.fao.fi.refpub.webservice.Children;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.GroupsType;
import org.fao.fi.refpub.webservice.HierarchyList;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class ConceptTypeDTO {

	public static Concept create(RefPubConcept concept) {
		
		Concept c = new Concept();

		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", concept.getName()));
		c.getLinks().add(LinkRelDTO.create(concept, urlChunks, concept.getName()));
		
		if (concept.isHasGroup()) {
			List<ResourceKeyValue> urlChunksGroups = new ArrayList<ResourceKeyValue>();
			urlChunksGroups.add(new ResourceKeyValue("concept", concept.getName()));
			urlChunksGroups.add(new ResourceKeyValue("group", ""));
			GroupsType grp = new GroupsType();
			grp.getLinks().add(LinkRelDTO.create(concept, urlChunksGroups, "groups"));
			c.setGroups(grp);
		}
		AttributesType attype = new AttributesType();
		List<ResourceKeyValue> urlChunksAttributes = new ArrayList<ResourceKeyValue>();
		urlChunksAttributes.add(new ResourceKeyValue("concept", concept.getName()));
		urlChunksAttributes.add(new ResourceKeyValue("attribute", ""));
		attype.getLinks().add(LinkRelDTO.create(concept, urlChunksAttributes, "attributes"));
		
		c.setCodeList(CodeListTypeDTO.create(concept));
		c.setAttributes(attype);
				
		return c;
	}
	
	public static Concept create(RefPubObject object) {
		
		Concept c = new Concept();

		if (object.getCodeList() != null) {
			for (CodeListDAO cl : object.getCodeList()) {
				if (cl.getIsDefault() == 1) {
					List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
					urlChunks.add(new ResourceKeyValue("concept", object.getConcept()));
					urlChunks.add(new ResourceKeyValue("codesystem", cl.getName()));
					urlChunks.add(new ResourceKeyValue("code", cl.getValue()));
					c.getLinks().add(LinkRelDTO.create(object, urlChunks, object.getSelfRel()));
				}
			}
			c.setCodeList(CodeListTypeDTO.createList(object, false));
		} else if (object.isIs_group()) {
			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			urlChunks.add(new ResourceKeyValue("concept", object.getConcept()));
			if (object.getFILTER() != null) {
				urlChunks.add(new ResourceKeyValue("group", object.getFILTER()));
			}
			if (object.getPKID() != null) {
				urlChunks.add(new ResourceKeyValue("", object.getPKID()));
			}
			c.getLinks().add(LinkRelDTO.create(object, urlChunks, "figis_group"));
		}
		
		
		if (object.getNAME() != null) {
			c.setName(object.getNAME());
		}
		
		if (object.getSHORTNAME() != null && object.getNAME_E() == null) {
			object.setNAME_E(object.getSHORTNAME());
		}
		
		if (object.getNAME_E() != null ||
			object.getNAME_F() != null ||
			object.getNAME_S() != null ||
			object.getNAME_R() != null ||
			object.getNAME_A() != null ||
			object.getNAME_C() != null ) {
		
			c.setMultilingualName(MultilingualTypeDTO.create(object, "SHORT"));
		}
		if (object.getFULL_NAME_E() != null ||
			object.getFULL_NAME_F() != null ||
			object.getFULL_NAME_S() != null ||
			object.getFULL_NAME_R() != null ||
			object.getFULL_NAME_A() != null ||
			object.getFULL_NAME_C() != null ) {
			
			c.setMultilingualFullName(MultilingualTypeDTO.create(object, "FULL"));
		}
		
		if (object.getLONG_NAME_E() != null ||
			object.getLONG_NAME_F() != null ||
			object.getLONG_NAME_S() != null ||
			object.getLONG_NAME_R() != null ||
			object.getLONG_NAME_A() != null ||
			object.getLONG_NAME_C() != null ) {
			
			c.setMultilingualLongName(MultilingualTypeDTO.create(object, "LONG"));
		}
		if (object.getOFFICIAL_NAME_E() != null ||
			object.getOFFICIAL_NAME_F() != null ||
			object.getOFFICIAL_NAME_S() != null ||
			object.getOFFICIAL_NAME_R() != null ||
			object.getOFFICIAL_NAME_A() != null ||
			object.getOFFICIAL_NAME_C() != null ) {
		
			c.setMultilingualOfficialName(MultilingualTypeDTO.create(object, "OFFICIAL"));
		}
		if (object.getSHORT_DESC_E() != null ||
			object.getSHORT_DESC_F() != null ||
			object.getSHORT_DESC_S() != null ||
			object.getSHORT_DESC_R() != null ||
			object.getSHORT_DESC_A() != null ||
			object.getSHORT_DESC_C() != null ) {
			
			c.setMultilingualShortDescription(MultilingualTypeDTO.create(object, "SHORT_DESC"));
		}
		
		if (object.getSCIENTIFIC_NAME() != null && !object.getSCIENTIFIC_NAME().trim().equalsIgnoreCase("")) {
			c.setScientificName(object.getSCIENTIFIC_NAME());
		}
		
		if (object.getVESSELNAME() != null && !object.getVESSELNAME().trim().equalsIgnoreCase("")) {
			c.setVesselName(object.getVESSELNAME());
		}
		
		try {
			c.setHierarchy(HierarchyListTypeDTO.create(object.getParents(), object.getChildrens()));
		} catch (Exception ex) {
			c.setHierarchy(null);
		}
				
		return c;
	}
	
	public static Concept createDeep(RefPubObject object) {
		Concept c = new Concept();
		c = ConceptTypeDTO.create(object);
		c.setHierarchy(null);
		
		if (object.getChildrens() != null && object.getChildrens().size() > 0) {
			for (RefPubObject o : object.getChildrens()) {
				Children chl = new Children();
				Child child = new Child();
				child.getConcepts().add(ConceptTypeDTO.createDeep(o));
				chl.getchildren().add(child);
				if (c.getHierarchy() != null) {
					c.getHierarchy().getChildrens().add(chl);
				} else {
					HierarchyList hl = new HierarchyList();
					hl.getChildrens().add(chl);
					c.setHierarchy(hl);
				}
			}
		}
		return c;
		
	}
	
	public static Concept createWithAttributes(RefPubObject object) {
		Concept concept = ConceptTypeDTO.create(object);
		if (object.getPKID() == null) { 
			return concept; 
		}
		concept.setAttr(AttrListDTO.create(object));
		return concept;
	}
	
	public static Concept create(RefPubObject object, boolean forCodeList) {
		
		Concept c = new Concept();
		c.setCodeList(CodeListTypeDTO.createList(object, forCodeList));
		
		return c;
	}
	
	public static Concept error(String message) {
		Concept c = new Concept();
		return c;
	}
}