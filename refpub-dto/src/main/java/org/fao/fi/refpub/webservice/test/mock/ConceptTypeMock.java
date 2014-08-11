package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.ConceptType;

public class ConceptTypeMock {

	public final static String CODE = "species";

	public static ConceptType create() {
		ConceptType c = new ConceptType();
		c.setCode(CODE);
		c.setMultilingualName(MultilingualNameTypeMock.create());
		c.setResourceUrl(ResourceUrlMock.create("concept") + CODE);
		return c;
	}

}
