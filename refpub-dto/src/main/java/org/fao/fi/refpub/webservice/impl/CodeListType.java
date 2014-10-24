package org.fao.fi.refpub.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fi.refpub.dao.objects.CodeList;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.CodeListDTO;
import org.fao.fi.refpub.webservice.CodeListListTypeDTO;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class CodeListType {
	public static CodeListListTypeDTO create(Map<String, String> codelists) {
		CodeListListTypeDTO n = new CodeListListTypeDTO();
		for (Map.Entry<String, String> entry : codelists.entrySet())
		{
			CodeListDTO cl = new CodeListDTO();
			cl.setName(entry.getValue());
		    n.getNames().add(cl);
		}
		return n;
	}
	
	public static CodeListListTypeDTO createList(RefPubObject obj, boolean forCodeList) {
		CodeListListTypeDTO n = new CodeListListTypeDTO();
		for (CodeList cl : obj.getCodeList()) {
			CodeListDTO cldto = new CodeListDTO();
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
			
			cldto.setUrl(ResourceUrl.create(urlChunks));
			n.getNames().add(cldto);
		}
		return n;
	}
}
