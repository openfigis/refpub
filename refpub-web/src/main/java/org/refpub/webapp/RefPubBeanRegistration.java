package org.refpub.webapp;

import org.fao.fi.refpub.webservice.beans.RefPubImplementation;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RefPubBeanRegistration extends AbstractBinder{

	@Override
	protected void configure() {
		bind(RefPubImplementation.class).to(RefPubInterface.class);
	}

}
