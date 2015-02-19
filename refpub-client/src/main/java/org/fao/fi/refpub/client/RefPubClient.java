package org.fao.fi.refpub.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.refpub.webservice.AttributeList;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.SystemError;
import org.fao.fi.refpub.webservice.beans.RefPubInterface;

public class RefPubClient implements RefPubInterface{
	
	UriInfo URI;
	private static String CONFIG_FILE;
	private static String REFPUB_URI;
	
	public RefPubClient (String refpub_host) throws Exception {
		if (refpub_host == null || !refpub_host.toLowerCase().startsWith("http")) {
			throw new Exception("RefPub host must be a valid URI");
		}
		RefPubClient.REFPUB_URI = refpub_host;
	}

	@Override
	public void setUrl(UriInfo uri) {
		if (uri != null) {
			URI = uri;
		} else {
			URI = null;
		}
	}

	@Override
	public void setPropertiesFile(String propertiesFile) {
		RefPubClient.CONFIG_FILE = propertiesFile;
		
	}

	@Override
	public ConceptList getAllConcept(String count, String page) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("count", count);
		params.put("page", page);
		String parameters = this.buildParameters(params);

		String XML = "";
		
		try {
			XML = this.getXML(REFPUB_URI + "concept/", parameters);
			JAXBContext jc = JAXBContext.newInstance( ConceptList.class );
		    Unmarshaller u = jc.createUnmarshaller();
		    return (ConceptList) u.unmarshal( new StringReader(XML) );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Concept getConcept(String concept, String count, String page) {
		return null;		
	}

	@Override
	public ConceptList getAllObjectByConcept(String concept, String count,
			String page) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("count", count);
		params.put("page", page);
		String parameters = this.buildParameters(params);
		String XML = "";
		
		try {
			XML = this.getXML(REFPUB_URI + "concept/" + concept, parameters);
			JAXBContext jc = JAXBContext.newInstance( ConceptList.class );
		    Unmarshaller u = jc.createUnmarshaller();
		    return (ConceptList) u.unmarshal( new StringReader(XML) );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Concept getObject(String concept, String codesystem, String code) {
		if (concept == null || codesystem == null || code == null) {
			try {
				throw new Exception ("Parameters can't be null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String XML = "";
		
		try {
			XML = this.getXML(REFPUB_URI + "concept/" + concept + "/codesystem/" + codesystem + "/code/" + code, "");
			JAXBContext jc = JAXBContext.newInstance( ConceptList.class );
		    Unmarshaller u = jc.createUnmarshaller();
		    return (Concept) u.unmarshal( new StringReader(XML) );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Concept getObject(String concept, String code) {
		return null;
	}

	@Override
	public Concept getAttributeForObject(String concept, String codesystem,
			String code, String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concept getAllCodeSystem(String count, String page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptList getObjectByCodeSystem(String concept, String codesystem,
			String count, String page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concept getAllCodeSystemByConcept(String concept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptList getGroups(String concept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptList getGroupMain(String concept, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptList getGroup(String Concept, String filter, String group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConceptList getSubGroups(String Concept, String filter,
			String group, String subGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeList getAllAttributesForConcept(String concept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeList getAllAttributesForConceptAndCodesystem(
			String concept, String codesystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemError error(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	private String buildParameters(HashMap<String, String> parameters) {
		String returnParameters = "";
		boolean found = false;
		for (Entry<String, String> e : parameters.entrySet()) {
		    String key    = e.getKey();
		    String value  = e.getValue();   
		    if (value != null && !value.equalsIgnoreCase("")) {
		    	if (!found) { 
		    		found = true;
		    		returnParameters += "?";
		    	} else {
		    		returnParameters += "&";
		    	}
		    	returnParameters += key + "=" + value;
		    } else if (key.toLowerCase().equals("count") && value == null) {
		    	if (!found) { 
		    		found = true;
		    		returnParameters += "?";
		    	} else {
		    		returnParameters += "&";
		    	}
		    	returnParameters += key + "=all";
		    }
		}
		return returnParameters;
	}
	
	private String setXMLVersion (String URL) {
		if (!URL.toLowerCase().endsWith("/xml")) {
			if (!URL.toLowerCase().endsWith("/")) {
				return URL + "/xml";
			} else {
				return URL + "xml";
			}
		} else {
			return URL;
		}
	}
	
	private String getXML(String URL, String parameters) throws IOException {
		URL con = new URL(this.setXMLVersion(URL) + parameters);
        URLConnection yc = con.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        String XML = "";
        while ((inputLine = in.readLine()) != null) 
            XML += inputLine;
        in.close();
        
        return XML;
	}
}
