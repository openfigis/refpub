package org.apache.solr.handler.dataimport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	    		
	    		List<FIGISObject> list = this.getObjects(url2);
	    		for (FIGISObject o : list) {
	    			figis_objects.add(o);
	    		}
			}
		}
		System.out.println("INIT DONE!");
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
    			
    			row.put("concept", obj.getConcept());
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
		//System.out.println("RefPubDataImportHandler::: " + url);
    	try {
    		InputStream in = new URL( url ).openStream();
            String genreJson = IOUtils.toString(in);
            JSONObject conceptJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            JSONArray conceptArray = (JSONArray) conceptJsonObject.get("concept");
            List<Map<String, String>> returnVal = new ArrayList<Map<String, String>>();
            
            for (int i = 0; i < conceptArray.size(); i++) {
	            JSONObject conceptArrayLinks = (JSONObject) conceptArray.get(i);
	            
	            JSONArray arrayOfConceptsLinks = (JSONArray) conceptArrayLinks.get("link");
	            
	            JSONObject c = (JSONObject) arrayOfConceptsLinks.get(0);
	            
	            Map<String,String> t = new HashMap<String, String>();
	            t.put((String) c.get("rel"), (String) c.get("value"));
	            returnVal.add(t);
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
            JSONArray conceptArray = (JSONArray) conceptJsonObject.get("concept");
            for (int i = 0; i < conceptArray.size(); i++) {
            	JSONObject ca = (JSONObject) conceptArray.get(i);
            	JSONObject multilingualLongName = (JSONObject) ca.get("multilingualLongName");
            	JSONObject multilingualName = (JSONObject) ca.get("multilingualName");
            	JSONObject multilingualFullName = (JSONObject) ca.get("multilingualFullName");
            	JSONObject multilingualOfficialName = (JSONObject) ca.get("multilingualOfficialName");
            	JSONObject multilingualShortDescription = (JSONObject) ca.get("multilingualShortDescription");
            	JSONArray link = (JSONArray) ca.get("link");
            	FIGISObject figis = new FIGISObject();
            	for (int j = 0; j < link.size(); j++) {
            		JSONObject la = (JSONObject) link.get(j);
            		figis.setConcept((String) la.get("rel"));
            		figis.setUrl((String) la.get("value"));
            	}
             	
            	if (multilingualName != null && multilingualName.get("value") != null) {
            		JSONArray arr = (JSONArray) multilingualName.get("value");
            		for (int j = 0; j < arr.size(); j++) {
            			JSONObject caa = (JSONObject) arr.get(j);
            			String lang = (String) caa.get("lang");
            			if (lang.equalsIgnoreCase("ar")) {
            				figis.setName_a((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("en")) {
            				figis.setName_e((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("fr")) {
            				figis.setName_f((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("es")) {
            				figis.setName_s((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("ru")) {
            				figis.setName_r((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("zh")) {
            				figis.setName_c((String) caa.get("value"));
            			}
            		}
            	}
            	
            	if (multilingualFullName != null && multilingualFullName.get("value") != null) {
            		JSONArray arr = (JSONArray) multilingualFullName.get("value");
            		for (int j = 0; j < arr.size(); j++) {
            			JSONObject caa = (JSONObject) arr.get(j);
            			String lang = (String) caa.get("lang");
            			if (lang.equalsIgnoreCase("ar")) {
            				figis.setFull_name_a((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("en")) {
            				figis.setFull_name_e((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("fr")) {
            				figis.setFull_name_f((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("es")) {
            				figis.setFull_name_s((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("ru")) {
            				figis.setFull_name_r((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("zh")) {
            				figis.setFull_name_c((String) caa.get("value"));
            			}
            		}
            	}
            	
            	if (multilingualLongName != null && multilingualLongName.get("value") != null) {
            		JSONArray arr = (JSONArray) multilingualLongName.get("value");
            		for (int j = 0; j < arr.size(); j++) {
            			JSONObject caa = (JSONObject) arr.get(j);
            			String lang = (String) caa.get("lang");
            			if (lang.equalsIgnoreCase("ar")) {
            				figis.setLong_name_a((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("en")) {
            				figis.setLong_name_e((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("fr")) {
            				figis.setLong_name_f((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("es")) {
            				figis.setLong_name_s((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("ru")) {
            				figis.setLong_name_r((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("zh")) {
            				figis.setLong_name_c((String) caa.get("value"));
            			}
            		}
            	}
            	
            	if (multilingualOfficialName != null && multilingualOfficialName.get("value") != null) {
            		JSONArray arr = (JSONArray) multilingualOfficialName.get("value");
            		for (int j = 0; j < arr.size(); j++) {
            			JSONObject caa = (JSONObject) arr.get(j);
            			String lang = (String) caa.get("lang");
            			if (lang.equalsIgnoreCase("ar")) {
            				figis.setOfficial_name_a((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("en")) {
            				figis.setOfficial_name_e((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("fr")) {
            				figis.setOfficial_name_f((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("es")) {
            				figis.setOfficial_name_s((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("ru")) {
            				figis.setOfficial_name_r((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("zh")) {
            				figis.setOfficial_name_c((String) caa.get("value"));
            			}
            		}
            	}
            	
            	if (multilingualShortDescription != null && multilingualShortDescription.get("value") != null) {
            		JSONArray arr = (JSONArray) multilingualShortDescription.get("value");
            		for (int j = 0; j < arr.size(); j++) {
            			JSONObject caa = (JSONObject) arr.get(j);
            			String lang = (String) caa.get("lang");
            			if (lang.equalsIgnoreCase("ar")) {
            				figis.setShort_description_a((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("en")) {
            				figis.setShort_description_e((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("fr")) {
            				figis.setShort_description_f((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("es")) {
            				figis.setShort_description_s((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("ru")) {
            				figis.setShort_description_r((String) caa.get("value"));
            			}
            			if (lang.equalsIgnoreCase("zh")) {
            				figis.setShort_description_c((String) caa.get("value"));
            			}
            		}
            	}
            	
            	returnList.add(figis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return returnList;
    }
	
	
}
