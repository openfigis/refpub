package org.fao.fi.refpub.webservice.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("refpub-config")
public class Configuration {
	private String db_schema = "FIGIS";
	private String cache_expiry;
	private String mybatis_configuration_file;
	
	private Properties properties;
	public Configuration() {}
	
	public Configuration(String propertiesFile) {
		try {
			File file = new File(propertiesFile);
			FileInputStream fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				if (key.toLowerCase().equals("db-schema")) { 
					this.setDb_schema(properties.getProperty(key));
				}
				if (key.toLowerCase().equals("cache-expiry-seconds")) { 
					this.setCache_expiry(properties.getProperty(key));
				}
				if (key.toLowerCase().equals("mybatis-config-file")) { 
					this.setMybatis_configuration_file(properties.getProperty(key));
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public String getDb_schema() {
		return db_schema;
	}

	private void setDb_schema(String db_schema) {
		this.db_schema = db_schema;
	}

	public Properties getProperties() {
		return properties;
	}

	private void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getCache_expiry() {
		return cache_expiry;
	}

	private void setCache_expiry(String cache_expiry) {
		this.cache_expiry = cache_expiry;
	}

	public String getMybatis_configuration_file() {
		return mybatis_configuration_file;
	}

	private void setMybatis_configuration_file(String mybatis_configuration_file) {
		this.mybatis_configuration_file = mybatis_configuration_file;
	}
}
