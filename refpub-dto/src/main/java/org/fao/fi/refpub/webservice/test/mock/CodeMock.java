package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.CodeDTO;

public class CodeMock {

	public static CodeDTO create() {
		return CodeSystemMock.create().getCodes().get(10);
	}

}
