package org.fao.fi.refpub.webservice.beans;

import javax.ws.rs.core.UriInfo;

import org.fao.fi.refpub.webservice.Attributes;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;

/*
 * This is the interface of the RefPub REST Interface
 */


public interface RefPubInterface {
	
	void setUrl(UriInfo uri);
	
	/*
	 * getAllConcept()
	 * Returns the list of all the concepts
	 * @return ConceptList
	 */
	//List<RefPubConcept> getConcepts();
	ConceptList getAllConcept(String count, String page);
	
	/*
	 * getConcept()
	 * Returns a single concept
	 * @param concept : The name of the concept
	 * @return Concept
	 */
	//RefPubConcept getConcept(String concept);
	Concept getConcept(String concept, String count, String page);
	
	/*
	 * getAllObjectByConcept()
	 * Returns a list of objects belonging to some concept
	 * @param concept : The name of the concept
	 * @return ConceptList
	 */
	//List<RefPubObject> getObjects(String concept);
	ConceptList getAllObjectByConcept(String concept, String count, String page);
	
	/*
	 * getObject()
	 * Returns a single object by its concept, codelist and codelist's code
	 * @param concept : The name of the concept
	 * @param codesystem : The name of the codelist
	 * @param code : The codelist's code
	 * @return Concept
	 */
	Concept getObject(String concept, String codesystem, String code);
	Concept getObject(String concept, String code);
	
	/*
	 * getAttributeForObject()
	 * Returns a single object's attribute by its concept, codelist and codelist's code
	 * @param concept : The name of the concept
	 * @param codesystem : The name of the codelist
	 * @param code : The codelist's code
	 * @param attribute : The object's attribute
	 * @return Concept
	 */
	Concept getAttributeForObject(String concept, String codesystem, String code, String attribute);
	
	/*
	 * getAllCodeSystem
	 * Return a list of codelist
	 * @return Concept
	 */
	Concept getAllCodeSystem(String count, String page);
	
	/*
	 * getObjectByCodeSystem()
	 * Returns a list of object by its concept and codelist
	 * @param concept : The name of the concept
	 * @param codelist : The name of the codelist
	 * @return ConceptList
	 */
	ConceptList getObjectByCodeSystem(String concept, String codesystem, String count, String page);
	
	/*
	 * getAllCodeSystemByConcept()
	 * Returns a list of codesystems by a selected concept
	 * @param concept
	 * @return Concept
	 */
	Concept getAllCodeSystemByConcept(String concept);
	
	Attributes getAllAttributesForConceptAndCodesystem(String concept, String codesystem);
}
