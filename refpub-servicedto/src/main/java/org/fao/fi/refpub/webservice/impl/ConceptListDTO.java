package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class ConceptListDTO {
	
	public static ConceptList create(List<RefPubConcept> list) {
		
		ConceptList l = new ConceptList();
		if (list.size() < 1) { return l; }
		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", ""));
		l.getLinks().add(LinkRelDTO.create(list.get(0), urlChunks, "self"));
		if (list.get(0).getCurrentURI().getCount() <= list.size()) { //This might be a bug! If the last page total is equal to the count it would show next page link
			l.getLinks().add(LinkRelDTO.create(list.get(0), urlChunks, "next"));
		}
		if (list.get(0).getCurrentURI().getPage() > 1) {
			l.getLinks().add(LinkRelDTO.create(list.get(0), urlChunks, "prev"));
		}
		l.setCountRecord(list.size());
		l.setTotalRecord(list.size());
		for (RefPubConcept m : list) {
			l.getConcepts().add(ConceptTypeDTO.create(m));
		}
		return l;
	}
	
	public static ConceptList createObj(List<RefPubObject> list) {
		
		ConceptList l = new ConceptList();
		if (list.size() < 1) { return l; }
		List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
		urlChunks.add(new ResourceKeyValue("concept", ""));
		l.getLinks().add(LinkRelDTO.create(list.get(0), urlChunks, "self"));
		if (list.get(0).getCurrentURI().getCount() <= list.size()) { //This might be a bug! If the last page total is equal to the count it would show next page link
			l.getLinks().add(LinkRelDTO.create(list.get(0), urlChunks, "next"));
		}
		if (list.get(0).getCurrentURI().getPage() > 1) {
			l.getLinks().add(LinkRelDTO.create(list.get(0), urlChunks, "prev"));
		}
		l.setCountRecord(list.size());
		l.setTotalRecord(list.get(0).getTotal());
		for (RefPubObject m : list) {
			l.getConcepts().add(ConceptTypeDTO.create(m));
		}
		return l;
	}
	
}