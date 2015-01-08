package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.Concept;

public class ConceptTypeMock {

	public final static String CODE = "species";

	public static Concept create() {
		Concept c = new Concept();
		//c.setCodelist(CODE);
		//c.setMultilingualName(MultilingualTypeMock.create());
		//c.setResourceUrl(ResourceUrlMock.create("concept") + CODE);
		return c;
	}

}
