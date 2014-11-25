package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;
import org.glassfish.jersey.server.JSONP;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("")
@ManagedBean
@Api(value = "/rest", description = "FAO RefPub - Reference Data Browser for Fisheries")
public class CoreWs {
	
	@Inject RefPubInterface bean;
	@Context private UriInfo uriInfo;
	@Context ServletContext context;
	
	/*
	 * Get Concepts JSON
	 */
	@Path("concept")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get all concepts")
	public Response concepts() {
		return this.conceptsJSON();
	}
	
	@Path("concept/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get all concepts in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of concepts", response = ConceptList.class)}
	)
	public Response conceptsJSON() {
		setBean();
		try {
			return Response.ok(bean.getAllConcept(this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}

	@Path("concept/xml")
	@GET
	@ApiOperation(value="Get all concepts in XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of concepts", response = ConceptList.class)}
	)
	public Response conceptsXML() {
		setBean();
		try {
			return Response.ok(bean.getAllConcept(this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}

	/*
	 * --------------------------------------------------------
	 * get codesystems
	 */
	@Path("codesystem")
	@GET
	@JSONP(queryParam = "jcb")
	public Response code() {
		return this.codeJson();
	}
	
	@Path("codesystem/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get all codesystems in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of codesystems", response = ConceptList.class)}
	)
	public Response codeJson() {
		setBean();
		try {
			return Response.ok(bean.getAllCodeSystem(this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("codesystem/xml")
	@GET
	@ApiOperation(value="Get all codesystems in XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of codesystems", response = ConceptList.class)}
	)
	public Response codeXML() {
		setBean();
		try {
			return Response.ok(bean.getAllCodeSystem(this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}
	
	/*
	 * --------------------------------------------------------
	 * Get all objects by concept
	 */
	@Path("concept/{concept}")
	@GET
	@JSONP(queryParam = "jcb")
	public Response conceptList(@PathParam("concept") String conceptCode) {
		return this.conceptListJson(conceptCode);
	}
	
	@Path("concept/{concept}/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get all records for concept in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of records for a concepts", response = ConceptList.class)}
	)
	public Response conceptListJson(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true) 
									@PathParam("concept") String conceptCode) {
		setBean();
		try {
			return Response.ok(bean.getAllObjectByConcept(conceptCode, this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/xml")
	@GET
	@ApiOperation(value="Get all records for concept in XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of records for a concepts", response = ConceptList.class)}
	)
	public Response conceptListXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
								   @PathParam("concept") String conceptCode) {
		setBean();
		try {
			return Response.ok(bean.getAllObjectByConcept(conceptCode, this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}

	
	/*
	 * --------------------------------------------------------
	 * Get all codesystems by concept
	 */
	@Path("concept/{concept}/codesystem")
	@GET
	@JSONP(queryParam = "jcb")
	public Response codesystems(@PathParam("concept") String concept) {
		return this.codesystemsJson(concept);
	}
	
	@Path("concept/{concept}/codesystem/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get the list of codesystems for a certain concept in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of codesystems for a concepts", response = ConceptList.class)}
	)
	public Response codesystemsJson(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
									@PathParam("concept") String concept) {
		try {
			setBean();
			return Response.ok(bean.getAllCodeSystemByConcept(concept))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/codesystem/xml")
	@GET
	@ApiOperation(value="Get the list of codesystems for a certain concept in XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of codesystems for a concepts", response = ConceptList.class)}
	)
	public Response codesystemsXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
								   @PathParam("concept") String concept) {
		try {
			setBean();
			return Response.ok(bean.getAllCodeSystemByConcept(concept))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}
	
	/*
	 * --------------------------------------------------------
	 * Get all objects by concept and codesystem
	 */

	@Path("concept/{concept}/codesystem/{codesystem}")
	@GET
	@JSONP(queryParam = "jcb")
	public Response codesystem(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return this.codesystemJson(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get the list of records by concept and codesystem in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of records for a concepts and codesystem", response = ConceptList.class)}
	)
	public Response codesystemJson(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true) 
								   @PathParam("concept") String concept,
								   @ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)	
								   @PathParam("codesystem") String codesystem) {
		setBean();
		try {
			return Response.ok(bean.getObjectByCodeSystem(concept, codesystem, this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/xml")
	@GET
	@ApiOperation(value="Get the list of records by concept and codesystem in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of records for a concepts and codesystem", response = ConceptList.class)}
	)
	public Response codesystemXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true) 
								   @PathParam("concept") String concept,
								   @ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)	
								   @PathParam("codesystem") String codesystem) {
		setBean();
		try {
			return Response.ok(bean.getObjectByCodeSystem(concept, codesystem, this.getPageParam("count"), this.getPageParam("page")))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}
	
	/*
	 * --------------------------------------------------------
	 * get object by concept, codesystem and code
	 */
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}")
	@GET
	@JSONP(queryParam = "jcb")
	public Response code(@PathParam("concept") String concept, 
						 @PathParam("codesystem") String codesystem,
						 @PathParam("code") String code) {
		return this.codeJson(concept, codesystem, code);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get a single record by concept, codesystem and code in JSON format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response codeJson(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							 @PathParam("concept") String concept, 
							 @ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)
							 @PathParam("codesystem") String codesystem,
							 @ApiParam(name = "code", value = "Alphanumeric value of the code", required = true)
							 @PathParam("code") String code) {
		try {
			setBean();
			return Response.ok(bean.getObject(concept, codesystem, code))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/xml")
	@GET
	@ApiOperation(value="Get a single record by concept, codesystem and code in XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response attrCodeXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
								@PathParam("concept") String concept, 
								@ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)
								@PathParam("codesystem") String codesystem,
								@ApiParam(name = "code", value = "Alphanumeric value of the code", required = true)
								@PathParam("code") String code) {
		try {
			setBean();
			return Response.ok(bean.getObject(concept, codesystem, code))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}

	/*
	 * --------------------------------------------------------
	 * get attributes for concept and codesystem
	 */
	@Path("concept/{concept}/codesystem/{codesystem}/attribute")
	@GET
	@JSONP(queryParam = "jcb")
	public Response code(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		return this.codeJson(concept, codesystem);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/attribute/json")
	@GET
	@JSONP(queryParam = "jcb")
	public Response codeJson(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		setBean();
		try {
			return Response.ok(bean.getAllAttributesForConceptAndCodesystem(concept, codesystem))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/attribute/xml")
	@GET
	public Response codeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem) {
		setBean();
		try {
			return Response.ok(bean.getAllAttributesForConceptAndCodesystem(concept, codesystem))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}
	
	
	/*
	 * --------------------------------------------------------
	 * get object's attribute by concept, codesystem and code
	 */
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}")
	@GET
	@JSONP(queryParam = "jcb")
	public Response attrCode(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code, @PathParam("attribute") String attribute) {
		return this.attrCodeJson(concept, codesystem, code, attribute);
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}/json")
	@GET
	@JSONP(queryParam = "jcb")
	public Response attrCodeJson(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code, @PathParam("attribute") String attribute) {
		try {
			setBean();
			return Response.ok(bean.getAttributeForObject(concept, codesystem, code, attribute))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}/xml")
	@GET
	public Response codeXML(@PathParam("concept") String concept, @PathParam("codesystem") String codesystem,
			@PathParam("code") String code, @PathParam("attribute") String attribute) {
		try {
			setBean();
			return Response.ok(bean.getAttributeForObject(concept, codesystem, code, attribute))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		}
	}
	
	/*
	 * Grouping
	 */
	
	@Path("concept/{concept}/group/{group}")
	@GET
	@JSONP(queryParam = "jcb")
	public Response group(@PathParam("concept") String concept, @PathParam("group") String groupId) {
		return this.groupJson(concept, groupId);
	}
	
	@Path("concept/{concept}/group/{group}/json")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get a single group by concept in JSON format with all parents and childrens")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response groupJson(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							  @PathParam("concept") String concept, 
							  @ApiParam(name = "group", value = "Alphanumeric value of the group", required = true)
							  @PathParam("group") String groupId) {
		try {
			setBean();
			return Response.ok(bean.getObject(concept, groupId))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("json"))
					.build();
		}
	}
	
	@Path("concept/{concept}/group/{group}/xml")
	@GET
	@ApiOperation(value="Get a single group by concept in XML format with all parents and childrens")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response groupXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							 @PathParam("concept") String concept, 
							 @ApiParam(name = "group", value = "Alphanumeric value of the group", required = true)
							 @PathParam("group") String groupId) {
		try {
			setBean();
			return Response.ok(bean.getObject(concept, groupId))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
		} catch (Exception ex) {
			return Response.ok(bean.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, this.getMediaType("xml"))
					.build();
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
	
	private String getMediaType(String out) {
		if ("xml".equalsIgnoreCase(out)) {
			return MediaType.APPLICATION_XML;
		} else if ("json".equalsIgnoreCase(out)) {
			if (this.getPageParam("jcb") != null) {
				return "application/x-javascript";
			} else {
				return MediaType.APPLICATION_JSON;
			}
		} else {
			return MediaType.APPLICATION_JSON;
		}
	}
}
