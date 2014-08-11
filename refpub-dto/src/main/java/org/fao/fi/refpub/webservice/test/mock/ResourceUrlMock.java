package org.fao.fi.refpub.webservice.test.mock;

public class ResourceUrlMock {

	public static String create(String type) {

		return "http://localhost:8080/refpub-web/rest/" + type + "/";
	}

}
