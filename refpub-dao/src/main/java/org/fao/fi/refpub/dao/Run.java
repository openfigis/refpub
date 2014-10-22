package org.fao.fi.refpub.dao;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;

/**
 * Hello world!
 *
 */
public class Run 
{
    public static void main( String[] args )
    {
        PersistenceServiceImplementation ii = new PersistenceServiceImplementation();
        List<RefPubObject> list = ii.getCategories();
        for (RefPubObject mc : list) {
        	System.out.println(mc.getName_e());
        }
    }
}
