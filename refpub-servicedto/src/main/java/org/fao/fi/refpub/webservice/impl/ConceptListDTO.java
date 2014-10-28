package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.test.mock.ResourceUrlMock;

public class ConceptListDTO {
	
	public static ConceptList create(List<RefPubConcept> list) {
		
		ConceptList l = new ConceptList();
		l.setResourceUrl(ResourceUrlMock.create("concept"));
		l.setCountRecords(list.size());
		for (RefPubConcept m : list) {
			l.getConcepts().add(ConceptTypeDTO.create(m));
		}
		return l;
	}
	
	public static ConceptList createObj(List<RefPubObject> list) {
		
		ConceptList l = new ConceptList();
		l.setCountRecords(list.size());
		l.setResourceUrl(ResourceUrlMock.create("concept"));
		for (RefPubObject m : list) {
			l.getConcepts().add(ConceptTypeDTO.create(m));
		}
		return l;
	}
}
