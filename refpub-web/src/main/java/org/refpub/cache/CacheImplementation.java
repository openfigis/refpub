package org.refpub.cache;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class CacheImplementation implements CacheInterface{
	private DB db;
	private HTreeMap<String, String> map;
	
	public CacheImplementation() {
		db = DBMaker.newFileDB(new File("/tmp/refpubCache"))
	               .closeOnJvmShutdown()
	               .encryptionEnable("password")
	               .make();

		if (!db.exists("refPubCollection")) {
		    map = db.createHashMap("refPubCollection")
	                .expireMaxSize(1000000)
	                .expireAfterWrite(30, TimeUnit.SECONDS)
	                .make();
		} else {
			map = db.getHashMap("refPubCollection");
		}
	}

	@Override
	public boolean isCached(String val) {
		if (map.containsKey(val)) {
			return true;
		}
		return false;
	}

	@Override
	public String getCachedValue(String val) {
		if (this.isCached(val))
			return map.get(val);
		return null;
	}

	@Override
	public void writeCache(String val, String store) {
		map.put(val, store);
		this.commit();
	}

	@Override
	public void purgeCache() {
		db.delete(null);
		this.commit();
	}

	@Override
	public void purgeElementFromCache(String val) {
		if (this.isCached(val)) {
			map.remove(val);
			this.commit();
		}
	}

	@Override
	public void commit() {
		db.commit();
		this.close();
	}
	
	private void close() {
		db.close();
	}

}
