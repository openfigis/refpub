package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.beans.RefPubImplementation;
import org.fao.fi.refpub.webservice.CodeDTO;
import org.fao.fi.refpub.webservice.CodeSystemDTO;
import org.fao.fi.refpub.webservice.CodeSystemListDTO;
import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.ConceptListDTO;
import org.fao.fi.refpub.webservice.impl.ConceptList;
import org.fao.fi.refpub.webservice.impl.ConceptType;
import org.fao.fi.refpub.webservice.test.mock.CodeMock;
import org.fao.fi.refpub.webservice.test.mock.CodeSystemListMock;
import org.fao.fi.refpub.webservice.test.mock.CodeSystemMock;
import org.fao.fi.refpub.webservice.test.mock.ConceptListMock;
import org.fao.fi.refpub.webservice.test.mock.ConceptTypeMock;

@Path("")
@ManagedBean
public class CoreWs {

	@Inject RefPubImplementation bean;
	
	
	@Path("concepts")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	// @Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO concepts() {
		//return ConceptListMock.create();
		
		//bean = new BeanImpl();
		return ConceptList.create(bean.getCategories());
	}
	
	@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptListDTO conceptList(@PathParam("concept") String conceptCode) {
		//return ConceptTypeMock.create();
		
		return ConceptList.create(bean.getObjects(conceptCode));
	}

	/*@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptDTO concept(@PathParam("concept") String conceptCode) {
		return ConceptTypeMock.create();
		
		//return ConceptType.create(bean.getObjects(conceptCode, "FIC_ITEM"));
	}*/

	@Path("codesystems")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CodeSystemListDTO codesystems() {
		return CodeSystemListMock.create();
	}

	@Path("concept/{concept}/codesystem/{codesystem}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CodeSystemDTO codesystem(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return CodeSystemMock.create();
	}

	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public CodeDTO code(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		return CodeMock.create();
	}

}
