package org.fao.fi.refpub.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.pool.MyBatisSqlSessionFactory;
public class PersistenceServiceImplementation implements PersistenceServiceInterface{
	
	public List<RefPubObject> getCategories() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCategories();
		} finally {
			sqlSession.close();
		}
	}

	
	public List<RefPubObject> getObjects(String id, String table, String pk_column) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjects(id, table, pk_column);
		} finally {
			sqlSession.close();
		}
	}


	public ClassInitXML getRefTable(int id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getRefTable(id);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDConcept> getConcepts() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getConcepts();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public MDConcept getConcept(String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getConcept(concept);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDCodelist> getCodelistForConcept(String concept) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodelistForConcept(concept);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public RefPubObject getObject(String table, String column, String codelist, String pk_column) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObject(table, column, codelist, pk_column);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public MDCodelist getCodeList(String concept, String codelistname) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodeList(concept, codelistname);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<RefPubObject> getParentHierarchy(String table, String group_table,
			String group_column, String id, String group_column_key, String primary_key) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getParentHierarchy(table, group_table, group_column, id, group_column_key, primary_key);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public TableInfo getTableInfo(String table) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getTableInfo(table);
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<MDCodelist> getCodeList_list() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getCodeList_list();
		} finally {
			sqlSession.close();
		}
	}


	@Override
	public List<RefPubObject> getObjectsByCodeList(String table, String column, String pk_column) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjectsByCodeList(table, column, pk_column);
		} finally {
			sqlSession.close();
		}
	}
}
