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
		if (uri == null) {
			uri = LinkRelDTO.buildDefaultURI();
		}
		LinkRel relObj = new LinkRel();
		relObj.setRel(rel);
		if ("self".equalsIgnoreCase(rel)) {
			relObj.setValue(uri.getFullURI());
			return relObj;
		}
		if ("next".equalsIgnoreCase(rel)) {
			if (uri.isAll()) {
				return relObj;
			}
			String page = Integer.toString(uri.getPage() + 1);
			relObj.setValue(uri.getFullURI().split("\\?")[0] + "?page=" + page + "&count=" + uri.getCount());
			return relObj;
		}
		if ("prev".equalsIgnoreCase(rel)) {
			if (uri.isAll()) {
				return relObj;
			}
			String page = Integer.toString(uri.getPage() - 1);
			relObj.setValue(uri.getFullURI().split("\\?")[0] + "?page=" + page + "&count=" + uri.getCount());
			return relObj;
		}
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
		String pathWithoutParameters = path.split("\\?")[0].replaceAll("/\\z", ""); 
		if (pathWithoutParameters.toLowerCase().endsWith("csv")) {
			return "csv";
		} else if (pathWithoutParameters.toLowerCase().endsWith("json")) {
			return "json";
		} else if (pathWithoutParameters.toLowerCase().endsWith("xml")) {
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
					path += chunk.getValue().replaceAll(" ", "%20") + "/";
				}
			}
		}
		return path;
	}
	
	private static URI buildDefaultURI() {
		URI uri = new URI();
		uri.setCount(100);
		uri.setFullURI("http://localhost/");
		uri.setHost("localhost");
		uri.setPage(1);
		uri.setPath("/");
		uri.setPort("80");
		return uri;
	}

}
