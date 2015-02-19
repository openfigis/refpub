package org.refpub.webservice.core;

import javax.inject.Inject;

import org.fao.fi.refpub.webservice.beans.RefPubImplementation;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
// @ActivatedAlternatives(Producer.class)
@AdditionalClasses({ RefPubImplementation.class })
@RefPubSupportJaxRs
@InRequestScope
public class CoreWsTest {

	@Inject
	CoreWs coreWs;

	// @Inject
	// ContextController contextController;

	String type = "json";
	String conceptCode = "Species";
	String codesystem = "ASFIS";
	String code = "TUN";
	String attribute = "lang";
	String group = "Family";
	String subgroup = "Order";

	@Test
	public void testConcepts() {
		coreWs.concepts(type);
	}

	@Test
	@Ignore
	public void testCodeString() {
		coreWs.code(type);
	}

	@Test
	@Ignore
	public void testConceptList() {
		coreWs.conceptList(conceptCode, type);
	}

	@Test
	@Ignore
	public void testAttributeList() {
		coreWs.attributeList(conceptCode, type);
	}

	@Test
	@Ignore
	public void testCodesystems() {
		coreWs.codesystems(conceptCode, type);
	}

	@Test
	@Ignore
	public void testCodesystem() {
		coreWs.codesystem(conceptCode, codesystem, type);
	}

	@Test
	@Ignore
	public void testCodeStringStringStringString() {
		coreWs.code(conceptCode, codesystem, code, type);
	}

	@Test
	@Ignore
	public void testAttrCodeXML() {
		coreWs.attrCodeXML(conceptCode, codesystem, codesystem, attribute, type);
	}

	@Test
	@Ignore
	public void testGetGroup() {
		coreWs.getGroup(conceptCode, type);
	}

	@Test
	@Ignore
	public void testGroupMain() {
		coreWs.groupMain(conceptCode, group, type);
	}

	@Test
	@Ignore
	public void testGetGroupXML() {
		coreWs.getGroupXML(conceptCode, group, subgroup, type);
	}

}
