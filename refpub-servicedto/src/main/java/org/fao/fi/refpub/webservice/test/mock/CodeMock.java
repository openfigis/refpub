package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.Code;

public class CodeMock {

	public static Code create() {
		return CodeSystemMock.create().getCodes().get(10);
	}

}
