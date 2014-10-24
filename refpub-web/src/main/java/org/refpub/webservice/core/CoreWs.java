package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.ConceptListDTO;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;
import org.fao.fi.refpub.webservice.impl.Code;

@Path("")
@ManagedBean

public class CoreWs {

	@Inject RefPubInterface bean;
	
	@Path("concept")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO concepts() {
		//return ConceptListMock.create();
		//return ConceptList.create(bean.getConcepts());
		return bean.getAllConcept();
	}
	
	@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO conceptList(@PathParam("concept") String conceptCode) {
		//return ConceptTypeMock.create();
		//return ConceptList.createObj(bean.getObjects(conceptCode));
		return bean.getAllObjectByConcept(conceptCode);
	}

	@Path("codesystem")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptDTO codesystems() {
		//return CodeSystemListMock.create();
		try {
			//return CodeListList.create(bean.getCodeLists());
			return bean.getAllCodeSystem();
		} catch (Exception ex) {
			return Code.error(ex.getMessage());
		}
	}

	@Path("concept/{concept}/codesystem/{codesystem}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO codesystem(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		//return CodeSystemMock.create();
		//return ConceptList.createObj(bean.getListByCodeList(concept, codesystem));
		return bean.getObjectByCodeSystem(concept, codesystem);
	}

	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptDTO code(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		try {
			//return Code.create(bean.getObject(concept, codesystem, code));
			return bean.getObject(concept, codesystem, code);
		} catch (Exception ex) {
			return Code.error(ex.getMessage());
		}
	}

}
