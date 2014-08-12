package org.refpub.webservice.core;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.CodeSystem;
import org.fao.fi.refpub.webservice.CodeSystemList;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.test.mock.CodeSystemListMock;
import org.fao.fi.refpub.webservice.test.mock.CodeSystemMock;
import org.fao.fi.refpub.webservice.test.mock.ConceptListMock;
import org.fao.fi.refpub.webservice.test.mock.ConceptTypeMock;

@Path("")
public class CoreWs {

	@Path("concepts")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	// @Produces({ MediaType.APPLICATION_JSON })
	public ConceptList concepts() {
		return ConceptListMock.create();
	}

	@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept concept(@PathParam("concept") String conceptCode) {
		return ConceptTypeMock.create();
	}

	@Path("codesystems")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CodeSystemList codesystems() {
		return CodeSystemListMock.create();
	}

	@Path("concept/{concept}/codesystem/{codesystem}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CodeSystem codesystem(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return CodeSystemMock.create();
	}

}
