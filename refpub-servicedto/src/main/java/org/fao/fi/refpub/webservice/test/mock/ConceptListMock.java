package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.ConceptListDTO;


public class ConceptListMock {

	public static ConceptListDTO create() {
		ConceptListDTO l = new ConceptListDTO();
		l.setResourceUrl(ResourceUrlMock.create("concepts"));
		l.getConcepts().add(ConceptTypeMock.create());
		l.getConcepts().add(ConceptTypeMock.create());
		return l;
	}

}
