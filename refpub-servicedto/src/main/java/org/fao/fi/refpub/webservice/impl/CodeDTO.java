package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.Concept;

public class CodeDTO {
	
	public static Concept create (RefPubObject object) {
		return ConceptTypeDTO.create(object);
	}
	
	public static Concept error (String message) {
		return ConceptTypeDTO.error(message);
	}
}
