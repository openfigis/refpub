package org.fao.fi.refpub.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;

public abstract interface PersistenceServiceInterface {
	List<RefPubObject> getCategories();
	List<RefPubObject> getObjects(@Param("meta") String id, @Param("table") String table);
	ClassInitXML getRefTable(int id);
}
