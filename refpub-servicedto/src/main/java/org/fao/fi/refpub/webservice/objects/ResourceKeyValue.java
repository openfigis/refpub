package org.fao.fi.refpub.webservice.objects;

public class ResourceKeyValue {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public ResourceKeyValue(String key, String value) {
		this.setKey(key);
		this.setValue(value);
	}
}
