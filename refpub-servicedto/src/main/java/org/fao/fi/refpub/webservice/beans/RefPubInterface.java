package org.fao.fi.refpub.webservice.beans;

import javax.ws.rs.core.UriInfo;

import org.fao.fi.refpub.webservice.AttributeList;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.SystemError;

/**
 * @author Enrico Anello
 * This is the interface of the RefPub REST Interface
 *
 */
public interface RefPubInterface {
	
	/**
	 * Sets the URI of the RestAPI interface, if any
	 * @param uri
	 */
	void setUrl(UriInfo uri);
	void setPropertiesFile(String propertiesFile);
	
	/**
	 * Gets all the main concepts
	 * @param count
	 * @param page
	 * @return
	 */
	ConceptList getAllConcept(String count, String page);
	
	
	/**
	 * Not Used Method
	 * @param concept
	 * @param count
	 * @param page
	 * @return
	 */
	Concept getConcept(String concept, String count, String page);
	
	
	/**
	 * Gets the list of objects for a concept
	 * @param concept
	 * @param count
	 * @param page
	 * @return
	 */
	ConceptList getAllObjectByConcept(String concept, String count, String page);
	

	/**
	 * Gets a single object by Concept, Codesystem and Code
	 * @param concept
	 * @param codesystem
	 * @param code
	 * @return
	 */
	Concept getObject(String concept, String codesystem, String code);
	/**
	 * Gets a single object by Concept and Code
	 * @param concept
	 * @param code
	 * @return
	 */
	Concept getObject(String concept, String code);
	
	
	/**
	 * Gets all attributes for an object
	 * @param concept
	 * @param codesystem
	 * @param code
	 * @param attribute
	 * @return
	 */
	Concept getAttributeForObject(String concept, String codesystem, String code, String attribute);
	
	
	/**
	 * Gets all Codesystems for all the concepts
	 * @param count
	 * @param page
	 * @return
	 */
	Concept getAllCodeSystem(String count, String page);
	
	
	/**
	 * Gets a single Codesystem for a concept
	 * @param concept
	 * @param codesystem
	 * @param count
	 * @param page
	 * @return
	 */
	ConceptList getObjectByCodeSystem(String concept, String codesystem, String count, String page);
	
	
	/**
	 * Gets all Codesystems for a single concept
	 * @param concept
	 * @return
	 */
	Concept getAllCodeSystemByConcept(String concept);
	
	
	/**
	 * Gets the groups for a concept
	 * @param concept
	 * @return
	 */
	ConceptList getGroups(String concept);
	
	
	/**
	 * Gets the main groupings for a concept and a main group
	 * @param concept
	 * @param filter
	 * @return
	 */
	ConceptList getGroupMain(String concept, String filter);
	
	
	/**
	 * Gets the list of objects for a group
	 * @param Concept
	 * @param filter
	 * @param group
	 * @return
	 */
	ConceptList getGroup(String Concept, String filter, String group);
	
	
	/**
	 * Not used method
	 * @param Concept
	 * @param filter
	 * @param group
	 * @param subGroup
	 * @return
	 */
	ConceptList getSubGroups(String Concept, String filter, String group, String subGroup);
	
	/**
	 * Gets all the attributes for a Concept
	 * @param concept
	 * @return
	 */
	AttributeList getAllAttributesForConcept(String concept);
	
	/**
	 * Gets all the attributes for a Concept and a Codesystem
	 * @param concept
	 * @param codesystem
	 * @return
	 */
	AttributeList getAllAttributesForConceptAndCodesystem(String concept, String codesystem);
	
	/**
	 * Error handler
	 * @param e
	 * @return
	 */
	SystemError error(Exception e);
}
