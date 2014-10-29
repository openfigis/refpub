package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.URI;
import org.fao.fi.refpub.webservice.LinkRel;
import org.fao.fi.refpub.webservice.objects.ResourceKeyValue;

public class LinkRelDTO {
	
	public static LinkRel create(RefPubObject object, List<ResourceKeyValue> urlChunks, String rel) {
		return(build(object.getCurrentURI(), urlChunks, rel));
	}
	
	public static LinkRel create(RefPubConcept object, List<ResourceKeyValue> urlChunks, String rel) {
		return(build(object.getCurrentURI(), urlChunks, rel));
	}
	
	private static LinkRel build(URI uri, List<ResourceKeyValue> urlChunks, String rel) {
		LinkRel relObj = new LinkRel();
		relObj.setRel(rel);
		if (uri == null) {
			relObj.setValue(getPathFromChunks(urlChunks));
			return relObj;
		}
		
		String URL = getLinkMethod(uri.getFullURI()) +
				uri.getHost() + ":" + uri.getPort() +
					 uri.getPath();
		
		URL += getPathFromChunks(urlChunks);
		URL += getOutputVersion(uri.getFullURI());
		
		relObj.setValue(URL);
		return relObj;
	}
	
	private static String getOutputVersion(String path) {
		if (path.toLowerCase().endsWith("csv")) {
			return "csv";
		} else if (path.toLowerCase().endsWith("json")) {
			return "json";
		} else if (path.toLowerCase().endsWith("xml")) {
			return "xml";
		} else {
			return "json";
		}
	}
	
	private static String getLinkMethod(String path) {
		if (path.toLowerCase().startsWith("http://")) {
			return "http://";
		} else if (path.toLowerCase().startsWith("https://")) {
			return "https://";
		} else {
			return "http://";
		}
	}
	
	private static String getPathFromChunks(List<ResourceKeyValue> urlChunks) {
		String path = "";
		if (urlChunks != null) {
			for (ResourceKeyValue chunk : urlChunks) {
				if (chunk.getKey() != null && !chunk.getKey().trim().equals("")) {
					path += chunk.getKey() + "/";
				}
				if (chunk.getValue() != null && !chunk.getValue().trim().equals("")) {
					path += chunk.getValue() + "/";
				}
			}
		}
		return path;
	}

}
