package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.ConceptList;


public class ConceptListMock {

	public static ConceptList create() {
		ConceptList l = new ConceptList();
		//l.setResourceUrl(ResourceUrlMock.create("concepts"));
		l.getConcepts().add(ConceptTypeMock.create());
		l.getConcepts().add(ConceptTypeMock.create());
		return l;
	}

}
