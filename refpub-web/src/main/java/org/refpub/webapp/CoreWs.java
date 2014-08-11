package org.refpub.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.ConceptList;

@Path("concepts")
public class CoreWs {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ConceptList concepts() {
		return new ConceptList();
	}

}
