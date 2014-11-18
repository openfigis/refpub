package org.fao.fi.refpub.webservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.AttrList;
import org.fao.fi.refpub.webservice.SimpleText;

public class AttrListDTO {
	public static AttrList create (RefPubObject object) {
		
		AttrList attrList = new AttrList();
		
		/*if (object.getScientific_name() != null) {
			SimpleText st = new SimpleText();
			st.setName("scientific_name");
			st.setValue(object.getScientific_name());
			attrList.getValues().add(st);
		}*/
		
		List<HashMap<String, String>> attributes = object.getATTRIBUTES();
		for (HashMap<String, String> attribute : attributes) {
			for (Entry<String, String> e : attribute.entrySet()) {
				SimpleText st = new SimpleText();
				st.setName(e.getKey());
				st.setValue(e.getValue());
				attrList.getValues().add(st);
			}
		}
		
		
		return attrList;
	}
}