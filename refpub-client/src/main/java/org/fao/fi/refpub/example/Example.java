package org.fao.fi.refpub.example;

import org.fao.fi.refpub.client.RefPubClient;
import org.fao.fi.refpub.webservice.AttributeList;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;

public class Example {

	static String URL = "http://localhost:8080/refpub-web/rest/";
	
	public static void main(String[] args) {
		RefPubClient client;
		
		int good = 0;
		int fail = 0;
		
		try {
			client = new RefPubClient(URL);
			ConceptList out1 = client.getAllConcept(null, null);
			good++;
			ConceptList out2 = client.getAllObjectByConcept("Country", null, null);
			good++;
			ConceptList out3 = client.getAllObjectByConcept("Country", "100", "2");
			good++;
			Concept out4 = client.getObject("Country", "ID", "97");
			good++;
			Concept out5 = client.getAttributeForObject("Country", "ID", "97", "UN-ISO2");
			good++;
			Concept out6 = client.getAllCodeSystem(null, null);
			good++;
			ConceptList out7 = client.getObjectByCodeSystem("Country", "UN-ISO2", null, null);
			good++;
			Concept out8 = client.getAllCodeSystemByConcept("Country");
			good++;
			ConceptList out9 = client.getGroups("Country");
			good++;
			ConceptList out10 = client.getGroupMain("Country", "fishery_stat_country");
			good++;
			ConceptList out11 = client.getGroup("Country", "fishery_stat_country", "10004");
			good++;
			AttributeList out12 = client.getAllAttributesForConcept("Country");
			good++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail++;
			good--;
		}
		
		System.out.println("Test Passed: " + Integer.toString(good));
		System.out.println("Test Failed: " + Integer.toString(fail));

	}

}

