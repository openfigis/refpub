package org.fao.fi.refpub.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import org.fao.fi.refpub.dao.objects.ClassInitXML;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;
import org.fao.fi.refpub.persistence.PersistenceServiceInterface;

@RequestScoped
public class RefPubImplementation implements RefPubInterface {

	@Produces
	public List<RefPubObject> getCategories() {
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();
		return ps.getCategories();
	}

	public List<RefPubObject> getObjects(String category) {
		
		int numericCategory = Integer.parseInt(category);
		PersistenceServiceInterface ps = new PersistenceServiceImplementation();

		ClassInitXML ci = ps.getRefTable(numericCategory);
		
		return ps.getObjects(category, "FIGIS." + ci.getRef_table());

	}

}
