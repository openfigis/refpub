package org.refpub.webservice.core;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.ConceptType;
import org.fao.fi.refpub.webservice.test.mock.ConceptListMock;
import org.fao.fi.refpub.webservice.test.mock.ConceptTypeMock;

@Path("")
public class CoreWs {

	@Path("concepts")
	@GET
	// @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList concepts() {
		return ConceptListMock.create();
	}

	@Path("concept/{conceptCode}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ConceptType concept(@PathParam("conceptCode") String conceptCode) {
		return ConceptTypeMock.create();
	}

}
