package org.fao.fi.refpub.dao.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	private static String CONFIG_FILE;
	
	public static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory==null) {
			InputStream inputStream;
			try {
				//inputStream = Resources.getResourceAsStream("org/fao/fi/mybatis/mappers/mybatis-config.xml");
				inputStream = new FileInputStream(CONFIG_FILE);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				try {
					sqlSessionFactory = new SqlSessionFactoryBuilder().build(
							Resources.getResourceAsStream("org/fao/fi/mybatis/mappers/mybatis-config.xml"));
				} catch (IOException ex1) {
					throw new RuntimeException(ex1.getCause());
				}
				
			}
		}
		return sqlSessionFactory;
	}

	
	public static SqlSession openSession(String configFile) {
		CONFIG_FILE = configFile;
		return getSqlSessionFactory().openSession();
	}

}

