package org.fao.fi.refpub.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
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

	
	public List<RefPubObject> getObjects(String id, String table) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		try {
			PersistenceServiceInterface mapper = sqlSession.getMapper(PersistenceServiceInterface.class);
			return mapper.getObjects(id, table);
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


}
