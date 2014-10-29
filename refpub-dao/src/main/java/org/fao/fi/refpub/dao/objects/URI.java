package org.fao.fi.refpub.dao.objects;

public class URI {
	private String host;
	private String port;
	private String path;
	private String fullURI;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFullURI() {
		return fullURI;
	}
	public void setFullURI(String fullURI) {
		this.fullURI = fullURI;
	}
}
