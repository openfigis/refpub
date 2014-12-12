package org.refpub.cache;


public interface CacheInterface {
	
	boolean isCached(String val);
	String getCachedValue(String val);
	void writeCache(String val, String store);
	void purgeCache();
	void purgeElementFromCache(String val);
	void commit();

}
