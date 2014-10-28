package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.CodeList;
import org.fao.fi.refpub.webservice.CodeListListType;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class CodeListTypeDTO {
	
	public static CodeListListType create(Map<String, String> codelists) {
		CodeListListType n = new CodeListListType();
		for (Map.Entry<String, String> entry : codelists.entrySet())
		{
			CodeList cl = new CodeList();
			cl.setName(entry.getValue());
		    n.getNames().add(cl);
		}
		return n;
	}
	
	public static CodeListListType create(Map<String, String> codelists, String concept) {
		CodeListListType n = new CodeListListType();
		for (Map.Entry<String, String> entry : codelists.entrySet())
		{
			CodeList cl = new CodeList();
			cl.setName(entry.getValue());
			
			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			urlChunks.add(new ResourceKeyValue("concept", concept));
			urlChunks.add(new ResourceKeyValue("codesystem", entry.getValue()));
			cl.setUrl(ResourceUrlDTO.create(urlChunks));
		    n.getNames().add(cl);
		}
		return n;
	}
	
	public static CodeListListType createList(RefPubObject obj, boolean forCodeList) {
		CodeListListType n = new CodeListListType();
		for (CodeListDAO cl : obj.getCodeList()) {
			CodeList cldto = new CodeList();
			cldto.setName(cl.getName());
			cldto.setValue(cl.getValue());

			List<ResourceKeyValue> urlChunks = new ArrayList<ResourceKeyValue>();
			if (forCodeList) { //We are listening JUST the codelist. so the referrer URL must be different from an object detail listening
				urlChunks.add(new ResourceKeyValue("concept", cl.getValue()));
				urlChunks.add(new ResourceKeyValue("codesystem", cl.getName()));
			} else { //object details listening. URL generator to list the object link by its codelists
				urlChunks.add(new ResourceKeyValue("concept", obj.getConcept()));
				urlChunks.add(new ResourceKeyValue("codesystem", cldto.getName()));
				urlChunks.add(new ResourceKeyValue("code", cldto.getValue()));
			}
			
			cldto.setUrl(ResourceUrlDTO.create(urlChunks));
			n.getNames().add(cldto);
		}
		return n;
	}
}
