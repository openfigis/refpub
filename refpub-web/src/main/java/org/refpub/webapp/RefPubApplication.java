package org.refpub.webapp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("rest")
public class RefPubApplication extends ResourceConfig {
	//@Context ServletContext context;
	
	public RefPubApplication() {
		register(new RefPubBeanRegistration());
		register(new CorsFilter());
		register(JacksonJsonProvider.class);
		packages("org.refpub.webservice.core");
	}

}
