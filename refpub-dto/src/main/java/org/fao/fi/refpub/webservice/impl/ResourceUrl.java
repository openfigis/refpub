package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class ResourceUrl {

	/*public static String create(String type) {
		return "http://localhost:8080/refpub-web/rest/" + type + "/";
	}
	
	public static String create(String type, String second) {
		return "http://localhost:8080/refpub-web/rest/" + type + "/##TYPE##/" + second + "/";
		
	}*/
	
	public static String create(List<ResourceKeyValue> chunks) {
		
		String basePath = "http://localhost:8080/refpub-web/rest/";
		for (ResourceKeyValue chunk : chunks) {
			basePath += chunk.getKey() + "/" + chunk.getValue() + "/";
		}
		return basePath;
	}

}
