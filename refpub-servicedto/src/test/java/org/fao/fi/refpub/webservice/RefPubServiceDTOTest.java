package org.fao.fi.refpub.webservice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.fao.fi.refpub.webservice.beans.RefPubImplementation;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;

public class RefPubServiceDTOTest extends TestCase{
	private static RefPubInterface rp;
	
	private static String CONCEPT_NAME = "Species";
	private static String CONCEPT_ID_COL = "ID";
	private static String CONCEPT_ID_VAL = "1412";
	private static String CONCEPT_ATTRIBUTE = "FULL_NAME_E";
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RefPubServiceDTOTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	rp = new RefPubImplementation();
    	rp.setPropertiesFile("/eork/FIGIS/refpub-data/conf/refpub.properties");
        return new TestSuite( RefPubServiceDTOTest.class );
    }
    
    public void testGetAllConcept() {
    	ConceptList cl = rp.getAllConcept("0", "10");
    	assertNotNull(cl);
    }
    
    /*
     * This implementation is not implemented yet
     */
    /*public void testGetConcept() {
    	Concept concept = rp.getConcept(CONCEPT_NAME, "20", "1");
    	assertNotNull(concept);
    }*/
    
    public void testGetAllObjectByConcept() {
    	ConceptList cl = rp.getAllObjectByConcept("Species", "20", "1");
    	assertNotNull(cl);
    }
    
    public void testGetObject() {
    	Concept con1 = rp.getObject(CONCEPT_NAME, CONCEPT_ID_VAL);
    	assertNotNull(con1);
    	Concept con2 = rp.getObject(CONCEPT_NAME, CONCEPT_ID_COL, CONCEPT_ID_VAL);
    	assertNotNull(con2);
    }
    
    public void testGetAttributeForObject() {
    	Concept con = rp.getAttributeForObject(CONCEPT_NAME, CONCEPT_ID_COL, CONCEPT_ID_VAL, CONCEPT_ATTRIBUTE);
    	assertNotNull(con);
    }
    
    public void testGetAllCodeSystem() {
    	Concept con = rp.getAllCodeSystem("100", "1");
    	assertNotNull(con);
    }

    public void testGetObjectByCodeSystem() {
    	ConceptList list = rp.getObjectByCodeSystem(CONCEPT_NAME, CONCEPT_ID_COL, CONCEPT_ID_VAL, "1");
    	assertNotNull(list);
    }

    public void testGetAllCodeSystemByConcept() {
    	Concept con = rp.getAllCodeSystemByConcept(CONCEPT_NAME);
    	assertNotNull(con);
    }
    
    public void testGetAllAttributesForConceptAndCodesystem () {
    	AttributeList attr = rp.getAllAttributesForConceptAndCodesystem(CONCEPT_NAME, CONCEPT_ID_COL);
    	assertNotNull(attr);
    }
    
    public void testError() {
    	SystemError error = rp.error(new Exception());
    	assertNotNull(error);
    }

}