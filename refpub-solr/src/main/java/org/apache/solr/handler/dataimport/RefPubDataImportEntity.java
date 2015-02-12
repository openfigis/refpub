package org.apache.solr.handler.dataimport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefPubDataImportEntity extends EntityProcessorBase{
	private String host;
	
	private List<FIGISObject> figis_objects;
	private int counter = 0;
	
	private static final Logger LOG = LoggerFactory.getLogger(RefPubDataImportEntity.class);
	
	@Override
	public void init(Context context) {
		super.init(context);
		host = getStringFromContext("host", null);
		figis_objects = new ArrayList<FIGISObject>();
		if (host != null) {
			String url = this.buildURL("/rest/concept/?count=all");
			List<Map<String, String>> concepts = this.getConcepts(url);
			for (Map<String, String> c : concepts) {
	    		String concept = "";
	    		for (String k : c.keySet()) {
	    			concept = k;
	    		}
	    		
	    		String url2 = c.get(concept) + "?count=all";
	    		
	    		if (concept.endsWith("_group")) {
	    			List<FIGISObject> list = this.getGroups(url2, concept.replace("_group", ""));
		    		for (FIGISObject o : list) {
		    			figis_objects.add(o);
		    		}
	    		} else {
	    			List<FIGISObject> list = this.getObjects(url2);
		    		for (FIGISObject o : list) {
		    			figis_objects.add(o);
		    		}
	    		}
	    		
			}
		}
	}
	
	
	@Override
	  public Map<String,Object> nextRow() {
	    Map<String, Object> row = new HashMap<String, Object>();
	    if (figis_objects != null) {
	    	if (counter < figis_objects.size()) {
	    		FIGISObject obj = figis_objects.get(counter);
	    		row.put("name_e", obj.getName_e());
    			row.put("name_f", obj.getName_f());
    			row.put("name_r", obj.getName_r());
    			row.put("name_s", obj.getName_s());
    			row.put("name_a", obj.getName_a());
    			row.put("name_c", obj.getName_c());
    			
    			row.put("full_name_e", obj.getFull_name_e());
    			row.put("full_name_f", obj.getFull_name_f());
    			row.put("full_name_r", obj.getFull_name_r());
    			row.put("full_name_s", obj.getFull_name_s());
    			row.put("full_name_a", obj.getFull_name_a());
    			row.put("full_name_c", obj.getFull_name_c());
    			
    			row.put("long_name_e", obj.getLong_name_e());
    			row.put("long_name_f", obj.getLong_name_f());
    			row.put("long_name_r", obj.getLong_name_r());
    			row.put("long_name_s", obj.getLong_name_s());
    			row.put("long_name_a", obj.getLong_name_a());
    			row.put("long_name_c", obj.getLong_name_c());
    			
    			row.put("official_name_e", obj.getOfficial_name_e());
    			row.put("official_name_f", obj.getOfficial_name_f());
    			row.put("official_name_r", obj.getOfficial_name_r());
    			row.put("official_name_s", obj.getOfficial_name_s());
    			row.put("official_name_a", obj.getOfficial_name_a());
    			row.put("official_name_c", obj.getOfficial_name_c());
    			
    			row.put("short_description_e", obj.getShort_description_e());
    			row.put("short_description_f", obj.getShort_description_f());
    			row.put("short_description_r", obj.getShort_description_r());
    			row.put("short_description_s", obj.getShort_description_s());
    			row.put("short_description_a", obj.getShort_description_a());
    			row.put("short_description_c", obj.getShort_description_c());
    			
    			row.put("scientific_name", obj.getScientific_name());
    			row.put("vessel_name", obj.getVessel());
    			
    			row.put("concept", obj.getConcept());
    			row.put("concept_group", obj.getConcept_group());
    			row.put("url", obj.getUrl());
    			
    			Random rand = new Random();
    			row.put("id", rand.nextInt());
	    		counter++;
	    		return row;
	    	} else {
	    		return null;
	    	}
	    } else {
	    	return null;
	    }
	  }
	
	private String getStringFromContext(String prop, String ifNull) {
	    String v = ifNull;
	    String val = context.getEntityAttribute(prop);
	    if (val != null) {
	    	val = context.replaceTokens(val);
	    	v = val;
	    }
	    return v;
	}
	
	private String buildURL (String path) {
		if (host.endsWith("/")) {
			return host + path.replaceFirst("/", "");
		} else {
			return host + "/" + path.replaceFirst("/", "");
		}
	}
	
	private List<Map<String, String>> getConcepts(String url) {
    	try {
    		InputStream in = new URL( url ).openStream();
            String genreJson = IOUtils.toString(in);
            JSONObject conceptJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            JSONObject list = (JSONObject) conceptJsonObject.get("conceptList");
            JSONArray conceptArray = (JSONArray) list.get("concept");
            List<Map<String, String>> returnVal = new ArrayList<Map<String, String>>();
            
            for (int i = 0; i < conceptArray.size(); i++) {
	            JSONObject conceptArrayLinks = (JSONObject) conceptArray.get(i);
	            
	            JSONArray arrayOfConceptsLinks = (JSONArray) conceptArrayLinks.get("link");
	            
	            JSONObject c = (JSONObject) arrayOfConceptsLinks.get(0);
	            
	            Map<String,String> t = new HashMap<String, String>();
	            t.put((String) c.get("rel"), (String) c.get("href"));
	            returnVal.add(t);
	            
	            JSONObject groups = (JSONObject) conceptArrayLinks.get("groups");
	            if (groups != null) {
	            	System.out.println(c.get("rel"));
	            	JSONArray groupLink = (JSONArray) groups.get("link");
	            	JSONObject g = (JSONObject) groupLink.get(0);
	            	Map<String,String> gg = new HashMap<String, String>();
		            gg.put((String) c.get("rel") + "_group", (String) g.get("href"));
		            returnVal.add(gg);
	            }
            }
            return returnVal;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    private List<FIGISObject> getObjects(String url) {
    	
    	List<FIGISObject> returnList = new ArrayList<FIGISObject>();
    	try {
    		InputStream in = new URL( url ).openStream();
            String genreJson = IOUtils.toString(in);
            JSONObject conceptJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            JSONObject conceptList = (JSONObject) conceptJsonObject.get("conceptList"); 
            JSONArray conceptArray = (JSONArray) conceptList.get("concept");
            for (int i = 0; i < conceptArray.size(); i++) {
            	JSONObject ca = (JSONObject) conceptArray.get(i);
            	FIGISObject figis = this.parseFigisObject(ca);  
            	List<FIGISObject> hierarchy = this.flatHierarchy(figis.getHierarchy());
            	for (FIGISObject fi : hierarchy) {
            		returnList.add(fi);
            	}
            	returnList.add(figis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return this.cleanUpList(returnList);
    }
	
    private List<FIGISObject> getGroups(String url, String concept) {
		List<FIGISObject> returnList = new ArrayList<FIGISObject>();
		InputStream in;
		try {
			in = new URL( url ).openStream();
			String genreJson = IOUtils.toString(in);
			JSONObject conceptJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            JSONObject conceptList = (JSONObject) conceptJsonObject.get("conceptList"); 
            JSONArray conceptArray = (JSONArray) conceptList.get("concept");			
			
			for (int i = 0; i < conceptArray.size(); i++) {
				FIGISObject figis = new FIGISObject();
				figis.setConcept_group(concept);
				JSONObject obj = (JSONObject) conceptArray.get(i);
				JSONArray links = (JSONArray) obj.get("link");
				JSONObject multilingualName = (JSONObject) obj.get("multilingualName");
				
				for (int j = 0; j < links.size(); j++) {
            		JSONObject la = (JSONObject) links.get(j);
            		figis.setConcept((String) la.get("rel"));
            		figis.setUrl((String) la.get("href"));
            	}
				
				if (multilingualName != null && multilingualName.size() > 0) {
					if (multilingualName.containsKey("EN")) {
            			figis.setName_e((String) multilingualName.get("EN"));
            		}
            		if (multilingualName.containsKey("FR")) {
            			figis.setName_f((String) multilingualName.get("FR"));
            		}
            		if (multilingualName.containsKey("ES")) {
            			figis.setName_s((String) multilingualName.get("ES"));
            		}
            		if (multilingualName.containsKey("AR")) {
            			figis.setName_a((String) multilingualName.get("AR"));
            		}
            		if (multilingualName.containsKey("RU")) {
            			figis.setName_r((String) multilingualName.get("RU"));
            		}
            		if (multilingualName.containsKey("ZH")) {
            			figis.setName_c((String) multilingualName.get("ZH"));
            		}
				}
				returnList.add(figis);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnList;
	}
    
    
    private List<FIGISObject> getHierarchy(String url) {
		InputStream in;
		List<FIGISObject> returnList = new ArrayList<FIGISObject>();
		try {
			in = new URL( url ).openStream();
			String genreJson = IOUtils.toString(in);
			JSONObject conceptJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            if (conceptJsonObject.containsKey("hierarchy")) {
            	JSONObject hierarchy = (JSONObject) conceptJsonObject.get("hierarchy");
            	JSONArray parentsArray = (JSONArray) hierarchy.get("parents");
            	JSONObject parent = (JSONObject) parentsArray.get(0);
            	
            	if (parent != null) {
            		JSONArray listOfParents = (JSONArray) parent.get("parent");
            		if (listOfParents != null) {
	            		for (int i = 0; i < listOfParents.size(); i++) {
	            			JSONObject obj = (JSONObject) listOfParents.get(i);
	            			FIGISObject figis = this.parseFigisObject(obj);
	            			if (figis.getUrl() != null) {
	            				List<FIGISObject> list = this.getHierarchy(figis.getUrl());
	            				for (FIGISObject hi : list) {
	            					returnList.add(hi);
	            				}
	            			}
	            			returnList.add(figis);
	            		}
            		}
            	}
            }
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return this.cleanUpList(returnList);
	}
   	
	private FIGISObject parseFigisObject(JSONObject ca) {
		return this.parseFigisObject(ca, true);
	}
	
	private FIGISObject parseFigisObject(JSONObject ca, boolean iterate) {
		JSONObject multilingualLongName = (JSONObject) ca.get("multilingualLongName");
    	JSONObject multilingualName = (JSONObject) ca.get("multilingualName");
    	JSONObject multilingualFullName = (JSONObject) ca.get("multilingualFullName");
    	JSONObject multilingualOfficialName = (JSONObject) ca.get("multilingualOfficialName");
    	JSONObject multilingualShortDescription = (JSONObject) ca.get("multilingualShortDescription");
    	String scientific_name = null;
    	if (ca.containsKey("scientific_name")) {
    		scientific_name = (String) ca.get("scientific_name");
    	}
    	JSONArray link = (JSONArray) ca.get("link");
    	FIGISObject figis = new FIGISObject();
    	String objectUrl = null;
    	String conceptName = null;
    	for (int j = 0; j < link.size(); j++) {
    		JSONObject la = (JSONObject) link.get(j);
    		conceptName = (String) la.get("rel");
    		figis.setConcept(conceptName);
    		objectUrl = (String) la.get("href");
    		figis.setUrl(objectUrl);
    	}
    	if (iterate) {
	    	if (objectUrl != null) {
	    		if (conceptName != null && conceptName.equalsIgnoreCase("Country")) {
	    			List<FIGISObject> hierarchy = this.getHierarchy(objectUrl);
	    			figis.setHierarchy(hierarchy);
	    		}
	    	}
    	}
    	
    	if (scientific_name != null) {
    		figis.setScientific_name(scientific_name);
    	}
    	
       	if (multilingualName != null && multilingualName.size() > 0) {
    		if (multilingualName.containsKey("EN")) {
    			figis.setName_e((String) multilingualName.get("EN"));
    		}
    		if (multilingualName.containsKey("FR")) {
    			figis.setName_f((String) multilingualName.get("FR"));
    		}
    		if (multilingualName.containsKey("ES")) {
    			figis.setName_s((String) multilingualName.get("ES"));
    		}
    		if (multilingualName.containsKey("AR")) {
    			figis.setName_a((String) multilingualName.get("AR"));
    		}
    		if (multilingualName.containsKey("RU")) {
    			figis.setName_r((String) multilingualName.get("RU"));
    		}
    		if (multilingualName.containsKey("ZH")) {
    			figis.setName_c((String) multilingualName.get("ZH"));
    		}
    	}
    	
    	if (multilingualFullName != null && multilingualFullName.size() > 0) {
    		if (multilingualFullName.containsKey("EN")) {
    			figis.setFull_name_e((String) multilingualFullName.get("EN"));
    		}
    		if (multilingualFullName.containsKey("FR")) {
    			figis.setFull_name_f((String) multilingualFullName.get("FR"));
    		}
    		if (multilingualFullName.containsKey("ES")) {
    			figis.setFull_name_s((String) multilingualFullName.get("ES"));
    		}
    		if (multilingualFullName.containsKey("AR")) {
    			figis.setFull_name_a((String) multilingualFullName.get("AR"));
    		}
    		if (multilingualFullName.containsKey("RU")) {
    			figis.setFull_name_r((String) multilingualFullName.get("RU"));
    		}
    		if (multilingualFullName.containsKey("ZH")) {
    			figis.setFull_name_c((String) multilingualFullName.get("ZH"));
    		}
    	}
    	
    	if (multilingualLongName != null && multilingualLongName.size() > 0) {
    		if (multilingualLongName.containsKey("EN")) {
    			figis.setLong_name_e((String) multilingualLongName.get("EN"));
    		}
    		if (multilingualLongName.containsKey("FR")) {
    			figis.setLong_name_f((String) multilingualLongName.get("FR"));
    		}
    		if (multilingualLongName.containsKey("ES")) {
    			figis.setLong_name_s((String) multilingualLongName.get("ES"));
    		}
    		if (multilingualLongName.containsKey("AR")) {
    			figis.setLong_name_a((String) multilingualLongName.get("AR"));
    		}
    		if (multilingualLongName.containsKey("RU")) {
    			figis.setLong_name_r((String) multilingualLongName.get("RU"));
    		}
    		if (multilingualLongName.containsKey("ZH")) {
    			figis.setLong_name_c((String) multilingualLongName.get("ZH"));
    		}
    	}
    	
    	if (multilingualOfficialName != null && multilingualOfficialName.size() > 0) {
    		if (multilingualOfficialName.containsKey("EN")) {
    			figis.setOfficial_name_e((String) multilingualOfficialName.get("EN"));
    		}
    		if (multilingualOfficialName.containsKey("FR")) {
    			figis.setOfficial_name_f((String) multilingualOfficialName.get("FR"));
    		}
    		if (multilingualOfficialName.containsKey("ES")) {
    			figis.setOfficial_name_s((String) multilingualOfficialName.get("ES"));
    		}
    		if (multilingualOfficialName.containsKey("AR")) {
    			figis.setOfficial_name_a((String) multilingualOfficialName.get("AR"));
    		}
    		if (multilingualOfficialName.containsKey("RU")) {
    			figis.setOfficial_name_r((String) multilingualOfficialName.get("RU"));
    		}
    		if (multilingualOfficialName.containsKey("ZH")) {
    			figis.setOfficial_name_c((String) multilingualOfficialName.get("ZH"));
    		}
    	}
    	
    	if (multilingualShortDescription != null && multilingualShortDescription.size() > 0) {
    		if (multilingualShortDescription.containsKey("EN")) {
    			figis.setShort_description_e((String) multilingualShortDescription.get("EN"));
    		}
    		if (multilingualShortDescription.containsKey("FR")) {
    			figis.setShort_description_f((String) multilingualShortDescription.get("FR"));
    		}
    		if (multilingualShortDescription.containsKey("ES")) {
    			figis.setShort_description_s((String) multilingualShortDescription.get("ES"));
    		}
    		if (multilingualShortDescription.containsKey("AR")) {
    			figis.setShort_description_a((String) multilingualShortDescription.get("AR"));
    		}
    		if (multilingualShortDescription.containsKey("RU")) {
    			figis.setShort_description_r((String) multilingualShortDescription.get("RU"));
    		}
    		if (multilingualShortDescription.containsKey("ZH")) {
    			figis.setShort_description_c((String) multilingualShortDescription.get("ZH"));
    		}
    	}
    	
    	return figis;
	}
	
	private List<FIGISObject> flatHierarchy(List<FIGISObject> list) {
		List<FIGISObject> returnList = new ArrayList<FIGISObject>();
		if (list != null && list.size() > 0) {
			for (FIGISObject fi : list) {
				returnList.add(fi);
				if (fi.getHierarchy() != null && fi.getHierarchy().size() > 0) {
					for (FIGISObject fii : this.flatHierarchy(fi.getHierarchy())) {
						returnList.add(fii);
					}
				}
			}
		}
		return returnList;
	}
	
	private List<FIGISObject> cleanUpList(List<FIGISObject> source) {
    	List<FIGISObject> cleanedUpList = new ArrayList<FIGISObject>();
    	List<Integer> indexToRemove = new ArrayList<Integer>();
    	for (int i = 0; i < source.size(); i++) {
			String url1 = source.get(i).getUrl();
			inner:
			for (int j = i+1; j < source.size(); j++) {
				String url2 = source.get(j).getUrl();
				if (url1.equalsIgnoreCase(url2)) {
					indexToRemove.add(j);
					break inner;
				}
			}
		}
		int x = 0;
		for (FIGISObject fo : source) {
			if (!indexToRemove.contains(x)) {
				cleanedUpList.add(fo);
			}
			x += 1;
		}
		return cleanedUpList;
    }
	
}
