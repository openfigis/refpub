package org.fao.fi.refpub.webservice.beans;

import org.fao.fi.refpub.webservice.ConceptDTO;
import org.fao.fi.refpub.webservice.ConceptListDTO;

/*
 * This is the interface of the RefPub REST Interface
 */


public interface RefPubInterface {
	/*
	 * getConcepts()
	 * Returns the list of all the concepts
	 * @return List<RefPubObject> List of any concept found in the DB
	 */
	//List<RefPubConcept> getConcepts();
	ConceptListDTO getAllConcept();
	
	/*
	 * getConcept()
	 * Returns a single concept
	 * @param concept : The name of the concept
	 * @return RefPubConcept
	 */
	//RefPubConcept getConcept(String concept);
	ConceptDTO getConcept(String concept);
	
	/*
	 * getObjects()
	 * Returns a list of objects belonging to some concept
	 * @param concept : The name of the concept
	 * @return List<RefPubObject>
	 */
	//List<RefPubObject> getObjects(String concept);
	ConceptListDTO getAllObjectByConcept(String concept);
	
	/*
	 * getObject()
	 * Returns a single object by its concept, codelist and codelist's code
	 * @param concept : The name of the concept
	 * @param codelist : The name of the codelist
	 * @param code : The codelist's code
	 * @return RefPubObject
	 */
	//RefPubObject getObject(String concept, String codelist, String code);
	ConceptDTO getObject(String concept, String codesystem, String code);
	
	/*
	 * getCodeLists
	 * Return a list of codelist
	 * @return RefPubObject with the List<CodeList> embedded
	 */
	//RefPubObject getCodeLists();
	ConceptDTO getAllCodeSystem();
	
	/*
	 * getListByCodeList()
	 * Returns a list of object by its concept and codelist
	 * @param concept : The name of the concept
	 * @param codelist : The name of the codelist
	 * @return List<RefPubObject>
	 */
	//List<RefPubObject> getListByCodeList(String concept, String codelist);
	ConceptListDTO getObjectByCodeSystem(String concept, String codesystem);
}
