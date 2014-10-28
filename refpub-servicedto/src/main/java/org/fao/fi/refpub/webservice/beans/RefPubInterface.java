package org.fao.fi.refpub.webservice.beans;

import org.fao.fi.refpub.webservice.AttributesDTO;
import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.ConceptListDTO;

/*
 * This is the interface of the RefPub REST Interface
 */


public interface RefPubInterface {
	/*
	 * getAllConcept()
	 * Returns the list of all the concepts
	 * @return ConceptListDTO
	 */
	//List<RefPubConcept> getConcepts();
	ConceptListDTO getAllConcept();
	
	/*
	 * getConcept()
	 * Returns a single concept
	 * @param concept : The name of the concept
	 * @return ConceptDTO
	 */
	//RefPubConcept getConcept(String concept);
	ConceptDTO getConcept(String concept);
	
	/*
	 * getAllObjectByConcept()
	 * Returns a list of objects belonging to some concept
	 * @param concept : The name of the concept
	 * @return ConceptListDTO
	 */
	//List<RefPubObject> getObjects(String concept);
	ConceptListDTO getAllObjectByConcept(String concept);
	
	/*
	 * getObject()
	 * Returns a single object by its concept, codelist and codelist's code
	 * @param concept : The name of the concept
	 * @param codesystem : The name of the codelist
	 * @param code : The codelist's code
	 * @return ConceptDTO
	 */
	ConceptDTO getObject(String concept, String codesystem, String code);
	
	/*
	 * getAllCodeSystem
	 * Return a list of codelist
	 * @return ConceptDTO
	 */
	ConceptDTO getAllCodeSystem();
	
	/*
	 * getObjectByCodeSystem()
	 * Returns a list of object by its concept and codelist
	 * @param concept : The name of the concept
	 * @param codelist : The name of the codelist
	 * @return ConceptListDTO
	 */
	ConceptListDTO getObjectByCodeSystem(String concept, String codesystem);
	
	/*
	 * getAllCodeSystemByConcept()
	 * Returns a list of codesystems by a selected concept
	 * @param concept
	 * @return ConceptDTO
	 */
	ConceptDTO getAllCodeSystemByConcept(String concept);
	
	AttributesDTO getAllAttributesForConceptAndCodesystem(String concept, String codesystem);
}
