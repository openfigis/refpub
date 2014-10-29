package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.LinkRel;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;
import org.fao.fi.refpub.webservice.test.mock.ResourceUrlMock;

public class ConceptListDTO {
	
	public static ConceptList create(List<RefPubConcept> list) {
		
		ConceptList l = new ConceptList();
		//l.setResourceUrl(ResourceUrlMock.create("concept"));
		/*LinkRel rel = new LinkRel();
		rel.setRel("self");
		rel.setValue(ResourceUrlMock.create("concept"));
		l.setLink(rel);*/
		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", ""));
		l.setLink(LinkRelDTO.create(list.get(0), urlChunks, "self"));
		l.setCountRecords(list.size());
		for (RefPubConcept m : list) {
			l.getConcepts().add(ConceptTypeDTO.create(m));
		}
		return l;
	}
	
	public static ConceptList createObj(List<RefPubObject> list) {
		
		ConceptList l = new ConceptList();
		/*LinkRel rel = new LinkRel();
		rel.setRel("self");
		rel.setValue(ResourceUrlMock.create("concept"));
		l.setLink(rel);*/
		//l.setResourceUrl(ResourceUrlMock.create("concept"));
		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", ""));
		l.setLink(LinkRelDTO.create(list.get(0), urlChunks, "self"));
		l.setCountRecords(list.size());
		for (RefPubObject m : list) {
			l.getConcepts().add(ConceptTypeDTO.create(m));
		}
		return l;
	}
}