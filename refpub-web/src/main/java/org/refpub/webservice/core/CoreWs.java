package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.beans.RefPubImplementation;
import org.fao.fi.refpub.webservice.CodeSystemDTO;
import org.fao.fi.refpub.webservice.CodeSystemListDTO;
import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.ConceptListDTO;
import org.fao.fi.refpub.webservice.impl.Code;
import org.fao.fi.refpub.webservice.impl.CodeListList;
import org.fao.fi.refpub.webservice.impl.ConceptList;
import org.fao.fi.refpub.webservice.test.mock.CodeSystemMock;

@Path("")
@ManagedBean
public class CoreWs {

	@Inject RefPubImplementation bean;
	
	
	@Path("concepts")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO concepts() {
		//return ConceptListMock.create();
		return ConceptList.create(bean.getConcepts());
	}
	
	@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO conceptList(@PathParam("concept") String conceptCode) {
		//return ConceptTypeMock.create();
		return ConceptList.createObj(bean.getObjects(conceptCode));
	}

	@Path("codesystems")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptDTO codesystems() {
		//return CodeSystemListMock.create();
		return CodeListList.create(bean.getCodeLists());
	}

	@Path("concept/{concept}/codesystem/{codesystem}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO codesystem(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		//return CodeSystemMock.create();
		return ConceptList.createObj(bean.getListByCodeList(concept, codesystem));
	}

	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptDTO code(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		return Code.create(bean.getObject(concept, codesystem, code));
	}

}
