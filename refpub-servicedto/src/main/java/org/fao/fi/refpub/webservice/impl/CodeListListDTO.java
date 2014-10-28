package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.Concept;

public class CodeListListDTO {
	
	public static Concept create(RefPubObject object) {
		return ConceptTypeDTO.create(object, true);
	}

}
