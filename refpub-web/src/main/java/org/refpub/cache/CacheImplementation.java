package org.refpub.cache;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

public class CacheImplementation implements CacheInterface{
	private DB db;
	private HTreeMap<String, List<String>> map;
	private String defaultPath = "/tmp";
	private String path;
	
	final static Logger logger = Logger.getLogger(CacheImplementation.class);
	
	private int DEFAULT_EXPIRY_SECONDS = 120;
	private int EXPIRY_SECONDS;
	
	public CacheImplementation(String pathToSaveCache, String expiry) {
		if (expiry == null) {
			EXPIRY_SECONDS = DEFAULT_EXPIRY_SECONDS;
		} else {
			try {
				EXPIRY_SECONDS = Integer.parseInt(expiry);
			} catch (Exception ex) {
				logger.debug("Error Setting up Expiry seconds. Configuration entry seems not an Integer value");
				EXPIRY_SECONDS = DEFAULT_EXPIRY_SECONDS;
			}
		}
		if (pathToSaveCache == null || pathToSaveCache.equalsIgnoreCase("")) {
			path = defaultPath;
		} else {
			path = pathToSaveCache;
		}
		init();
	}
	public CacheImplementation(String pathToSaveCache) {
		EXPIRY_SECONDS = DEFAULT_EXPIRY_SECONDS;
		if (pathToSaveCache == null || pathToSaveCache.equalsIgnoreCase("")) {
			path = defaultPath;
		} else {
			path = pathToSaveCache;
		}
		init();
	}
	public CacheImplementation() {
		EXPIRY_SECONDS = DEFAULT_EXPIRY_SECONDS;
		path = defaultPath;
		init();
	}
	public void init() {
		logger.debug("Init the cache mechanism. Expiry period set to " + Integer.toString(EXPIRY_SECONDS) + " seconds");
		logger.debug("... Path where the cache is stored is: " + path);
		if (!path.endsWith("/")) {
			path += "/";
		}
		db = DBMaker.newFileDB(new File(path + "refpubCache"))
	               .closeOnJvmShutdown()
	               .encryptionEnable("password")
	               .make();

		if (!db.exists("refPubCollection")) {
		    map = db.createHashMap("refPubCollection")
	                .expireMaxSize(1000000)
	                .make();
		} else {
			map = db.getHashMap("refPubCollection");
		}
	}

	@Override
	public boolean isCached(String val) {
		if (map.containsKey(val)) {
			List<String> ccached = map.get(val);
			long tsehc = Long.parseLong(ccached.get(1));
			long currentTs = Long.parseLong(this.getCurrentTimestamp());
			if ( (currentTs - tsehc) < this.EXPIRY_SECONDS) {
				logger.debug("The cache for val: " + val + " is still valid");
				return true;
			} else {
				logger.debug("The cache for val: " + val + " is expired");
				this.purgeElementFromCache(val);
				return false;
			}
		}
		return false;
	}

	@Override
	public String getCachedValue(String val) {
		if (this.isCached(val))
			try {
				logger.info("Returning cached resource");
				if (db.isClosed()) { init(); }
				return map.get(val).get(0);
			} catch (Exception ex) {
				logger.info("Error returning cached resource. Returning null");
				return null;
			}
		return null;
	}

	@Override
	public void writeCache(String val, String store) {
		List<String> ehc = new ArrayList<String>();
		ehc.add(store);
		ehc.add(this.getCurrentTimestamp());
		if (db.isClosed()) { init(); }
		try {
			map.put(val, ehc);
			this.commit();
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			logger.error("Error writing cache. It might be worth flushing the cache on " + path);
		}
	}

	@Override
	public void purgeCache() {
		db.delete(null);
		this.commit();
	}

	@Override
	public void purgeElementFromCache(String val) {
		if (map.containsKey(val)) {
			if (db.isClosed()) { init(); }
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
	
	private String getCurrentTimestamp() {
		long unixTimestamp = Instant.now().getEpochSecond();
		return Long.toString(unixTimestamp);
	}

}
