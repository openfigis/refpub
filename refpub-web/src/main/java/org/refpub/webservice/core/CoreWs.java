package org.refpub.webservice.core;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
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
import org.fao.fi.refpub.webservice.cache.CacheService;
import org.fao.fi.refpub.webservice.cache.CacheServiceInterceptor;
import org.glassfish.jersey.server.JSONP;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("")
@ManagedBean
@Api(value = "/rest", description = "FAO RefPub - Reference Data Browser for Fisheries")
@CacheService
public class CoreWs {
	
	@Inject RefPubInterface bean;
	@Context private UriInfo uriInfo;
	@Context ServletContext context;
	@Context HttpHeaders httpHeaders;
	
	
	@Path("concept/{type: .*}")
	@GET
	@ApiOperation(value="Get all concepts in JSON/XML format")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval of concepts", response = ConceptList.class)}
	)
	public Response concepts( @ApiParam(name = "type", value = "Output type {json/xml}", required = true)
			 				  @PathParam("type") String type) {
		return this.ConceptsRun(this.getMediaType(httpHeaders, type));
	}
	
	@Path("codesystem/{type: .*}")
	@GET
	@ApiOperation(value="Get all codesystems in JSON/XML format")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval of codesystems", response = ConceptList.class)}
	)
	public Response code(@ApiParam(name = "type", value = "Output type {json/xml}", required = true) 
	 					 @PathParam("type") String type) {
		return this.CodeRun(this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/{type: .*}")
	@GET
	@ApiOperation(value="Get all records for concept in JSON/XML format")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval of records for a concepts", response = ConceptList.class)}
	)
	public Response conceptList(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
								@PathParam("concept") String conceptCode, 
								@ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							    @PathParam("type") String type) {
		return this.conceptListRun(conceptCode, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/attribute/{type: .*}")
	@GET
	@ApiOperation(value="Get all records for concept in JSON/XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of records for a concepts", response = ConceptList.class)}
	)
	public Response attributeList(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
								@PathParam("concept") String conceptCode, 
								@ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							    @PathParam("type") String type) {
		return this.attributeListRun(conceptCode, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/codesystem/{type: .*}")
	@GET
	@ApiOperation(value="Get the list of codesystems for a certain concept in JSON/XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of codesystems for a concepts", response = ConceptList.class)}
	)
	@Interceptors({CacheServiceInterceptor.class})
	public Response codesystems(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
								@PathParam("concept") String concept, 
								@ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							    @PathParam("type") String type) {
		return this.codesystemsRun(concept, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/{type: .*}")
	@GET
	@ApiOperation(value="Get the list of records by concept and codesystem in JSON/XML format")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval of records for a concepts and codesystem", response = ConceptList.class)}
	)
	public Response codesystem(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true) 
								   @PathParam("concept") String concept,
								   @ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)	
								   @PathParam("codesystem") String codesystem, 
								   @ApiParam(name = "type", value = "Output type {json/xml}", required = true)
								   @PathParam("type") String type) {
		return this.codesystemRun(concept, codesystem, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/{type: .*}")
	@GET
	@JSONP(queryParam = "jcb")
	@ApiOperation(value="Get a single record by concept, codesystem and code in JSON/XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response code(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							 @PathParam("concept") String concept, 
							 @ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)
							 @PathParam("codesystem") String codesystem,
							 @ApiParam(name = "code", value = "Alphanumeric value of the code", required = true)
							 @PathParam("code") String code, 
							 @ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							 @PathParam("type") String type) {
		return this.codeRun(concept, codesystem, code, this.getMediaType(httpHeaders, type));
	}
	
	/*
	 * Removed for an error in the wiki
	 */
	
	/*@Path("concept/{concept}/codesystem/{codesystem}/attribute/{type: .*}")
	@GET
	@ApiOperation(value="Get all the possible attributes for a concept in JSON/XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the attributes", response = ConceptList.class)}
	)
	public Response codeAttr(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							@PathParam("concept") String concept, 
							@ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)
							@PathParam("codesystem") String codesystem, 
							@ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							@PathParam("type") String type) {
		
		if ("json".equalsIgnoreCase(type)) {
			return this.codeAttrRun(concept, codesystem, this.getMediaType("json"));
		} else if ("xml".equalsIgnoreCase(type)) {
			return this.codeAttrRun(concept, codesystem, this.getMediaType("xml"));
		} else {
			return this.codeAttrRun(concept, codesystem, null);
		}
	}*/
	
	@Path("concept/{concept}/codesystem/{codesystem}/code/{code}/attribute/{attribute}/{type: .*}")
	@GET
	@ApiOperation(value="Get the value of a single attribute for a single record in JSON/XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the attribute", response = ConceptList.class)}
	)
	public Response attrCodeXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							@PathParam("concept") String concept, 
							@ApiParam(name = "codesystem", value = "Alphanumeric value of the codesystem", required = true)
							@PathParam("codesystem") String codesystem,
							@ApiParam(name = "code", value = "Alphanumeric value of the code", required = true)
							@PathParam("code") String code, 
							@ApiParam(name = "attribute", value = "Alphanumeric value of the attribute", required = true)
							@PathParam("attribute") String attribute, 
							@ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							@PathParam("type") String type) {
		return this.attrCodeRun(concept, codesystem, code, attribute, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/group/{type: .*}")
	@GET
	@ApiOperation(value="Get the groups for a concept in JSON/XML format")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of the attribute", response = ConceptList.class)}
	)
	public Response getGroup(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true) @PathParam("concept") String concept, 
			@ApiParam(name = "type", value = "Alphanumeric value of the concept", required = true) @PathParam("type") String type) {
		return this.getGroupRun(concept, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/group/{group}/{type: .*}")
	@GET
	@ApiOperation(value="Get a single group by concept in JSON/XML format with all parents and childrens")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response groupMain(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							 @PathParam("concept") String concept, 
							 @ApiParam(name = "group", value = "Alphanumeric value of the group", required = true)
							 @PathParam("group") String group, 
							 @ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							 @PathParam("type") String type) {
		return this.groupMainRun(concept, group, this.getMediaType(httpHeaders, type));
	}
	
	@Path("concept/{concept}/group/{group}/{subgroup}/{type: .*}")
	@GET
	@ApiOperation(value="Get a single group by concept in JSON/XML format with all parents and childrens")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successful retrieval of the record for a concepts, codesystem and code", response = ConceptList.class)}
	)
	public Response getGroupXML(@ApiParam(name = "concept", value = "Alphanumeric value of the concept", required = true)
							 @PathParam("concept") String concept, 
							 @ApiParam(name = "group", value = "Alphanumeric value of the group", required = true)
							 @PathParam("group") String group, 
							 @ApiParam(name = "subgroup", value = "Alphanumeric value of the group", required = true)
							 @PathParam("subgroup") String subgroup, 
							 @ApiParam(name = "type", value = "Output type {json/xml}", required = true)
							 @PathParam("type") String type) {
		return this.getGroupRun(concept, group, subgroup, this.getMediaType(httpHeaders, type));
	}
	
	
	
	
	
	
	/* 
	 * 
	 * Private Methods
	 * 
	*/
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
			appConfigPath = "/work/FIGIS/refpub_data/conf/refpub.properties";
		}
		
		bean.setPropertiesFile(appConfigPath);
	}
	
	private String getMediaType(HttpHeaders httpHeaders, String type) {
		if (type != null && !type.trim().equalsIgnoreCase("")) {
			if ("xml".equalsIgnoreCase(type)) {
				return MediaType.APPLICATION_XML;
			} else if ("json".equalsIgnoreCase(type)) {
				if (this.getPageParam("jcb") != null) {
					return "application/x-javascript";
				} else {
					return MediaType.APPLICATION_JSON;
				}
			}
		}
		for (MediaType mt : httpHeaders.getAcceptableMediaTypes()) {
			if (mt.equals(MediaType.APPLICATION_JSON_TYPE)) {
				if (this.getPageParam("jcb") != null) {
					return "application/x-javascript";
				} else {
					return MediaType.APPLICATION_JSON;
				}
			} else if (mt.equals(MediaType.APPLICATION_XML_TYPE)) { //note that this is application/xml and not text/xml
				return MediaType.APPLICATION_XML;
			}
		}
		return MediaType.APPLICATION_JSON;
	}
	
	/*Runners*/
	private Response ConceptsRun(String mediatype) {
		setBean();
		String count = this.getPageParam("count");
		String page = this.getPageParam("page");
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAllConcept(count, page);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response CodeRun(String mediatype) {
		setBean();
		String count = this.getPageParam("count");
		String page = this.getPageParam("page");
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAllCodeSystem(count, page);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	
	private Response attributeListRun(String conceptCode, String mediatype) {
		setBean();
		String count = this.getPageParam("count");
		String page = this.getPageParam("page");
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAllAttributesForConcept(conceptCode);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	
	private Response conceptListRun(String conceptCode, String mediatype) {
		setBean();
		String count = this.getPageParam("count");
		String page = this.getPageParam("page");
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAllObjectByConcept(conceptCode, count, page);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response codesystemsRun(String conceptCode, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAllCodeSystemByConcept(conceptCode);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response codesystemRun(String conceptCode, String codesystem, String mediatype) {
		setBean();
		String count = this.getPageParam("count");
		String page = this.getPageParam("page");
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getObjectByCodeSystem(conceptCode, codesystem, count, page);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response codeRun(String conceptCode, String codesystem, String code, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getObject(conceptCode, codesystem, code);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	/*private Response codeAttrRun(String conceptCode, String codesystem, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAllAttributesForConceptAndCodesystem(conceptCode, codesystem);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}*/
	private Response attrCodeRun(String conceptCode, String codesystem, String code, String attribute, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getAttributeForObject(conceptCode, codesystem, code, attribute);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response getGroupRun(String conceptCode, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getGroups(conceptCode);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response groupMainRun(String conceptCode, String filter, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getGroupMain(conceptCode, filter);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}
	private Response getGroupRun(String conceptCode, String filter, String group, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getGroup(conceptCode, filter, group);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}	
	/*private Response subSubgroupRun(String conceptCode, String filter, String group, String subgroup, String mediatype) {
		setBean();
		return new Execute(uriInfo, mediatype, context){
			public Object run() {
				return bean.getSubGroups(conceptCode, filter, group, subgroup);
			}
			public Object error(Exception err) {
				return bean.error(err);
			}
		}.execute();
	}*/
}
