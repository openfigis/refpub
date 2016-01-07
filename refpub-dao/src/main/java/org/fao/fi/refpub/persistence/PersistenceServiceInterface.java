package org.fao.fi.refpub.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.chunks.MDGrouping;
import org.fao.fi.refpub.dao.objects.chunks.MDGroupingDepth;
import org.fao.fi.refpub.dao.objects.chunks.TableReference;
import org.fao.fi.refpub.dao.objects.db.TableInfo;

public abstract interface PersistenceServiceInterface {
	TableInfo getTableInfo(@Param("db_schema") String db_schema, @Param("table") String table);

	/* List<RefPubObject> getCategories(@Param("db_schema") String db_schema); */

	HashMap<String, Object> getMDRefObject(@Param("db_schema") String db_schema, @Param("metaId") String metaId);

	ArrayList<HashMap<String, Object>> getObjects(@Param("db_schema") String db_schema,
			@Param("meta") List<String> meta, @Param("meta_column") String id_column, @Param("table") String table,
			@Param("main_id") String pk_column, @Param("min") String min, @Param("max") String max);

	ArrayList<HashMap<String, Object>> getObjectsFlat(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("primaryKey") String primaryKey,
			@Param("nameColumn") String nameColumn, @Param("min") String min, @Param("max") String max);

	List<MDConcept> getConcepts(@Param("db_schema") String db_schema);

	MDConcept getConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);

	List<MDCodelist> getCodelistForConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);

	MDCodelist getDefaultCodelistFromConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);

	MDCodelist getCodeList(@Param("db_schema") String db_schema, @Param("concept") String concept,
			@Param("codelistname") String codelistname);

	MDCodelist getCodeListForAttribute(@Param("db_schema") String db_schema, @Param("concept") String concept,
			@Param("attribute") String attribute);

	ClassInitXML getRefTable(@Param("db_schema") String db_schema, @Param("id") int id);

	ArrayList<HashMap<String, Object>> getObject(@Param("db_schema") String db_schema, @Param("table") String table,
			@Param("column") String column, @Param("codelist") String codelistvalue,
			@Param("main_id") String pk_column);

	ArrayList<HashMap<String, Object>> getObjectById(@Param("db_schema") String db_schema, @Param("table") String table,
			@Param("main_id") String pk_column, @Param("id") String id);

	ArrayList<HashMap<String, Object>> getObjectByMeta(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("main_id") String pk_column, @Param("meta_column") String meta_column,
			@Param("meta") String meta);

	ArrayList<HashMap<String, Object>> getObjectByMetaPaginate(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("main_id") String pk_column, @Param("meta_column") String meta_column,
			@Param("meta") String meta, @Param("start") String start, @Param("numRows") String numRows);

	int getObjectByMetaCount(@Param("db_schema") String db_schema, @Param("table") String table,
			@Param("main_id") String pk_column, @Param("meta_column") String meta_column, @Param("meta") String meta);

	ArrayList<HashMap<String, Object>> getAttributeForSingleObject(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("column_source") String column_source,
			@Param("column_target") String column_target, @Param("codelist") String codelistvalue,
			@Param("main_id") String pk_column, @Param("attribute") String attribute);

	ArrayList<HashMap<String, Object>> getObjectsByCodeList(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("meta_column") String meta_column, @Param("meta_id") String meta_id,
			@Param("column") String column, @Param("main_id") String pk_column, @Param("min") String min,
			@Param("max") String max);

	ArrayList<HashMap<String, Object>> getObjectsByCodeListWithoutMeta(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("column") String column, @Param("main_id") String pk_column,
			@Param("min") String min, @Param("max") String max);

	List<MDCodelist> getCodeList_list(@Param("db_schema") String db_schema);

	List<MDCodelist> getCodeList_listByConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);

	List<GenericType> getTableColumns(@Param("db_schema") String db_schema, @Param("table") String table);

	ArrayList<HashMap<String, Object>> getParentHierarchy(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("group_table") String group_table,
			@Param("group_column") String group_column, @Param("meta_column") String meta_column,
			@Param("id") String id, @Param("group_column_key") String group_column_key,
			@Param("primary_key") String primary_key);

	ArrayList<HashMap<String, Object>> getRootParentHierarchy(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("group_table") String group_table,
			@Param("group_column") String group_column, @Param("meta_column") String meta_column,
			@Param("id") String id, @Param("group_column_key") String group_column_key,
			@Param("columnName") String columnName, @Param("primary_key") String primary_key);

	ArrayList<HashMap<String, Object>> getChildrenHierarchy(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("group_table") String group_table,
			@Param("group_column") String group_column, @Param("meta_column") String meta_column,
			@Param("id") String id, @Param("group_column_key") String group_column_key,
			@Param("primary_key") String primary_key);

	List<MDGrouping> getGroups(@Param("db_schema") String db_schema, @Param("conceptId") int conceptId);

	ArrayList<HashMap<String, Object>> getGroupsValues(@Param("db_schema") String db_schema,
			@Param("list") List<Integer> list);

	MDGrouping getGroup(@Param("db_schema") String db_schema, @Param("filter") String filter);

	int countGroupsForConcept(@Param("db_schema") String db_schema, @Param("concept") String concept);

	ArrayList<HashMap<String, Object>> getGroupAttr(@Param("db_schema") String db_schema,
			@Param("filter") String filter);

	ArrayList<HashMap<String, Object>> getHierarchy(@Param("db_schema") String db_schema,
			@Param("filter") String filter);

	MDGrouping getGroupByFilter(@Param("db_schema") String db_schema, @Param("filter") String filter);

	MDGrouping getGroupByDescription(@Param("db_schema") String db_schema,
			@Param("short_description") String short_description);

	ArrayList<HashMap<String, Object>> getHierarchyForGroup(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("primary_key") String primary_key,
			@Param("meta_column") String meta_column, @Param("metacolumn_ret") String metacolumn_ret,
			@Param("hid") String hierarchy_id);

	Integer isObjectBelongingToGroup(@Param("db_schema") String db_schema, @Param("table") String table,
			@Param("grp_column") String grp_column, @Param("memb_column") String memb_column,
			@Param("group") String group, @Param("item_id") String item_id);

	TableReference getTableReferenceByName(@Param("db_schema") String db_schema, @Param("name") String name);

	TableReference getTableReferenceById(@Param("db_schema") String db_schema, @Param("id") int id);

	MDGroupingDepth getGroupDepth(@Param("db_schema") String db_schema, @Param("groupId") int groupId,
			@Param("hierarchy") String hierarchy);

	List<MDGroupingDepth> getGroupDepthById(@Param("db_schema") String db_schema, @Param("groupId") int groupId);

	ArrayList<HashMap<String, Object>> getRootHierarchy(@Param("db_schema") String db_schema,
			@Param("table") String table, @Param("primary_key") String primary_key,
			@Param("meta_column") String meta_column, @Param("meta") String meta);

	ArrayList<HashMap<String, Object>> getSubGroupHierarchy(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("hierarchyItemTable") String hierarchyItemTable,
			@Param("hierarchyGroupTable") String hierarchyGroupTable, @Param("item_primaryKey") String item_primaryKey,
			@Param("hierarchyItem_primaryKey") String hierarchyItem_primaryKey,
			@Param("item_metaColumn") String item_metaColumn,
			@Param("hierarchyItem_metaColumn") String hierarchyItem_metaColumn,
			@Param("hierarchyGroup_memberColumn") String hierarchyGroup_memberColumn,
			@Param("hierarchyGroup_groupColumn") String hierarchyGroup_groupColumn, @Param("list") List<String> list);

	ArrayList<HashMap<String, Object>> getSubGroupHierarchyAlternative(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("item_metaColumn") String item_metaColumn,
			@Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("filter") String filter);

	/*
	 * ArrayList<HashMap<String, Object>> getFilteredObjectsHierarchy(
	 * 
	 * @Param("db_schema") String db_schema,
	 * 
	 * @Param("itemTable") String itemTable,
	 * 
	 * @Param("hierarchyItemTable") String hierarchyItemTable,
	 * 
	 * @Param("hierarchyGroupTable") String hierarchyGroupTable,
	 * 
	 * @Param("itemGroupTable") String itemGroupTable,
	 * 
	 * @Param("item_primaryKey") String item_primaryKey,
	 * 
	 * @Param("item_metaColumn") String item_metaColumn,
	 * 
	 * @Param("hierarchyItem_primaryKey") String hierarchyItem_primaryKey,
	 * 
	 * @Param("hierarchyItem_metaColumn") String hierarchyItem_metaColumn,
	 * 
	 * @Param("hierarchyGroup_memberColumn") String hierarchyGroup_memberColumn,
	 * 
	 * @Param("hierarchyGroup_groupColumn") String hierarchyGroup_groupColumn,
	 * 
	 * @Param("itemGroup_groupColumn") String itemGroup_groupColumn,
	 * 
	 * @Param("itemGroup_memberColumn") String itemGroup_memberColumn,
	 * 
	 * @Param("filter") String filter,
	 * 
	 * @Param("meta") List<String> meta,
	 * 
	 * @Param("list") List<String> list);
	 */
	ArrayList<HashMap<String, Object>> getFilteredObjectsHierarchy(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("hierarchyItemTable") String hierarchyItemTable,
			@Param("hierarchyGroupTable") String hierarchyGroupTable, @Param("itemGroupTable") String itemGroupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("item_metaColumn") String item_metaColumn,
			@Param("hierarchyItem_primaryKey") String hierarchyItem_primaryKey,
			@Param("hierarchyItem_metaColumn") String hierarchyItem_metaColumn,
			@Param("hierarchyGroup_memberColumn") String hierarchyGroup_memberColumn,
			@Param("hierarchyGroup_groupColumn") String hierarchyGroup_groupColumn,
			@Param("itemGroup_groupColumn") String itemGroup_groupColumn,
			@Param("itemGroup_memberColumn") String itemGroup_memberColumn,
			@Param("hierarchyGroup_metaColumn") String hierarchyGroup_metaColumn, @Param("filter") String filter,
			@Param("meta") List<String> meta);

	/* For commodities groups */
	ArrayList<HashMap<String, Object>> getRootHierarchyCommodities(@Param("db_schema") String db_schema,
			@Param("hierarchyItemTable") String hierarchyItemTable,
			@Param("hierarchyItem_metaColumn") String hierarchyItem_metaColumn, @Param("groupTable") String groupTable,
			@Param("hierarchyItem_primaryKey") String hierarchyItem_primaryKey,
			@Param("group_memberColumn") String group_memberColumn, @Param("filter") String filter);

	ArrayList<HashMap<String, Object>> getSubGroupHierarchyCommodities(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("hierarchyGroupTable") String hierarchyGroupTable, @Param("item_primaryKey") String item_primaryKey,
			@Param("item_metaColumn") String item_metaColumn, @Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("filter") String filter);

	ArrayList<HashMap<String, Object>> getFilteredObjectsHierarchyCommodities(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("item_metaColumn") String item_metaColumn,
			@Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("filter") String filter,
			@Param("list") List<String> list);

	ArrayList<HashMap<String, Object>> getChildsHierarchyAlternative(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("item_metaColumn") String item_metaColumn,
			@Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("filter") String filter,
			@Param("list") List<String> list);

	ArrayList<HashMap<String, Object>> getFlatHierarchy(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("item_metaColumn") String item_metaColumn,
			@Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("filter") String filter);

	ArrayList<HashMap<String, Object>> getMetaGrouping(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("item_primaryKey") String item_primaryKey,
			@Param("item_metaColumn") String item_metaColumn, @Param("metaList") List<String> metaList);

	ArrayList<HashMap<String, Object>> getEntireHierarchy(@Param("db_schema") String db_schema,
			@Param("hierarchy") String hierarchy);

	ArrayList<HashMap<String, Object>> getAplhabeticalByFilter(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("item_metaColumn") String item_metaColumn,
			@Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("filter") String filter);

	ArrayList<HashMap<String, Object>> getFlatHierarchyType(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("meta_filter") String meta_filter,
			@Param("hierarchy") String hierarchy);

	ArrayList<HashMap<String, Object>> getFlatHierarchyTypeExtended(@Param("db_schema") String db_schema,
			@Param("itemTable") String itemTable, @Param("groupTable") String groupTable,
			@Param("item_primaryKey") String item_primaryKey, @Param("group_memberColumn") String group_memberColumn,
			@Param("group_groupColumn") String group_groupColumn, @Param("metaList") List<String> metaList,
			@Param("hierarchy") String hierarchy);
}