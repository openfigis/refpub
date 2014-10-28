package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class ConceptTypeDTO {

	public static Concept create(RefPubConcept concept) {
		
		Concept c = new Concept();

		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", concept.getName()));
		
		c.setResourceUrl(ResourceUrlDTO.create(urlChunks));
		//c.setName(concept.getName());
		c.setCodeList(CodeListTypeDTO.create(concept.getCodelists(), concept.getName()));
				
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
					c.setResourceUrl(ResourceUrlDTO.create(urlChunks));
				}
			}
			c.setCodeList(CodeListTypeDTO.createList(object, false));
		}
		
		//c.setId(Integer.toString(object.getId()));
		c.setScientificName(object.getScientific_name());
		//c.setSysItem(object.getFic_sys_item());
		//c.setCodeList(CodeListType.createList(object, false));
		c.setMultilingualName(MultilingualTypeDTO.create(object, "SHORT"));
		c.setMultilingualFullName(MultilingualTypeDTO.create(object, "FULL"));
		c.setMultilingualLongName(MultilingualTypeDTO.create(object, "LONG"));
		c.setMultilingualLongName(MultilingualTypeDTO.create(object, "OFFICIAL"));
		
		try {
			c.setHierarchy(HierarchyListTypeDTO.create(object.getParents()));
		} catch (Exception ex) {
			c.setHierarchy(null);
		}
				
		return c;
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
