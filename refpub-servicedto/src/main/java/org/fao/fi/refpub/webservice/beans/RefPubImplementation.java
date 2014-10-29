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
	
	@Override
	public void setUrl(UriInfo uri) {
		if (uri != null) {
			URI = uri;
		} else {
			URI = null;
		}
	}
	
	@Override
	public ConceptList getAllConcept() {
		return ConceptListDTO.create(this.getAllConcepts());
	}


	@Override
	public Concept getConcept(String concept) {
		return null;
	}


	@Override
	public ConceptList getAllObjectByConcept(String concept) {
		return ConceptListDTO.createObj(this.getAllObjectsForConcept(concept));
	}


	@Override
	public Concept getObject(String concept, String codesystem, String code) {
		return CodeDTO.create(this.getSingleObject(concept, codesystem, code));
	}


	@Override
	public Concept getAllCodeSystem() {
		return CodeListListDTO.create(this.getAllCodeList());
	}


	@Override
	public ConceptList getObjectByCodeSystem(String concept, String codesystem) {
		return ConceptListDTO.createObj(this.getObjectsByCodeList(concept, codesystem));
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
	

	
	private List<RefPubConcept> getAllConcepts() {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		
		List<RefPubConcept> concepts = new ArrayList<RefPubConcept>();
		
		List<MDConcept> listOfConcepts = ps.getConcepts(); //Retrieves the concepts from the persistence layer
		for (MDConcept c : listOfConcepts) { //for each concept try to set-up the codelist to be pushed into RefPubObjects
			RefPubConcept concept = new RefPubConcept();
			concept.setName(c.getRest_concept());
			concept.setTable_name(c.getTable_name());
			concept.setTable_grp_name(c.getTable_group());
			concept.setMeta(c.getFic_meta());
			
			List<MDCodelist> codelists = ps.getCodelistForConcept(concept.getName());
			
			Map<String,String> codemap = new HashMap<String, String>();
			for (MDCodelist code : codelists) {
				codemap.put(code.getCode_column(), code.getCode_name());
			}
			concept.setCodelists(codemap);
			
			// TODO Add lookup to MD-REFOBJECT to retrieve the concept translations
			concept.setCurrentURI(this.BuildURI());
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
		concept.setMeta(c.getFic_meta());
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(concept.getName());
		Map<String,String> codemap = new HashMap<String, String>();
		for (MDCodelist code : codelists) {
			codemap.put(code.getCode_column(), code.getCode_name());
		}
		concept.setCodelists(codemap);
		concept.setCurrentURI(this.BuildURI());
		return concept;
	}
	
	
	private List<RefPubObject> getAllObjectsForConcept(String concept) {
		RefPubConcept cp = this.getSingleConcept(concept); 
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		
		MDConcept mdconcept = ps.getConcept(concept);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		List<RefPubObject> objs = ps.getObjects(cp.getMeta(), "FIGIS." + cp.getTable_name(), tbl.getPrimary_key());
		if (objs == null) { return new ArrayList<RefPubObject>(); }
		List<MDCodelist> codelists = ps.getCodelistForConcept(concept);
		
		int counter = 0;
		for (RefPubObject obj : objs) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI());
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
		
		
		obj.setParents(ps.getParentHierarchy("FIGIS." + mdconcept.getTable_name(),
											 "FIGIS." +  mdconcept.getTable_group(), 
											 mdconcept.getTable_group_member(), 
											 obj.getPrimary_key_id(),
											 mdconcept.getTable_group_column(),
											 tbl.getPrimary_key()));
		
		obj.setCurrentURI(this.BuildURI());
		return obj;
	}
	
	private RefPubObject getAllCodeList() {
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
		returnObj.setCurrentURI(this.BuildURI());
		return returnObj;
	}
	
	private List<RefPubObject> getObjectsByCodeList(String concept, String codelist) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
	
		MDCodelist cl = ps.getCodeList(concept, codelist);
		TableInfo ti = ps.getTableInfo(cl.getTable_name());
		List<RefPubObject> returnList = ps.getObjectsByCodeList("FIGIS." + cl.getTable_name(), 
																cl.getCode_column(), 
																ti.getPrimary_key());
		
		if (returnList == null) { return new ArrayList<RefPubObject>(); }
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(concept);
		
		int counter = 0;
		for (RefPubObject obj : returnList) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI());
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
		returnObj.setCurrentURI(this.BuildURI());
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
	
	private URI BuildURI() {
		URI uri = new URI();
		
		if (URI == null) {
			return null;
		}
		uri.setFullURI(URI.getRequestUri().toString());
		uri.setHost(URI.getRequestUri().getHost());
		uri.setPath(URI.getBaseUri().getPath());
		uri.setPort(Integer.toString(URI.getRequestUri().getPort()));
		
		return uri;
	}

		
}
