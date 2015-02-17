package org.refpub.webapp;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.fao.fi.refpub.webservice.beans.RefPubImplementation;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RefPubBeanRegistration extends AbstractBinder{
	
	@Context ServletContext context;

	@Override
	protected void configure() {
		bind(RefPubImplementation.class).to(RefPubInterface.class);
	}
}
