package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptListDTO;
import org.fao.fi.refpub.webservice.test.mock.ResourceUrlMock;

public class ConceptList {
	
	public static ConceptListDTO create(List<RefPubConcept> list) {
		
		ConceptListDTO l = new ConceptListDTO();
		l.setResourceUrl(ResourceUrlMock.create("concepts"));
		for (RefPubConcept m : list) {
			l.getConcepts().add(ConceptType.create(m));
		}
		return l;
	}
	
	public static ConceptListDTO createObj(List<RefPubObject> list) {
		
		ConceptListDTO l = new ConceptListDTO();
		l.setResourceUrl(ResourceUrlMock.create("concepts"));
		for (RefPubObject m : list) {
			l.getConcepts().add(ConceptType.create(m));
		}
		return l;
	}
}
