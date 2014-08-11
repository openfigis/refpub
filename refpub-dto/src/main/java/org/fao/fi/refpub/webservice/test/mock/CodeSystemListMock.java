package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.CodeSystemList;

public class CodeSystemListMock {

	public static CodeSystemList create() {
		CodeSystemList l = new CodeSystemList();
		l.setResourceUrl(ResourceUrlMock.create("codesystems"));
		l.getConcepts().add(CodeSystemMock.create());
		l.getConcepts().add(CodeSystemMock.create());
		return l;
	}

}
