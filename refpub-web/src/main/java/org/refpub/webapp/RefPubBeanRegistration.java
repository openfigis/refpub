package org.refpub.webapp;

import org.fao.fi.refpub.beans.RefPubImplementation;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RefPubBeanRegistration extends AbstractBinder{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		//bind(TestBeanImplementation.class).to(TestBeanImplementation.class);
		bind(RefPubImplementation.class).to(RefPubImplementation.class);
	}

}
