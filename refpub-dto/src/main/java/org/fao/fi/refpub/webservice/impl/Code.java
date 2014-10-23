package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ConceptDTO;

public class Code {
	
	public static ConceptDTO create (RefPubObject object) {
		return ConceptType.create(object);
	}
}
