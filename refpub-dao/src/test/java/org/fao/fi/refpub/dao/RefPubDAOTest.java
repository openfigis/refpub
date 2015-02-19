package org.fao.fi.refpub.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.db.TableInfo;
import org.fao.fi.refpub.dao.utils.Utils;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;
import org.fao.fi.refpub.persistence.PersistenceServiceInterface;

public class RefPubDAOTest extends TestCase{
	
	private static String FIGIS_SCHEMA = "FIGIS";
	private static String FIGIS_TEST_TABLE = "FIC_ITEM";
	private static PersistenceServiceInterface ps;
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RefPubDAOTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	ps = new PersistenceServiceImplementation("/work/FIGIS/refpub_data/conf/mybatis/mybatis-config.xml");
        return new TestSuite( RefPubDAOTest.class );
    }

    public void testGetTableInfo()
    {
    	TableInfo ti = ps.getTableInfo(FIGIS_SCHEMA, FIGIS_TEST_TABLE);
    	assertNotNull(ti);
    }
    
    public void testGetConcepts() {
    	List<MDConcept> list = ps.getConcepts(FIGIS_SCHEMA);
    	boolean listItemsFlag = false;
    	if (list.size() > 0) { listItemsFlag=true; }
    	assertTrue(listItemsFlag);
    }
    
    public void testGetConcept() {
    	MDConcept concept = ps.getConcept(FIGIS_SCHEMA, "Species");
    	assertNotNull(concept);
    }
    
    public void testGetCodelistForConcept() {
    	List<MDCodelist> list = ps.getCodelistForConcept(FIGIS_SCHEMA, "Species");
    	boolean listItemsFlag = false;
    	if (list.size() > 0) { listItemsFlag=true; }
    	assertTrue(listItemsFlag);
    }
    
    public void testGetDefaultCodelistFromConcept() {
    	MDCodelist c = ps.getDefaultCodelistFromConcept(FIGIS_SCHEMA, "Species");
    	assertNotNull(c);
    }
    
    public void testGetCodeList() {
    	MDCodelist c = ps.getCodeList(FIGIS_SCHEMA, "Species", "ID");
    	assertNotNull(c);
    }

    public void testGetObject() {
    	ArrayList<HashMap<String, Object>> results = ps.getObject(FIGIS_SCHEMA, FIGIS_TEST_TABLE, FIGIS_TEST_TABLE, "2753", FIGIS_TEST_TABLE);
    	if (results.size() < 1) {
    		assertTrue(false);
    	}
    	RefPubObject object = Utils.buildRefPubObject(results);
    	assertNotNull(object);
    }

    public void testGetObjectById() {
    	ArrayList<HashMap<String, Object>> results = ps.getObjectById(FIGIS_SCHEMA, FIGIS_TEST_TABLE, FIGIS_TEST_TABLE, "2753");
    	if (results.size() < 1) {
    		assertTrue(false);
    	}
    	RefPubObject object = Utils.buildRefPubObject(results);
    	assertNotNull(object);
    }

     public void testGetAttributeForSingleObject() {    
    	 ArrayList<HashMap<String, Object>> results = ps.getAttributeForSingleObject(FIGIS_SCHEMA, FIGIS_TEST_TABLE, "FIC_ITEM", FIGIS_TEST_TABLE, "2753", FIGIS_TEST_TABLE, "FULL_NAME_E");
     	 if (results.size() < 1) {
     		 assertTrue(false);
     	 }
     	 RefPubObject object = Utils.buildRefPubObject(results);
     	 assertNotNull(object); 
     }

     public void testGetObjectsByCodeList() {
    	 ArrayList<HashMap<String, Object>> results = ps.getObjectsByCodeList(FIGIS_SCHEMA, 
    			 										FIGIS_TEST_TABLE, 
    			 										"META", 
    			 										"31005", 
    			 										FIGIS_TEST_TABLE, 
    			 										FIGIS_TEST_TABLE, 
    			 										"0", "10");
    	 int length = results.size();
    	 if (length < 1 && length > 10) {
    		 assertTrue(false);
    	 }
    	 
    	 List<RefPubObject> obj = Utils.buildRefPubObjectList(results);
    	 assertNotNull(obj);
     }
     
     public void testGetCodeList_list() {
    	 List<MDCodelist> list = ps.getCodeList_list(FIGIS_SCHEMA);
    	 assertNotNull(list);
     }

     public void testGetCodeList_listByConcept() {
    	 List<MDCodelist> list = ps.getCodeList_listByConcept(FIGIS_SCHEMA, "Species");
    	 assertNotNull(list);
     }
     
     public void testGetTableColumns() {
    	 List<GenericType> list = ps.getTableColumns(FIGIS_SCHEMA, FIGIS_TEST_TABLE);
    	 assertNotNull(list);
     }
     
     public void testGetParentHierarchy() {
    	 ArrayList<HashMap<String, Object>> results = ps.getParentHierarchy(FIGIS_SCHEMA,
    			 															FIGIS_TEST_TABLE,
    			 															"FIC_ITEM_GRP_BASE", 
    			 															"ITEM_MEMB", 
    			 															"META",
    			 															"1412", 
    			 															"ITEM_GRP", 
    			 															FIGIS_TEST_TABLE);
    	 if (results.size() < 1) {
    		 assertTrue(false);
    	 }
    	 
    	 List<RefPubObject> objects = Utils.buildRefPubObjectList(results);
    	 assertNotNull(objects);
     }

     public void testGhildrenHierarchy() {
    	 ArrayList<HashMap<String, Object>> results = ps.getChildrenHierarchy(FIGIS_SCHEMA,
    			 															FIGIS_TEST_TABLE,
    			 															"FIC_ITEM_GRP_BASE", 
    			 															"ITEM_MEMB", 
    			 															"META",
    			 															"1412", 
    			 															"ITEM_GRP", 
    			 															FIGIS_TEST_TABLE);
    	 if (results.size() < 1) {
    		 assertTrue(false);
    	 }
    	 
    	 List<RefPubObject> objects = Utils.buildRefPubObjectList(results);
    	 assertNotNull(objects);
     }
     
}
