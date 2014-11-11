package org.fao.fi.refpub.webservice.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.math.NumberUtils;
import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.URI;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.utils.Utils;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;
import org.fao.fi.refpub.persistence.PersistenceServiceInterface;
import org.fao.fi.refpub.webservice.Attributes;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.impl.AttributeListTypeDTO;
import org.fao.fi.refpub.webservice.impl.CodeDTO;
import org.fao.fi.refpub.webservice.impl.CodeListListDTO;
import org.fao.fi.refpub.webservice.impl.ConceptListDTO;
import org.fao.fi.refpub.webservice.objects.Constants;

/*
 * Implementation of the CDI bean that implements the RefPub Restful API functionalities
 */

@RequestScoped
@Named("refpub")
public class RefPubImplementation implements RefPubInterface {
	@Produces
	
	UriInfo URI;
	private static String DEFAULT_COUNT = "100";
	
	@Override
	public void setUrl(UriInfo uri) {
		if (uri != null) {
			URI = uri;
		} else {
			URI = null;
		}
	}
	
	@Override
	public ConceptList getAllConcept(String count, String page) {
		return ConceptListDTO.create(this.getAllConcepts(count, page));
	}


	@Override
	public Concept getConcept(String concept, String count, String page) {
		return null;
	}


	@Override
	public ConceptList getAllObjectByConcept(String concept, String count, String page) {
		return ConceptListDTO.createObj(this.getAllObjectsForConcept(concept, count, page));
	}


	@Override
	public Concept getObject(String concept, String codesystem, String code) {
		return CodeDTO.create(this.getSingleObject(concept, codesystem, code));
	}
	
	@Override
	public Concept getObject(String concept, String code) {
		return CodeDTO.create(this.getSingleObject(concept, code));
	}

	
	
	@Override
	///////////////////////////
	public Concept getAttributeForObject(String concept, String codesystem,
			String code, String attribute) {
		return CodeDTO.create(this.getSingleAttributeForSingleObject(concept, codesystem, code, attribute));
	}


	@Override
	public Concept getAllCodeSystem(String count, String page) {
		return CodeListListDTO.create(this.getAllCodeList(count, page));
	}
	

	@Override
	public ConceptList getObjectByCodeSystem(String concept, String codesystem, String count, String page) {
		return ConceptListDTO.createObj(this.getObjectsByCodeList(concept, codesystem, count, page));
	}
	
	
	@Override
	public Concept getAllCodeSystemByConcept(String concept) {
		return CodeListListDTO.create(this.getCodeSystemByConcept(concept));
	}
	
	
	@Override
	public Attributes getAllAttributesForConceptAndCodesystem(
			String concept, String codesystem) {
		return AttributeListTypeDTO.create(this.attributeListByConceptCodelist(concept, codesystem));
	}
	
	
	

	
	private List<RefPubConcept> getAllConcepts(String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		
		List<RefPubConcept> concepts = new ArrayList<RefPubConcept>();
		
		List<MDConcept> listOfConcepts = ps.getConcepts(); //Retrieves the concepts from the persistence layer
		for (MDConcept c : listOfConcepts) { //for each concept try to set-up the codelist to be pushed into RefPubObjects
			RefPubConcept concept = new RefPubConcept();
			concept.setName(c.getRest_concept());
			concept.setTable_name(c.getTable_name());
			concept.setTable_grp_name(c.getTable_group());
			/*concept.setMeta(c.getMeta_id());*/
			
			List<MDCodelist> codelists = ps.getCodelistForConcept(concept.getName());
			
			Map<String,String> codemap = new HashMap<String, String>();
			for (MDCodelist code : codelists) {
				codemap.put(code.getCode_column(), code.getCode_name());
			}
			concept.setCodelists(codemap);
			
			// TODO Add lookup to MD-REFOBJECT to retrieve the concept translations
			concept.setCurrentURI(this.BuildURI(count, page));
			concepts.add(concept);
		}
		
		return concepts;
	}
	
	private RefPubConcept getSingleConcept(String Concept) {
		RefPubConcept concept = new RefPubConcept();
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		
		MDConcept c = ps.getConcept(Concept);
		concept.setName(c.getRest_concept());
		concept.setTable_name(c.getTable_name());
		concept.setTable_grp_name(c.getTable_group());
		concept.setMeta(c.getMeta_id());
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(concept.getName());
		Map<String,String> codemap = new HashMap<String, String>();
		for (MDCodelist code : codelists) {
			codemap.put(code.getCode_column(), code.getCode_name());
		}
		concept.setCodelists(codemap);
		concept.setCurrentURI(this.BuildURI("all", null));
		return concept;
	}
	
	
	private List<RefPubObject> getAllObjectsForConcept(String concept, String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		RefPubConcept cp = this.getSingleConcept(concept); 
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		
		MDConcept mdconcept = ps.getConcept(concept);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		List<RefPubObject> objs = ps.getObjects(cp.getMeta(),
												mdconcept.getMeta_column(),
												"FIGIS." + cp.getTable_name(), 
												tbl.getPrimary_key(),
												min, max);
		if (objs == null) { return new ArrayList<RefPubObject>(); }
		List<MDCodelist> codelists = ps.getCodelistForConcept(concept);
		
		int counter = 0;
		for (RefPubObject obj : objs) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI(count, page));
			objs.set(counter, obj);
			counter++;
		}
		
		return objs;
	}
		
	private RefPubObject getSingleObject(String concept, String codelist, String code) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		MDConcept mdconcept = ps.getConcept(concept);
		MDCodelist mdcodelist =  ps.getCodeList(concept, codelist);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		RefPubObject obj = ps.getObject("FIGIS." + mdconcept.getTable_name(), mdcodelist.getCode_column(), code, tbl.getPrimary_key());
		
		if (obj == null) {
			return new RefPubObject();
		}
		
		List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), obj);
		obj.setConcept(concept);
		obj.setCodeList(codemap);
		
		List<RefPubObject> parents = ps.getParentHierarchy(  "FIGIS." + mdconcept.getTable_name(),
															 "FIGIS." +  mdconcept.getTable_group(), 
															 mdconcept.getTable_group_member(),
															 mdconcept.getMeta_column(),
															 obj.getPrimary_key_id(),
															 mdconcept.getTable_group_column(),
															 tbl.getPrimary_key());
		List<RefPubObject> childrens = ps.getChildrenHierarchy(  "FIGIS." + mdconcept.getTable_name(),
																 "FIGIS." +  mdconcept.getTable_group(), 
																 mdconcept.getTable_group_member(),
																 mdconcept.getMeta_column(),
																 obj.getPrimary_key_id(),
																 mdconcept.getTable_group_column(),
																 tbl.getPrimary_key());
		for (int x = 0; x < parents.size(); x++) {
			parents.get(x).setCurrentURI(this.BuildURI("1", "1"));
			parents.get(x).setConcept(obj.getConcept());
			parents.get(x).setCodeList(Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), parents.get(x)));
		}
		for (int x = 0; x < childrens.size(); x++) {
			childrens.get(x).setCurrentURI(this.BuildURI("1", "1"));
			childrens.get(x).setConcept(obj.getConcept());
			childrens.get(x).setCodeList(Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), childrens.get(x)));
		}
		
		obj.setParents(parents);
		obj.setChildrens(childrens);
		
		obj.setCurrentURI(this.BuildURI(null, null));
		return obj;
	}
	
	private RefPubObject getSingleObject(String concept, String code) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		MDConcept mdconcept = ps.getConcept(concept);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		RefPubObject obj = ps.getObjectById("FIGIS." + mdconcept.getTable_name(), tbl.getPrimary_key(), code);
		
		if (obj == null) {
			return new RefPubObject();
		}
		
		List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), obj);
		obj.setConcept(concept);
		obj.setCodeList(codemap);
		
			
		List<RefPubObject> parents = ps.getParentHierarchy(  "FIGIS." + mdconcept.getTable_name(),
															 "FIGIS." +  mdconcept.getTable_group(), 
															 mdconcept.getTable_group_member(),
															 mdconcept.getMeta_column(),
															 obj.getPrimary_key_id(),
															 mdconcept.getTable_group_column(),
															 tbl.getPrimary_key());
		List<RefPubObject> childrens = ps.getChildrenHierarchy(  "FIGIS." + mdconcept.getTable_name(),
																 "FIGIS." +  mdconcept.getTable_group(), 
																 mdconcept.getTable_group_member(),
																 mdconcept.getMeta_column(),
																 obj.getPrimary_key_id(),
																 mdconcept.getTable_group_column(),
																 tbl.getPrimary_key());
		
		
		for (int x = 0; x < parents.size(); x++) {
			parents.get(x).setCurrentURI(this.BuildURI("1", "1"));
			parents.get(x).setConcept(obj.getConcept());
			parents.get(x).setCodeList(Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), parents.get(x)));
		}
		for (int x = 0; x < childrens.size(); x++) {
			childrens.get(x).setCurrentURI(this.BuildURI("1", "1"));
			childrens.get(x).setConcept(obj.getConcept());
			childrens.get(x).setCodeList(Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), childrens.get(x)));

		}
		
		obj.setParents(parents);
		obj.setChildrens(childrens);
		
		obj.setCurrentURI(this.BuildURI("1", "1")); 
		return obj;
	}
	
	private RefPubObject getSingleAttributeForSingleObject(String concept, String codelist, String code, String attribute) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		MDConcept mdconcept = ps.getConcept(concept);
		MDCodelist mdcodelist =  ps.getCodeList(concept, codelist);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		RefPubObject obj = ps.getAttributeForSingleObject("FIGIS." + mdconcept.getTable_name(), 
														  mdcodelist.getCode_column(), 
														  code, 
														  tbl.getPrimary_key(),
														  attribute);
		
		if (obj == null) {
			return new RefPubObject();
		}
		return obj;
	}
	
	private RefPubObject getAllCodeList(String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		RefPubObject returnObj = new RefPubObject();
		
		List<MDCodelist> codelists = ps.getCodeList_list();
		
		List<CodeListDAO> l = new ArrayList<CodeListDAO>();
		
		for (MDCodelist cl : codelists) {
			CodeListDAO codeListObj = new CodeListDAO();
			codeListObj.setName(cl.getCode_name());
			codeListObj.setValue(cl.getRest_concept());
			l.add(codeListObj);
		}
		returnObj.setCodeList(l);
		returnObj.setCurrentURI(this.BuildURI(count, page));
		return returnObj;
	}
	
	private List<RefPubObject> getObjectsByCodeList(String concept, String codelist, String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
	
		MDConcept con = ps.getConcept(concept);
		MDCodelist cl = ps.getCodeList(concept, codelist);
		if (cl == null) {
			return new ArrayList<RefPubObject>();
		}
		TableInfo ti = ps.getTableInfo(cl.getTable_name());
		List<RefPubObject> returnList = ps.getObjectsByCodeList("FIGIS." + cl.getTable_name(),
																con.getMeta_column(),
																con.getMeta_id(),
																cl.getCode_column(), 
																ti.getPrimary_key(),
																min, max);
		
		if (returnList == null) { return new ArrayList<RefPubObject>(); }
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(concept);
		
		int counter = 0;
		for (RefPubObject obj : returnList) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI(count, page));
			returnList.set(counter, obj);
			counter++;
		}
		
		return returnList;
	}


	private RefPubObject getCodeSystemByConcept(String concept) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		RefPubObject returnObj = new RefPubObject();
		
		List<MDCodelist> codelists = ps.getCodeList_listByConcept(concept);
		
		List<CodeListDAO> l = new ArrayList<CodeListDAO>();
		
		for (MDCodelist cl : codelists) {
			CodeListDAO codeListObj = new CodeListDAO();
			codeListObj.setName(cl.getCode_name());
			codeListObj.setValue(cl.getRest_concept());
			l.add(codeListObj);
		}
		returnObj.setCodeList(l);
		returnObj.setCurrentURI(this.BuildURI(null, null));
		return returnObj;
	}


	
	private List<GenericType> attributeListByConceptCodelist(String concept, String codelist) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		MDCodelist cl = ps.getCodeList(concept, codelist);
		List<GenericType> list = ps.getTableColumns(cl.getTable_name());
		
		List<GenericType> retList = new ArrayList<GenericType>(); 
		for (GenericType t : list) {
			if (Arrays.asList(Constants.valid_attributes).contains(t.getValue().toLowerCase())) {
				t.setValue(t.getValue().toLowerCase());
				retList.add(t);
			}
		}
		
		return retList;
	}
	
	private URI BuildURI(String count, String page) {
		URI uri = new URI();
		
		if (URI == null) {
			return null;
		}
		uri.setFullURI(URI.getRequestUri().toString());
		uri.setHost(URI.getRequestUri().getHost());
		uri.setPath(URI.getBaseUri().getPath());
		uri.setPort(Integer.toString(URI.getRequestUri().getPort()));
		if (count != null && page != null) {
			if ("all".equalsIgnoreCase(count)) {
				uri.setAll(true);
				uri.setCount(999999999);
				uri.setPage(1);
			} else {
				uri.setCount(Integer.parseInt(count));
				uri.setPage(Integer.parseInt(page));
			}
		}
		return uri;
	}
	
	/*
	 * Calculates the pagination min/max offset for the query
	 * @param minMax is an identifier with value "min" or "max"
	 * @param count is the count of returned records
	 * @param page is the page you look for
	 * @return the min value if the param minMax is "min" / the max value if the param minMax is "max"
	 * @return null in the case minMax is not set to "min" || "max"
	 */
	private String calculateQueryPagination(String minMax, String count, String page) {
		boolean min = false;
		boolean max = false;
		if ("all".equalsIgnoreCase(count)) {
			count = "9999999";
			page = "1";
		}
		if ("min".equalsIgnoreCase(minMax)) {
			min = true;
		} else if ("max".equalsIgnoreCase(minMax)) {
			max = true;
		} else {
			return null;
		}
		
		if (count == null || !NumberUtils.isNumber(count)) {
			count = "100";
		} if (page == null || !NumberUtils.isNumber(page)) {
			page = "1";
		}
		
		String value = "";
		if (min) {
			if (Integer.parseInt(page) == 1) {
				return "0";
			}
			value = Integer.toString((Integer.parseInt(count) * (Integer.parseInt(page) - 1)) + 1);
		} else if (max) {
			value = Integer.toString(Integer.parseInt(count) * (Integer.parseInt(page)));
		} else {
			return null;
		}
		
		return value;
	}

	
	

		
}
