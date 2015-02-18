package org.refpub.client;

import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       RefPubClient client = new RefPubClient();
       ConceptList list = client.getMainListOfConcepts();
       
       Concept concept = client.getObject("Country", "ID", "2");
       Concept codesystems = client.getCodeSystemsForConcept("Country");
       ConceptList metaGroups = client.getMetaGroupsForConcept("Country");
       ConceptList subGroups = client.getSubGroupsForConceptAndMeta("Country", "recofi_stat_country");
       ConceptList group = client.getGroup("Country", "recofi_stat_country", "10007");
       
       System.out.println("Done!");
    }
}
