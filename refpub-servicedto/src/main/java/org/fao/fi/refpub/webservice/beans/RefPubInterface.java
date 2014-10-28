package org.fao.fi.refpub.webservice.beans;

import org.fao.fi.refpub.webservice.Attributes;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;

/*
 * This is the interface of the RefPub REST Interface
 */


public interface RefPubInterface {
	/*
	 * getAllConcept()
	 * Returns the list of all the concepts
	 * @return ConceptList
	 */
	//List<RefPubConcept> getConcepts();
	ConceptList getAllConcept();
	
	/*
	 * getConcept()
	 * Returns a single concept
	 * @param concept : The name of the concept
	 * @return Concept
	 */
	//RefPubConcept getConcept(String concept);
	Concept getConcept(String concept);
	
	/*
	 * getAllObjectByConcept()
	 * Returns a list of objects belonging to some concept
	 * @param concept : The name of the concept
	 * @return ConceptList
	 */
	//List<RefPubObject> getObjects(String concept);
	ConceptList getAllObjectByConcept(String concept);
	
	/*
	 * getObject()
	 * Returns a single object by its concept, codelist and codelist's code
	 * @param concept : The name of the concept
	 * @param codesystem : The name of the codelist
	 * @param code : The codelist's code
	 * @return Concept
	 */
	Concept getObject(String concept, String codesystem, String code);
	
	/*
	 * getAllCodeSystem
	 * Return a list of codelist
	 * @return Concept
	 */
	Concept getAllCodeSystem();
	
	/*
	 * getObjectByCodeSystem()
	 * Returns a list of object by its concept and codelist
	 * @param concept : The name of the concept
	 * @param codelist : The name of the codelist
	 * @return ConceptList
	 */
	ConceptList getObjectByCodeSystem(String concept, String codesystem);
	
	/*
	 * getAllCodeSystemByConcept()
	 * Returns a list of codesystems by a selected concept
	 * @param concept
	 * @return Concept
	 */
	Concept getAllCodeSystemByConcept(String concept);
	
	Attributes getAllAttributesForConceptAndCodesystem(String concept, String codesystem);
}
