package org.fao.fi.refpub.beans;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubObject;

public interface RefPubInterface {
	List<RefPubObject> getCategories();
	List<RefPubObject> getObjects(String category);
}
