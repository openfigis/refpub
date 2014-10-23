package org.fao.fi.refpub.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;

public abstract interface PersistenceServiceInterface {
	TableInfo getTableInfo(@Param("table") String table);
	
	List<RefPubObject> getCategories();
	List<RefPubObject> getObjects(@Param("meta") String id, @Param("table") String table, @Param("main_id") String pk_column);
	List<MDConcept> getConcepts();
	MDConcept getConcept(String concept);
	List<MDCodelist> getCodelistForConcept(String concept);
	MDCodelist getCodeList(@Param("concept") String concept, @Param("codelistname") String codelistname);
	ClassInitXML getRefTable(int id);
	RefPubObject getObject(@Param("table") String table, @Param("column") String column, @Param("codelist") String codelistvalue, @Param("main_id") String pk_column);
	
	List<RefPubObject> getParentHierarchy(@Param("table") String table,
									@Param("group_table") String group_table,
									@Param("group_column") String group_column,
									@Param("id") String id,
									@Param("group_column_key") String group_column_key,
									@Param("primary_key") String primary_key);
}