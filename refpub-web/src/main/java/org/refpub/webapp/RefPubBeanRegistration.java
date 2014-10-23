package org.refpub.webapp;

import org.fao.fi.refpub.beans.RefPubImplementation;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RefPubBeanRegistration extends AbstractBinder{

	@Override
	protected void configure() {
		bind(RefPubImplementation.class).to(RefPubImplementation.class);
	}

}
