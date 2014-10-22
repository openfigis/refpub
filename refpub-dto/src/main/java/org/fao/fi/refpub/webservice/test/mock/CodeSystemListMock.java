package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.CodeSystemListDTO;


public class CodeSystemListMock {

	public static CodeSystemListDTO create() {
		CodeSystemListDTO l = new CodeSystemListDTO();
		l.setResourceUrl(ResourceUrlMock.create("codesystems"));
		l.getConcepts().add(CodeSystemMock.create());
		l.getConcepts().add(CodeSystemMock.create());
		return l;
	}
}
