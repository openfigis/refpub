package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.AttrList;
import org.fao.fi.refpub.webservice.SimpleText;

public class AttrListDTO {
	public static AttrList create (RefPubObject object) {
		
		AttrList attrList = new AttrList();
		
		if (object.getScientific_name() != null) {
			SimpleText st = new SimpleText();
			st.setName("scientific_name");
			st.setValue(object.getScientific_name());
			attrList.getValues().add(st);
		}
		
		
		return attrList;
	}
}
