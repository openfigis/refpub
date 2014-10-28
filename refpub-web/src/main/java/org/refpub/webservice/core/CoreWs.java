package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fao.fi.refpub.webservice.Attributes;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;
import org.fao.fi.refpub.webservice.impl.CodeDTO;

@Path("")
@ManagedBean

public class CoreWs {

	@Inject RefPubInterface bean;
	
	/*
	 * Get Concepts JSON
	 */
	@Path("concept")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList concepts() {
		return bean.getAllConcept();
	}
	
	@Path("concept/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ConceptList conceptsXML() {
		return bean.getAllConcept();
	}
	
	/*
	 * --------------------------------------------------------
	 * Get all objects by concept
	 */
	
	@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList conceptList(@PathParam("concept") String conceptCode) {
		return bean.getAllObjectByConcept(conceptCode);
	}
	
	@Path("concept/{concept}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ConceptList conceptListXML(@PathParam("concept") String conceptCode) {
		return bean.getAllObjectByConcept(conceptCode);
	}

	
	/*
	 * --------------------------------------------------------
	 * Get all codesystems by concept
	 */
	
	@Path("concept/{concept}/codesystem")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept codesystems(@PathParam("concept") String concept) {
		try {
			return bean.getAllCodeSystemByConcept(concept);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	@Path("concept/{concept}/codesystem/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Concept codesystemsXML(@PathParam("concept") String concept) {
		try {
			return bean.getAllCodeSystemByConcept(concept);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	/*
	 * --------------------------------------------------------
	 * Get all objects by concept and codesystem
	 */

	@Path("concept/{concept}/codesystem/{codesystem}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList codesystem(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return bean.getObjectByCodeSystem(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ConceptList codesystemXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return bean.getObjectByCodeSystem(concept, codesystem);
	}
	
	/*
	 * --------------------------------------------------------
	 * get object by concept, codesystem and code
	 */
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept code(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		try {
			return bean.getObject(concept, codesystem, code);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Concept codeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		try {
			return bean.getObject(concept, codesystem, code);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}

	/*
	 * --------------------------------------------------------
	 * get attributes for concept and codesystem
	 */
	@Path("concept/{concept}/codesystem/{codesystem}/attribute")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Attributes code(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return bean.getAllAttributesForConceptAndCodesystem(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/attribute/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Attributes codeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return bean.getAllAttributesForConceptAndCodesystem(concept, codesystem);
	}

}
