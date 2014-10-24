package org.fao.fi.refpub.webservice.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.fao.fi.refpub.dao.objects.CodeList;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.utils.Utils;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;
import org.fao.fi.refpub.persistence.PersistenceServiceInterface;
import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.ConceptListDTO;
import org.fao.fi.refpub.webservice.impl.Code;
import org.fao.fi.refpub.webservice.impl.CodeListList;
import org.fao.fi.refpub.webservice.impl.ConceptList;

/*
 * Implementation of the CDI bean that implements the RefPub Restful API functionalities
 */

@RequestScoped
@Named("refpub")
public class RefPubImplementation implements RefPubInterface {
	@Produces
	
	@Override
	public ConceptListDTO getAllConcept() {
		return ConceptList.create(this.getAllConcepts());
	}


	@Override
	public ConceptDTO getConcept(String concept) {
		return null;
	}


	@Override
	public ConceptListDTO getAllObjectByConcept(String concept) {
		return ConceptList.createObj(this.getAllObjectsForConcept(concept));
	}


	@Override
	public ConceptDTO getObject(String concept, String codesystem, String code) {
		return Code.create(this.getSingleObject(concept, codesystem, code));
	}


	@Override
	public ConceptDTO getAllCodeSystem() {
		return CodeListList.create(this.getAllCodeList());
	}


	@Override
	public ConceptListDTO getObjectByCodeSystem(String concept, String codesystem) {
		return ConceptList.createObj(this.getObjectsByCodeList(concept, codesystem));
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
			List<CodeList> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
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
		
		List<CodeList> codemap = Utils.retrieveCodeListForObject(ps.getCodelistForConcept(concept), obj);
		obj.setConcept(concept);
		obj.setCodeList(codemap);
		
		
		obj.setParents(ps.getParentHierarchy("FIGIS." + mdconcept.getTable_name(),
											 "FIGIS." +  mdconcept.getTable_group(), 
											 mdconcept.getTable_group_member(), 
											 obj.getPrimary_key_id(),
											 mdconcept.getTable_group_column(),
											 tbl.getPrimary_key()));

		return obj;
	}
	
	private RefPubObject getAllCodeList() {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation(); //Set up the persistence layer
		RefPubObject returnObj = new RefPubObject();
		
		List<MDCodelist> codelists = ps.getCodeList_list();
		
		List<CodeList> l = new ArrayList<CodeList>();
		
		for (MDCodelist cl : codelists) {
			CodeList codeListObj = new CodeList();
			codeListObj.setName(cl.getCode_name());
			codeListObj.setValue(cl.getRest_concept());
			l.add(codeListObj);
		}
		returnObj.setCodeList(l);
		
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
			List<CodeList> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			returnList.set(counter, obj);
			counter++;
		}
		
		return returnList;
	}


	


	
}
