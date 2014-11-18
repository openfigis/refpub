package org.fao.fi.refpub.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.pool.MyBatisSqlSessionFactory;
public class PersistenceServiceImplementation implements PersistenceServiceInterface{
	
	public PersistenceServiceImplementation() {}

	
	public List<RefPubObject> getCategories(String db_schema) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCategories(db_schema);
		} finally {
			sqlSession.close();
		}
	}

	
	/*public List<RefPubObject> getObjects(String db_schema, String id, String id_column, String table, String pk_column, String min, String max) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjects(db_schema, id, id_column, table, pk_column, min, max);
		} finally {
			sqlSession.close();
		}
	}*/
	
	public ArrayList<HashMap<String, Object>> getObjects(String db_schema, String id, String id_column, String table, String pk_column, String min, String max) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjects(db_schema, id, id_column, table, pk_column, min, max);
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
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getConcepts(db_schema);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public MDConcept getConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDCodelist> getCodelistForConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodelistForConcept(db_schema, concept);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public MDCodelist getDefaultCodelistFromConcept(String db_schema, String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
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
	public RefPubObject getObjectById(String db_schema, String table, String pk_column,
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
	public MDCodelist getCodeList(String db_schema, String concept, String codelistname) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
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
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
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
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
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
	public RefPubObject getAttributeForSingleObject(String db_schema, String table,
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
}