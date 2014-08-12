package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.CodeSystem;

public class CodeSystemMock {

	public final static String CODE = "asfis";

	public static CodeSystem create() {
		CodeSystem c = new CodeSystem();
		c.setCode(CODE);
		c.setMultilingualName(MultilingualTypeMock.create());
		c.setResourceUrl(ResourceUrlMock.create("codesystem") + CODE);
		return c;
	}

}
