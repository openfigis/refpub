package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.webservice.TextTypeValuePair;

public class AttributeTypeDTO {
	public static TextTypeValuePair create (String key, String value) {
		TextTypeValuePair tvp = new TextTypeValuePair();
		tvp.setName(key);
		tvp.setValue(value);
		return tvp;
	}
}
