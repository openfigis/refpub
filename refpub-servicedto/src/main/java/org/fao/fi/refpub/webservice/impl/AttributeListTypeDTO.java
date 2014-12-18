package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.webservice.AttributeList;

public class AttributeListTypeDTO {
	public static AttributeList create(List<GenericType> list) {
		AttributeList ret = new AttributeList();
		ret.setCountRecord(list.size());
		for (GenericType gt : list) {
			ret.getAttributes().add(AttributeTypeDTO.create(gt.getKey(), gt.getValue()));
		}
		return ret;
	}
	
	public static AttributeList error() {
		AttributeList ret = new AttributeList();
		ret.getAttributes().add(AttributeTypeDTO.create("status", "error"));
		return ret;
	}
	public static AttributeList errorWithMessage(String message) {
		AttributeList ret = new AttributeList();
		ret.getAttributes().add(AttributeTypeDTO.create("error", message));
		return ret;
	}
}
