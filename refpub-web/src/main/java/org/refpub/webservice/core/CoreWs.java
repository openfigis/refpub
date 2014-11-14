package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.fao.fi.refpub.webservice.Attributes;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;
import org.fao.fi.refpub.webservice.impl.CodeDTO;

@Path("")
@ManagedBean

public class CoreWs {

	@Inject RefPubInterface bean;
	@Context private UriInfo uriInfo;
	@Context ServletContext context;
	
	/*
	 * Get Concepts JSON
	 */
	@Path("concept")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList concepts() {
		return this.conceptsJSON();
	}
	
	@Path("concept/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList conceptsJSON() {
		setBean();
		return bean.getAllConcept(this.getPageParam("count"), this.getPageParam("page"));
	}
	
	@Path("concept/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ConceptList conceptsXML() {
		setBean();
		return bean.getAllConcept(this.getPageParam("count"), this.getPageParam("page"));
	}

	/*
	 * --------------------------------------------------------
	 * get codesystems
	 */
	@Path("codesystem")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept code() {
		return this.codeJson();
	}
	
	@Path("codesystem/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept codeJson() {
		setBean();
		return bean.getAllCodeSystem(this.getPageParam("count"), this.getPageParam("page"));
	}
	
	@Path("codesystem/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Concept codeXML() {
		setBean();
		return bean.getAllCodeSystem(this.getPageParam("count"), this.getPageParam("page"));
	}
	
	/*
	 * --------------------------------------------------------
	 * Get all objects by concept
	 */
	@Path("concept/{concept}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList conceptList(@PathParam("concept") String conceptCode) {
		return this.conceptListJson(conceptCode);
	}
	
	@Path("concept/{concept}/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList conceptListJson(@PathParam("concept") String conceptCode) {
		setBean();
		return bean.getAllObjectByConcept(conceptCode, this.getPageParam("count"), this.getPageParam("page"));
	}
	
	@Path("concept/{concept}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ConceptList conceptListXML(@PathParam("concept") String conceptCode) {
		setBean();
		return bean.getAllObjectByConcept(conceptCode, this.getPageParam("count"), this.getPageParam("page"));
	}

	
	/*
	 * --------------------------------------------------------
	 * Get all codesystems by concept
	 */
	@Path("concept/{concept}/codesystem")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept codesystems(@PathParam("concept") String concept) {
		return this.codesystemsJson(concept);
	}
	
	@Path("concept/{concept}/codesystem/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept codesystemsJson(@PathParam("concept") String concept) {
		try {
			setBean();
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
			setBean();
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
		return this.codesystemJson(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ConceptList codesystemJson(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		setBean();
		return bean.getObjectByCodeSystem(concept, codesystem, this.getPageParam("count"), this.getPageParam("page"));
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ConceptList codesystemXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		setBean();
		return bean.getObjectByCodeSystem(concept, codesystem, this.getPageParam("count"), this.getPageParam("page"));
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
		return this.codeJson(concept, codesystem, code);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept codeJson(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		try {
			setBean();
			return bean.getObject(concept, codesystem, code);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Concept attrCodeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code) {
		try {
			setBean();
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
		return this.codeJson(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/attribute/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Attributes codeJson(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		setBean();
		return bean.getAllAttributesForConceptAndCodesystem(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/attribute/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Attributes codeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		setBean();
		return bean.getAllAttributesForConceptAndCodesystem(concept, codesystem);
	}
	
	
	/*
	 * --------------------------------------------------------
	 * get object's attribute by concept, codesystem and code
	 */
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept attrCode(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code, @PathParam("attribute") String attribute) {
		return this.attrCodeJson(concept, codesystem, code, attribute);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept attrCodeJson(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code, @PathParam("attribute") String attribute) {
		try {
			setBean();
			return bean.getAttributeForObject(concept, codesystem, code, attribute);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Concept codeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code, @PathParam("attribute") String attribute) {
		try {
			setBean();
			return bean.getAttributeForObject(concept, codesystem, code, attribute);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	@Path("concept/{concept}/group/{group}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept group(@PathParam("concept") String concept, @PathParam("group") String groupId) {
		return this.groupJson(concept, groupId);
	}
	
	@Path("concept/{concept}/group/{group}/json")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Concept groupJson(@PathParam("concept") String concept, @PathParam("group") String groupId) {
		try {
			setBean();
			return bean.getObject(concept, groupId);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	@Path("concept/{concept}/group/{group}/xml")
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Concept groupXML(@PathParam("concept") String concept, @PathParam("group") String groupId) {
		try {
			setBean();
			return bean.getObject(concept, groupId);
		} catch (Exception ex) {
			return CodeDTO.error(ex.getMessage());
		}
	}
	
	
	
	/* Private Methods*/
	private String getPageParam(String param) {
		String value = uriInfo.getQueryParameters().getFirst(param);
		if (value == null || ("").equals(value.trim())) {
			return null;
		}
		return value;
	}
	
	private void setBean() {
		bean.setUrl(uriInfo);
		
		String appConfigPath = context.getInitParameter("refpub-confFile");
		if (appConfigPath == null) {
			appConfigPath = "/opt/refpub/conf/refpub.properties";
		}
		
		bean.setPropertiesFile(appConfigPath);
	}
}
