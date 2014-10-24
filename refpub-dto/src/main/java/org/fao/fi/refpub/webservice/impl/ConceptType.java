package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.CodeList;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class ConceptType {

	
	public static ConceptDTO create(RefPubConcept concept) {
		
		ConceptDTO c = new ConceptDTO();

		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", concept.getName()));
		
		c.setResourceUrl(ResourceUrl.create(urlChunks));
		//c.setName(concept.getName());
		c.setCodeList(CodeListType.create(concept.getCodelists()));
				
		return c;
	}
	
	public static ConceptDTO create(RefPubObject object) {
		
		ConceptDTO c = new ConceptDTO();
		if (object.getCodeList() != null) {
			for (CodeList cl : object.getCodeList()) {
				if (cl.getIsDefault() == 1) {
					List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
					urlChunks.add(new ResourceKeyValue("concept", object.getConcept()));
					urlChunks.add(new ResourceKeyValue("codesystem", cl.getName()));
					urlChunks.add(new ResourceKeyValue("code", cl.getValue()));
					c.setResourceUrl(ResourceUrl.create(urlChunks));
				}
			}
			c.setCodeList(CodeListType.createList(object, false));
		}
		
		//c.setId(Integer.toString(object.getId()));
		c.setScientificName(object.getScientific_name());
		//c.setSysItem(object.getFic_sys_item());
		//c.setCodeList(CodeListType.createList(object, false));
		c.setMultilingualName(MultilingualType.create(object, "SHORT"));
		c.setMultilingualFullName(MultilingualType.create(object, "FULL"));
		c.setMultilingualLongName(MultilingualType.create(object, "LONG"));
		c.setMultilingualLongName(MultilingualType.create(object, "OFFICIAL"));
		
		try {
			c.setHierarchy(HierarchyListType.create(object.getParents()));
		} catch (Exception ex) {
			c.setHierarchy(null);
		}
				
		return c;
	}
	
	public static ConceptDTO create(RefPubObject object, boolean forCodeList) {
		
		ConceptDTO c = new ConceptDTO();
		c.setCodeList(CodeListType.createList(object, forCodeList));
		
		return c;
	}
}
