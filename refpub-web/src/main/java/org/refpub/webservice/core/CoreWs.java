package org.refpub.webservice.core;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.ConceptType;

@Path("concepts")
public class CoreWs {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	// @Produces({ MediaType.APPLICATION_XML })
	public ConceptList concepts() {

		ConceptList l = new ConceptList();

		ConceptType c = new ConceptType();
		c.setCode("erik");

		l.getConcepts().add(c);

		return l;
	}
}
