package org.refpub.client;

import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.beans.RefPubImplementation;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;

public class RefPubClient {
	
	static RefPubInterface refPub = null;
	
	static {
		refPub = new RefPubImplementation();
	}
	
	public ConceptList getMainListOfConcepts() {
		return refPub.getAllConcept(null, null);
	}
	
	public ConceptList getListForConcept(String concept) {
		return this.getListForConcept(concept, null, null);
	}
	
	public ConceptList getListForConcept(String concept, String count, String page) {
		return refPub.getAllObjectByConcept(concept, count, page);
	}
	
	public Concept getObject(String concept, String codesystem, String code) {
		return refPub.getObject(concept, codesystem, code);
	}
	
	public Concept getCodeSystemsForConcept(String concept) {
		return refPub.getAllCodeSystemByConcept(concept);
	}
	
	public ConceptList getMetaGroupsForConcept(String concept) {
		return refPub.getGroups(concept);
	}
	
	public ConceptList getSubGroupsForConceptAndMeta(String concept, String metaGroup) {
		return refPub.getGroupMain(concept, metaGroup);
	}
	
	public ConceptList getGroup(String concept, String metaGroup, String group) {
		return refPub.getGroup(concept, metaGroup, group);
	}
}
