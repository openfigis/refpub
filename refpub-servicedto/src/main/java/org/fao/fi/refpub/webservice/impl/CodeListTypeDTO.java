package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.AtomLink;
import org.fao.fi.refpub.webservice.CodeList;
import org.fao.fi.refpub.webservice.CodeListListType;
import org.fao.fi.refpub.webservice.LinkRel;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class CodeListTypeDTO {
	
	public static CodeListListType create(Map<String, String> codelists) {
		CodeListListType n = new CodeListListType();
		for (Map.Entry<String, String> entry : codelists.entrySet())
		{
			CodeList cl = new CodeList();
			cl.setName(entry.getValue());
			n.getCodes().add(cl);
		}
		return n;
	}
	
	public static CodeListListType create(RefPubConcept concept) {
		Map<String, String> codelists = concept.getCodelists();
		CodeListListType n = new CodeListListType();
		for (Map.Entry<String, String> entry : codelists.entrySet())
		{
			CodeList cl = new CodeList();
			
			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			urlChunks.add(new ResourceKeyValue("concept", concept.getName()));
			urlChunks.add(new ResourceKeyValue("codesystem", entry.getValue()));

			cl.getLinks().add(LinkRelDTO.create(concept, urlChunks, "cl_" + entry.getValue().toLowerCase()));
			cl.setName(entry.getValue());
			
			urlChunks.add(new ResourceKeyValue("attribute", ""));
			AtomLink attributesLink = new AtomLink();
			attributesLink.getLinks().add(LinkRelDTO.create(concept, urlChunks, "attr_" + entry.getValue().toLowerCase()));
			cl.setAttributes(attributesLink);
			n.getCodes().add(cl);
		}
		return n;
	}
	
	public static CodeListListType createList(RefPubObject obj, boolean forCodeList) {
		CodeListListType n = new CodeListListType();
		for (CodeListDAO cl : obj.getCodeList()) {
			CodeList cldto = new CodeList();

			cldto.setName(cl.getName());

			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			if (forCodeList) { //We are listening JUST the codelist. so the referrer URL must be different from an object detail listening
				urlChunks.add(new ResourceKeyValue("concept", cl.getValue()));
				urlChunks.add(new ResourceKeyValue("codesystem", cl.getName()));
			} else { //object details listening. URL generator to list the object link by its codelists
				if (obj.getConcept() != null && cldto.getName() != null && cl.getValue() != null) {
					urlChunks.add(new ResourceKeyValue("concept", obj.getConcept()));
					urlChunks.add(new ResourceKeyValue("codesystem", cldto.getName()));
					urlChunks.add(new ResourceKeyValue("code", cl.getValue()));
					cldto.getLinks().add(LinkRelDTO.create(obj, urlChunks, "cl_" + cldto.getName().toLowerCase()));
				}
			}
			
			n.getCodes().add(cldto);
		
		}
		return n;
	}
}
