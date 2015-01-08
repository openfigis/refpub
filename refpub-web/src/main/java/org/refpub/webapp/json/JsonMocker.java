package org.refpub.webapp.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;


public class JsonMocker {

	private String TO_MOCK;
	private ArrayList<String> TAG_TO_MOCK;
	
	public JsonMocker(String source) {
		this.TO_MOCK = source;
		this.TAG_TO_MOCK = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

		{
		    add("attr");
		}};
	}
	
	public String mock() {
		for (String tag : this.TAG_TO_MOCK) {
			this.TO_MOCK = this.rebuildJson(tag);
		}
		return this.TO_MOCK;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String rebuildJson(String tag) {
		try {
			JSONParser parser = new JSONParser();

			ContainerFactory orderedKeyFactory = new ContainerFactory()
			{
				@Override
				public List creatArrayContainer() {
			      return new LinkedList();
			    }

			    public Map createObjectContainer() {
			      return new LinkedHashMap();
			    }

			};

			Object obj =  parser.parse(TO_MOCK, orderedKeyFactory);  
			LinkedHashMap map = (LinkedHashMap)obj;
			LinkedHashMap concept = (LinkedHashMap) map.get("concept");
			
			LinkedHashMap tagToReplace = (LinkedHashMap) concept.get(tag);
			LinkedList value = (LinkedList) tagToReplace.get("value");
			
			LinkedHashMap newAttrs = new LinkedHashMap();
			for (int i = 0; i < value.size(); i++) {
				LinkedHashMap linkedHashMap = (LinkedHashMap) value.get(i);
				newAttrs.put(linkedHashMap.get("name"), linkedHashMap.get("value"));
			}
			
			value.clear();
			value.add(newAttrs);
			
			tagToReplace.remove("value");
			tagToReplace.put("value", value);
			
			concept.replace(tag, tagToReplace);
	
			return JSONValue.toJSONString(concept);
		} catch (Exception e) {
			
		}
		
		return this.TO_MOCK;
	}
	
}
