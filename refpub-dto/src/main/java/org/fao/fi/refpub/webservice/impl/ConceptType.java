package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptDTO;

public class ConceptType {

	
	public static ConceptDTO create(RefPubObject cat) {
		ConceptDTO c = new ConceptDTO();
		if (cat.getCodelist_name() != null) {
			c.setCode(cat.getCodelist_name());
		}
		
		c.setAlpha3Code(cat.getAlpha3code());
		c.setId(Integer.toString(cat.getId()));
		c.setScientificName(cat.getScientific_name());
		c.setSysItem(cat.getFic_sys_item());
		
		c.setMultilingualName(MultilingualType.create(cat, "SHORT"));
		c.setMultilingualFullName(MultilingualType.create(cat, "FULL"));
		c.setMultilingualLongName(MultilingualType.create(cat, "LONG"));
		
		c.setResourceUrl(ResourceUrl.create("concept") + Integer.toString(cat.getId()));
		return c;
	}
}
