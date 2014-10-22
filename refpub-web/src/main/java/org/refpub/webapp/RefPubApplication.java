package org.refpub.webapp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("rest")
public class RefPubApplication extends ResourceConfig {

	public RefPubApplication() {
		packages("org.refpub.webservice.core");
		register(JacksonJsonProvider.class);
		register(new RefPubBeanRegistration());

	}

}
