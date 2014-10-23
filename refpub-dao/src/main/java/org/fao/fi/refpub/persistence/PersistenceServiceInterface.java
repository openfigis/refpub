package org.fao.fi.refpub.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;

public abstract interface PersistenceServiceInterface {
	List<RefPubObject> getCategories();
	List<RefPubObject> getObjects(@Param("meta") String id, @Param("table") String table);
	List<MDConcept> getConcepts();
	MDConcept getConcept(String concept);
	List<MDCodelist> getCodelistForConcept(String concept);
	MDCodelist getCodeList(@Param("concept") String concept, @Param("codelistname") String codelistname);
	ClassInitXML getRefTable(int id);
	RefPubObject getObject(@Param("table") String table, @Param("column") String column, @Param("codelist") String codelistvalue);
	
}
