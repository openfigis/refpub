package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.webservice.Attributes;

public class AttributeListTypeDTO {
	public static Attributes create(List<GenericType> list) {
		Attributes ret = new Attributes();
		ret.setCountRecords(list.size());
		for (GenericType gt : list) {
			ret.getAttributes().add(AttributeTypeDTO.create(gt.getKey(), gt.getValue()));
		}
		return ret;
	}
	
	public static Attributes error() {
		Attributes ret = new Attributes();
		ret.getAttributes().add(AttributeTypeDTO.create("status", "error"));
		return ret;
	}
	public static Attributes errorWithMessage(String message) {
		Attributes ret = new Attributes();
		ret.getAttributes().add(AttributeTypeDTO.create("error", message));
		return ret;
	}
}
