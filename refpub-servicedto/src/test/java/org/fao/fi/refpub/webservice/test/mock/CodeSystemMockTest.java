package org.fao.fi.refpub.webservice.test.mock;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.fao.fi.refpub.webservice.CodeSystemDTO;
import org.junit.Test;

public class CodeSystemMockTest {

	@Test
	public void testCreate() {
		CodeSystemDTO cs = CodeSystemMock.create();
		assertTrue(cs.getCodes().size() > 0);
		assertNotNull(cs.getCodes().get(0).getResourceUrl());
	}

}