package org.refpub.webapp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class RefPubApplication extends ResourceConfig {
	
	public RefPubApplication() {
		register(new RefPubBeanRegistration());
		register(new org.refpub.webapp.CorsFilter());
	    packages("com.wordnik.swagger.jersey.listing");
		//register(JacksonJsonProvider.class);
		packages("org.refpub.webservice.core");
	}
}
