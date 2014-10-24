package org.fao.fi.refpub.beans;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;

public interface RefPubInterface {
	List<RefPubConcept> getConcepts();
	RefPubConcept getConcept(String concept);
	List<RefPubObject> getObjects(String concept);
	RefPubObject getObject(String concept, String codelist, String code);
	RefPubObject getCodeLists();
	List<RefPubObject> getListByCodeList(String concept, String codelist);
}
