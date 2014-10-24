package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.ConceptDTO;

public class ConceptTypeMock {

	public final static String CODE = "species";

	public static ConceptDTO create() {
		ConceptDTO c = new ConceptDTO();
		//c.setCodelist(CODE);
		c.setMultilingualName(MultilingualTypeMock.create());
		c.setResourceUrl(ResourceUrlMock.create("concept") + CODE);
		return c;
	}

}
