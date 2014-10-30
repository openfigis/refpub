package org.fao.fi.refpub.dao.objects;

public class URI {
	private String host;
	private String port;
	private String path;
	private String fullURI;
	private int page;
	private int count;
	private boolean all;
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
	}
}
