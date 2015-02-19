package org.fao.fi.refpub.client;

import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;

public class App {

	static String URL = "http://localhost:8080/refpub-web/rest/";
	
	public static void main(String[] args) {
		RefPubClient client;
		try {
			client = new RefPubClient(URL);
			ConceptList out1 = client.getAllConcept(null, null);
			ConceptList out2 = client.getAllObjectByConcept("Country", null, null);
			ConceptList out3 = client.getAllObjectByConcept("Country", "100", "2");
			Concept out4 = client.getObject("Country", "ID", "97");
			
			System.out.println("Done!");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
