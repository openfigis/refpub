package org.fao.fi.refpub.webservice.impl;

public class ResourceUrl {

	public static String create(String type) {

		// return "http://hqldvfigis2:8684/refpub-web/rest/" + type + "/";
		return "http://localhost:8080/refpub-web/rest/" + type + "/";
	}

}
