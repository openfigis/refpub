package org.refpub.webapp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class RefPubApplication extends ResourceConfig {
	//@Context ServletContext context;
	
	public RefPubApplication() {
		register(new RefPubBeanRegistration());
		register(new org.refpub.webapp.CorsFilter());
		//register(JacksonJsonProvider.class);
		packages("org.refpub.webservice.core");
	}

}
