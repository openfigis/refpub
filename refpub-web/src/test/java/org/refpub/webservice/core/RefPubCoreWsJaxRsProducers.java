package org.refpub.webservice.core;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;

import org.jglue.cdiunit.internal.jaxrs.JaxRsProducers;
import org.jglue.cdiunit.internal.jaxrs.JaxRsQualifier;

public class RefPubCoreWsJaxRsProducers extends JaxRsProducers {

	@Produces
	@RequestScoped
	@JaxRsQualifier
	public HttpServletRequest getHttpServletRequest() {
		return new RefPubMockHttpServletRequestImpl();
	}
}
