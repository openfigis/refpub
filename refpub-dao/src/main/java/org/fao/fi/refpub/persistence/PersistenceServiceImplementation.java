package org.fao.fi.refpub.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.chunks.MDGrouping;
import org.fao.fi.refpub.dao.objects.chunks.MDGroupingDepth;
import org.fao.fi.refpub.dao.objects.chunks.TableReference;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.pool.MyBatisSqlSessionFactory;
import org.fao.fi.refpub.dao.pool.MyBatisSqlSessionFactoryLocalMysql;
public class PersistenceServiceImplementation implements PersistenceServiceInterface{
	
	public PersistenceServiceImplementation() {}
	
	public ArrayList<HashMap<String, Object>> getObjects(String db_schema, List<String> meta, String id_column, String table, String pk_column, String min, String max) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjects(db_schema, meta, id_column, table, pk_column, min, max);
		} finally {
			sqlSession.close();
		}
	}


	public ClassInitXML getRefTable(String db_schema, int id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getRefTable(db_schema, id);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDConcept> getConcepts(String db_schema) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getConcepts(db_schema);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public MDConcept getConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDCodelist> getCodelistForConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodelistForConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public MDCodelist getDefaultCodelistFromConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getDefaultCodelistFromConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}


	/*@Override
	public RefPubObject getObject(String db_schema, String table, String column, String codelist, String pk_column) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObject(db_schema, table, column, codelist, pk_column);
		} finally {
			sqlSession.close();
		}
	}*/
	
	@Override
	public ArrayList<HashMap<String, Object>> getObject(String db_schema, String table, String column, String codelist, String pk_column) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObject(db_schema, table, column, codelist, pk_column);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getObjectById(String db_schema, String table, String pk_column,
			String id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjectById(db_schema, table, pk_column, id);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getObjectByMeta(String db_schema,
			String table, String pk_column, String meta_column, String meta) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjectByMeta(db_schema, table, pk_column, meta_column, meta);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int getObjectByMetaCount(String db_schema, String table,
			String pk_column, String meta_column, String meta) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjectByMetaCount(db_schema, table, pk_column, meta_column, meta);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getObjectByMetaPaginate(
			String db_schema, String table, String pk_column,
			String meta_column, String meta, String start, String numRows) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjectByMetaPaginate(db_schema, table, pk_column, meta_column, meta, start, numRows);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public MDCodelist getCodeList(String db_schema, String concept, String codelistname) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodeList(db_schema, concept, codelistname);
		} finally {
			sqlSession.close();
		}
	}


	/*@Override
	public List<RefPubObject> getParentHierarchy(String db_schema, String table, String group_table,
			String group_column, String meta_column, String id, String group_column_key, String primary_key) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getParentHierarchy(db_schema, table, group_table, group_column, meta_column, id, group_column_key, primary_key);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<RefPubObject> getChildrenHierarchy(String db_schema, String table,
			String group_table, String group_column, String meta_column,
			String id, String group_column_key, String primary_key) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getChildrenHierarchy(db_schema, table, group_table, group_column, meta_column, id, group_column_key, primary_key);
		} finally {
			sqlSession.close();
		}
	}*/
	
	@Override
	public ArrayList<HashMap<String, Object>> getParentHierarchy(String db_schema, String table, String group_table,
			String group_column, String meta_column, String id, String group_column_key, String primary_key) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getParentHierarchy(db_schema, table, group_table, group_column, meta_column, id, group_column_key, primary_key);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getRootParentHierarchy(
			String db_schema, String table, String group_table,
			String group_column, String meta_column, String id,
			String group_column_key, String primary_key) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getRootParentHierarchy(db_schema, table, group_table, group_column, meta_column, id, group_column_key, primary_key);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getChildrenHierarchy(String db_schema, String table,
			String group_table, String group_column, String meta_column,
			String id, String group_column_key, String primary_key) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getChildrenHierarchy(db_schema, table, group_table, group_column, meta_column, id, group_column_key, primary_key);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public TableInfo getTableInfo(String db_schema, String table) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getTableInfo(db_schema, table);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDCodelist> getCodeList_list(String db_schema) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodeList_list(db_schema);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public ArrayList<HashMap<String, Object>> getObjectsByCodeList(String db_schema, 
												String table, 
												String meta_column, 
												String meta_id, 
												String column, 
												String pk_column, 
												String min, 
												String max) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjectsByCodeList(db_schema, table, meta_column, meta_id, column, pk_column, min, max);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDCodelist> getCodeList_listByConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodeList_listByConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<GenericType> getTableColumns(String db_schema, String table) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getTableColumns(db_schema, table);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public ArrayList<HashMap<String, Object>> getAttributeForSingleObject(String db_schema, String table,
			String column, String codelistvalue, String pk_column,
			String attribute) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getAttributeForSingleObject(db_schema, table, column, codelistvalue, pk_column, attribute);
		} finally {
			sqlSession.close();
		}
	}

	
	

	@Override
	public List<MDGrouping> getGroups(String db_schema, int conceptId) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroups(db_schema, conceptId);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public ArrayList<HashMap<String, Object>> getGroupsValues(String db_schema,
			List<Integer> list) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroupsValues(db_schema, list);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public ArrayList<HashMap<String, Object>> getHierarchy(String db_schema,
			String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getHierarchy(db_schema, filter);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public ArrayList<HashMap<String, Object>> getGroupAttr(String db_schema,
			String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroupAttr(db_schema, filter);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public MDGrouping getGroup(String db_schema, String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroup(db_schema, filter);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public MDGrouping getGroupByFilter(String db_schema, String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroupByFilter(db_schema, filter);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public MDGrouping getGroupByDescription(String db_schema,
			String short_description) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroupByDescription(db_schema, short_description);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public ArrayList<HashMap<String, Object>> getHierarchyForGroup(
			String db_schema, String table, String primary_key, String meta_column,
			String metacolumn_ret, String hierarchy_id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getHierarchyForGroup(db_schema, table, primary_key,
					meta_column, metacolumn_ret, hierarchy_id);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public Integer isObjectBelongingToGroup(String db_schema, String table, String grp_column, String memb_column,
			String group, String item_id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			Integer result = mapper.isObjectBelongingToGroup(db_schema, table, grp_column,
					memb_column, group, item_id);
			return result;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public TableReference getTableReferenceByName(String db_schema, String name) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getTableReferenceByName(db_schema, name);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public TableReference getTableReferenceById(String db_schema, int id) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getTableReferenceById(db_schema, id);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public MDGroupingDepth getGroupDepth(String db_schema, int groupId, String hierarchy) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getGroupDepth(db_schema, groupId, hierarchy);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getRootHierarchy(
			String db_schema, String table, String primary_key,
			String meta_column, String meta) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getRootHierarchy(db_schema, table, primary_key,
					meta_column, meta);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getSubGroupHierarchy(
			String db_schema, String itemTable, String hierarchyItemTable,
			String hierarchyGroupTable, String item_primaryKey,
			String hierarchyItem_primaryKey,
			String item_metaColumn,
			String hierarchyItem_metaColumn,
			String hierarchyGroup_memberColumn,
			String hierarchyGroup_groupColumn, List<String> list) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getSubGroupHierarchy(db_schema, itemTable, hierarchyItemTable,
					hierarchyGroupTable, item_primaryKey, hierarchyItem_primaryKey, item_metaColumn,
					hierarchyItem_metaColumn, hierarchyGroup_memberColumn, hierarchyGroup_groupColumn, list);
		} finally {
			sqlSession.close();
		}
	}

	/*@Override
	public ArrayList<HashMap<String, Object>> getFilteredObjectsHierarchy(
			String db_schema, String itemTable, String hierarchyItemTable,
			String hierarchyGroupTable, String itemGroupTable,
			String item_primaryKey, String item_metaColumn,
			String hierarchyItem_primaryKey,
			String hierarchyItem_metaColumn,
			String hierarchyGroup_memberColumn,
			String hierarchyGroup_groupColumn, String itemGroup_groupColumn, String hierarchyGroup_metaColumn,
			String filter, List<String> meta) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getFilteredObjectsHierarchy(db_schema, itemTable, hierarchyItemTable,
					hierarchyGroupTable, itemGroupTable, item_primaryKey, item_metaColumn, hierarchyItem_primaryKey,
					hierarchyItem_metaColumn, hierarchyGroup_memberColumn, hierarchyGroup_groupColumn, itemGroup_groupColumn,
					itemGroup_memberColumn, filter, meta);
		} finally {
			sqlSession.close();
		}
	}*/
	@Override
	public ArrayList<HashMap<String, Object>> getFilteredObjectsHierarchy(
			String db_schema, String itemTable, String hierarchyItemTable,
			String hierarchyGroupTable, String itemGroupTable,
			String item_primaryKey, String item_metaColumn,
			String hierarchyItem_primaryKey, String hierarchyItem_metaColumn,
			String hierarchyGroup_memberColumn,
			String hierarchyGroup_groupColumn, String itemGroup_groupColumn,
			String itemGroup_memberColumn, String hierarchyGroup_metaColumn,
			String filter, List<String> meta) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getFilteredObjectsHierarchy(db_schema, itemTable, hierarchyItemTable,
					hierarchyGroupTable, itemGroupTable, item_primaryKey, item_metaColumn, hierarchyItem_primaryKey,
					hierarchyItem_metaColumn, hierarchyGroup_memberColumn, hierarchyGroup_groupColumn, itemGroup_groupColumn,
					itemGroup_memberColumn, hierarchyGroup_metaColumn, filter, meta);
		} finally {
			sqlSession.close();
		}
	}

	/* For Commodities groups */
	@Override
	public ArrayList<HashMap<String, Object>> getRootHierarchyCommodities(
			String db_schema, String hierarchyItemTable, String hierarchyItem_metaColumn, String groupTable,
			String hierarchyItem_primaryKey, String group_memberColumn, String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getRootHierarchyCommodities(db_schema, hierarchyItemTable, hierarchyItem_metaColumn,
					groupTable, hierarchyItem_primaryKey, group_memberColumn, filter);
		} finally {
			sqlSession.close();
		}
	}
	@Override
	public ArrayList<HashMap<String, Object>> getSubGroupHierarchyCommodities(
			String db_schema, String itemTable, String groupTable, String hierarchyGroupTable,
			String item_primaryKey, String item_metaColumn,
			String group_memberColumn, String group_groupColumn, String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getSubGroupHierarchyCommodities(db_schema, itemTable, groupTable, 
					hierarchyGroupTable, item_primaryKey, item_metaColumn, group_memberColumn, 
					group_groupColumn, filter);
		} finally {
			sqlSession.close();
		}
	}
	@Override
	public ArrayList<HashMap<String, Object>> getFilteredObjectsHierarchyCommodities(
			String db_schema, String itemTable, String groupTable,
			String item_primaryKey, String item_metaColumn,
			String group_memberColumn, String group_groupColumn, String filter,
			List<String> list) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getFilteredObjectsHierarchyCommodities(db_schema, itemTable, groupTable, 
					item_primaryKey, item_metaColumn, group_memberColumn, group_groupColumn, 
					filter, list);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getSubGroupHierarchyAlternative(
			String db_schema, String itemTable, String groupTable,
			String item_primaryKey, String item_metaColumn,
			String group_memberColumn, String group_groupColumn,
			String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getSubGroupHierarchyAlternative(db_schema, itemTable, groupTable, 
					item_primaryKey, item_metaColumn, group_memberColumn, group_groupColumn,
					filter);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getChildsHierarchyAlternative(
			String db_schema, String itemTable, String groupTable,
			String item_primaryKey, String item_metaColumn,
			String group_memberColumn, String group_groupColumn, String filter,
			List<String> list) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getChildsHierarchyAlternative(db_schema, itemTable, groupTable, 
					item_primaryKey, item_metaColumn, group_memberColumn, group_groupColumn,
					filter, list);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getFlatHierarchy(
			String db_schema, String itemTable, String groupTable,
			String item_primaryKey, String item_metaColumn,
			String group_memberColumn, String group_groupColumn, String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getFlatHierarchy(db_schema, itemTable, groupTable, 
					item_primaryKey, item_metaColumn, group_memberColumn, group_groupColumn,
					filter);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getMetaGrouping(String db_schema,
			String itemTable, String item_primaryKey, String item_metaColumn,
			List<String> metaList) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getMetaGrouping(db_schema, itemTable, 
					item_primaryKey, item_metaColumn, metaList);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getEntireHierarchy(String db_schema,
			String hierarchy) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getEntireHierarchy(db_schema, hierarchy);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAplhabeticalByFilter(
			String db_schema, String itemTable, String groupTable, 
			String item_primaryKey, String item_metaColumn, String group_memberColumn,
			String group_groupColumn, String filter) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getAplhabeticalByFilter(db_schema, itemTable, groupTable, item_primaryKey, item_metaColumn, group_memberColumn, group_groupColumn, filter);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int countGroupsForConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactoryLocalMysql.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.countGroupsForConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}
}