package org.fao.fi.refpub.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;

public abstract interface PersistenceServiceInterface {
	TableInfo getTableInfo(@Param("db_schema") String db_schema, @Param("table") String table);
	
	List<RefPubObject> getCategories(@Param("db_schema") String db_schema);
	List<RefPubObject> getObjects(@Param("db_schema") String db_schema, 
								  @Param("meta") String id,
								  @Param("meta_column") String id_column,
								  @Param("table") String table, 
								  @Param("main_id") String pk_column,
								  @Param("min") String min, 
								  @Param("max") String max);
	List<MDConcept> getConcepts(@Param("db_schema") String db_schema);
	MDConcept getConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);
	List<MDCodelist> getCodelistForConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);
	MDCodelist getDefaultCodelistFromConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);
	MDCodelist getCodeList(@Param("db_schema") String db_schema, @Param("concept") String concept, @Param("codelistname") String codelistname);
	ClassInitXML getRefTable(@Param("db_schema") String db_schema, @Param("id") int id);
	
	RefPubObject getObject(@Param("db_schema") String db_schema, 
						   @Param("table") String table, 
						   @Param("column") String column, 
						   @Param("codelist") String codelistvalue, 
						   @Param("main_id") String pk_column);
	RefPubObject getObjectById(@Param("db_schema") String db_schema, 
							   @Param("table") String table, 
							   @Param("main_id") String pk_column, 
							   @Param("id") String id);
	RefPubObject getAttributeForSingleObject(@Param("db_schema") String db_schema,
											 @Param("table") String table, 
			   								 @Param("column") String column, 
			   								 @Param("codelist") String codelistvalue, 
			   								 @Param("main_id") String pk_column,
			   								 @Param("attribute") String attribute);
	
	List<RefPubObject> getObjectsByCodeList(@Param("db_schema") String db_schema, 
											@Param("table") String table,
											@Param("meta_column") String meta_column,
											@Param("meta_id") String meta_id,
											@Param("column") String column, 
											@Param("main_id") String pk_column,
											@Param("min") String min, 
											@Param("max") String max);
	List<MDCodelist> getCodeList_list(@Param("db_schema") String db_schema);
	List<MDCodelist> getCodeList_listByConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);
	List<GenericType> getTableColumns(@Param("db_schema") String db_schema, @Param("table") String table);
	
	List<RefPubObject> getParentHierarchy(@Param("db_schema") String db_schema, 
									@Param("table") String table,
									@Param("group_table") String group_table,
									@Param("group_column") String group_column,
									@Param("meta_column") String meta_column,
									@Param("id") String id,
									@Param("group_column_key") String group_column_key,
									@Param("primary_key") String primary_key);
	
	List<RefPubObject> getChildrenHierarchy(@Param("db_schema") String db_schema, 
									@Param("table") String table,
									@Param("group_table") String group_table,
									@Param("group_column") String group_column,
									@Param("meta_column") String meta_column,
									@Param("id") String id,
									@Param("group_column_key") String group_column_key,
									@Param("primary_key") String primary_key);
	
}