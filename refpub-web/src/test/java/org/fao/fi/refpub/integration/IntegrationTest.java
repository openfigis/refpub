package org.fao.fi.refpub.integration;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.LinkRel;
import org.junit.Assert;

public class IntegrationTest extends TestCase{
	
	private static String BASE_URL = "http://localhost:58080/refpub-web";
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public IntegrationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( IntegrationTest.class );
    }
    
    
    
    public void testConnection() {
    	assertTrue(this.Connect());
    }
    
    public void testGetConcepts() {
    	Object obj = this.getConceptList(BASE_URL + "/rest/concept/xml");
    	if (obj == null) {
    		assertTrue(false);
    	}
    	ConceptList po = (ConceptList)obj;
    	Assert.assertNotNull(po);
    	if (po.getConcepts().size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
    }
    
    public void testGetCountries() {
    	Object obj = this.getConceptList(BASE_URL + "/rest/concept/Country/xml");
    	if (obj == null) {
    		assertTrue(false);
    	}
    	ConceptList po = (ConceptList)obj;
    	Assert.assertNotNull(po);
    	if (po.getConcepts().size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
    }
    
    public void testGetSpecies() {
    	Object obj = this.getConceptList(BASE_URL + "/rest/concept/Species/xml");
    	if (obj == null) {
    		assertTrue(false);
    	}
    	ConceptList po = (ConceptList)obj;
    	Assert.assertNotNull(po);
    	if (po.getConcepts().size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
    }
    
    public void testGetSpeciesIterations() {
    	Object obj = this.getConceptList(BASE_URL + "/rest/concept/Species/xml");
    	if (obj == null) {
    		assertTrue(false);
    	}
    	ConceptList po = (ConceptList)obj;
    	Assert.assertNotNull(po);
    	if (po.getConcepts().size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
    	int numOfIterations = this.iterateSpecies(po, 0);
    	if (numOfIterations > 20) {
    		assertTrue(true);
    	} else {
    		assertTrue(false);
    	}
    }
    
    public void testCountryGroups() {
    	int iterations = this.countryGroupIterations(BASE_URL + "/rest/concept/Country/group/xml", 0);
    	if (iterations < 10) {
    		assertTrue(false);
    	} else {
    		assertTrue(true);
    	}
    }
    
    private int countryGroupIterations(String base, int iterations) {
    	iterations = iterations + 1;
    	Object object = this.getConceptList(base);
    	ConceptList conceptList = null;
    	if (object instanceof ConceptList) {
    		conceptList = (ConceptList) object;
    	} else if (object instanceof Concept) {
    		Concept concept = (Concept) object;
    		if (concept.getAttr().getValues().size() > 0) {
    			return iterations;
    		} else {
    			return 0;
    		}
    	}
    	if (conceptList.getConcepts().size() < 1) {
    		return 0;
    	}
    	for (Concept concept : conceptList.getConcepts()) {
    		if (concept.getLinks().size() > 0) {
	    		LinkRel linkToSubObject = concept.getLinks().get(0);
	    		iterations = this.countryGroupIterations(linkToSubObject.getHref(), iterations);
    		}
    	}
    	return iterations;
    }
    
    
    private int iterateSpecies(ConceptList base, int iterations) {
    	iterations = iterations + 1;
    	for (LinkRel link : base.getLinks()) {
    		if (link.getRel().toLowerCase().trim().equals("next")) {
    			ConceptList nextConcepts = (ConceptList) this.getConceptList(link.getHref());
    			if (nextConcepts == null) {
    				return 0;
    			} else {
    				iterations = this.iterateSpecies(nextConcepts, iterations);
    			}
    		}
    	}
    	return iterations;
    }
    
    private Object getConceptList(String URL) {
    	JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(ConceptList.class);
			Unmarshaller m = jc.createUnmarshaller();
			Object  po = (Object )m.unmarshal(new URL( URL ));
			return po;
		} catch (JAXBException e) {
			return null;
		} catch (MalformedURLException e) {
			return null;
		}
    }
    
    private boolean Connect() {
    	try {
			InputStream in = new URL( BASE_URL ).openStream();
		} catch (IOException e) {
			return false;
		}
    	return true;
    }

}
