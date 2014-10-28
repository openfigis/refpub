package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.webservice.TextTypeValuePairDTO;

public class AttributeType {
	public static TextTypeValuePairDTO create (String key, String value) {
		TextTypeValuePairDTO tvp = new TextTypeValuePairDTO();
		tvp.setName(key);
		tvp.setValue(value);
		return tvp;
	}
}
