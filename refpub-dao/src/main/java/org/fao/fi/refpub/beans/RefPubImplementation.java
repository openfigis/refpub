package org.fao.fi.refpub.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.fao.fi.refpub.dao.objects.CodeList;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.utils.Utils;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;
import org.fao.fi.refpub.persistence.PersistenceServiceInterface;

@RequestScoped
public class RefPubImplementation implements RefPubInterface {

	@Produces
	public List<RefPubConcept> getConcepts() {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
		
		List<RefPubConcept> concepts = new ArrayList<RefPubConcept>();
		
		List<MDConcept> listOfConcepts = ps.getConcepts();
		for (MDConcept c : listOfConcepts) {
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
	
	@Override
	public RefPubConcept getConcept(String Concept) {
		RefPubConcept concept = new RefPubConcept();
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
		
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

	public List<RefPubObject> getObjects(String concept) {
		RefPubConcept cp = this.getConcept(concept); 
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
		
		MDConcept mdconcept = ps.getConcept(concept);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		List<RefPubObject> objs = ps.getObjects(cp.getMeta(), "FIGIS." + cp.getTable_name(), tbl.getPrimary_key());
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

	

	@Override
	public RefPubObject getObject(String concept, String codelist, String code) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
		MDConcept mdconcept = ps.getConcept(concept);
		MDCodelist mdcodelist =  ps.getCodeList(concept, codelist);
		TableInfo tbl = ps.getTableInfo(mdconcept.getTable_name());
		
		RefPubObject obj = ps.getObject("FIGIS." + mdconcept.getTable_name(), mdcodelist.getCode_column(), code, tbl.getPrimary_key());
		
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

	@Override
	public RefPubObject getCodeLists() {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
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

	@Override
	public List<RefPubObject> getListByCodeList(String concept, String codelist) {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
	
		MDCodelist cl = ps.getCodeList(concept, codelist);
		TableInfo ti = ps.getTableInfo(cl.getTable_name());
		List<RefPubObject> returnList = ps.getObjectsByCodeList("FIGIS." + cl.getTable_name(), 
																cl.getCode_column(), 
																ti.getPrimary_key());
		
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
