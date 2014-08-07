package org.refpub.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.ConceptList;

public class CoreWs {

	@GET
	@Path("/concepts/")
	@Produces(MediaType.APPLICATION_JSON)
	public ConceptList concepts() {
		return new ConceptList();
	}

}
