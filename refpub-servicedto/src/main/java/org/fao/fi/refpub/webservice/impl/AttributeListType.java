package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.webservice.AttributesDTO;

public class AttributeListType {
	public static AttributesDTO create(List<GenericType> list) {
		AttributesDTO ret = new AttributesDTO();
		ret.setCountRecords(list.size());
		for (GenericType gt : list) {
			ret.getAttributes().add(AttributeType.create(gt.getKey(), gt.getValue()));
		}
		return ret;
	}
	
	public static AttributesDTO error() {
		AttributesDTO ret = new AttributesDTO();
		ret.getAttributes().add(AttributeType.create("status", "error"));
		return ret;
	}
	public static AttributesDTO errorWithMessage(String message) {
		AttributesDTO ret = new AttributesDTO();
		ret.getAttributes().add(AttributeType.create("error", message));
		return ret;
	}
}
