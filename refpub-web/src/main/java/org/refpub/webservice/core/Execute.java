package org.refpub.webservice.core;

import java.io.StringWriter;
import java.util.Iterator;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.fao.fi.refpub.webservice.AttributeList;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.refpub.cache.CacheImplementation;
import org.refpub.cache.CacheInterface;



public class Execute {
	
	private UriInfo uri;
	private String mediatype = MediaType.APPLICATION_JSON;
	private String ERROR = "Error Executing Request.";
	
	public Execute() {};
	public Execute(UriInfo uri) {
		this.uri = uri;
	};
	public Execute(UriInfo uri, String mediatype) {
		this.uri = uri;
		if (mediatype != null) {
			this.mediatype = mediatype;
		}
	};
	
	public Object run() {
		return ERROR;
	}
	
	public Object error(Exception error) {
		return ERROR;
	}
	
	public Response execute() {
		CacheInterface cache = new CacheImplementation();
		String key = this.buildFullUri();
		if (uri != null) {	
			String cachedResponse = cache.getCachedValue(key);
			if (cachedResponse != null) {
				System.out.println("Reading From Cache....");
				return Response.ok(cachedResponse)
						.header(HttpHeaders.CONTENT_TYPE, mediatype)
						.build();
			}
		}
		
		try {
			String result = this.marshaller(this.run());
			cache.writeCache(key, result);
			return Response.ok(result)
					.header(HttpHeaders.CONTENT_TYPE, mediatype)
					.build();
		} catch (Exception ex) {
			return Response.ok(this.error(ex))
					.header(HttpHeaders.CONTENT_TYPE, mediatype)
					.build();
		}
	}
	
	private String buildFullUri() {
		if (uri != null) {
			MultivaluedMap<String, String> uriParameters = uri.getQueryParameters();
			Iterator<String> it = uriParameters.keySet().iterator();
			String parameters = "";
			while (it.hasNext()) {
				String k = it.next();
				String v = uriParameters.getFirst(k);
				parameters += k + "=" + v + "&";
			}
			if (! "".equals(parameters)) {
				parameters = "?" + parameters;
				parameters = parameters.replace(parameters.substring(parameters.length()-1), "");
			}
			return uri.getAbsolutePath().getRawPath() + parameters;
		} else {
			return null;
		}
	}
	
	
	/* Marshallers */
	private String marshaller(Object obj) throws JAXBException {
		if ("application/json".equalsIgnoreCase(mediatype)) {
			return this.JSONMarhaller(obj);
		} else {
			return this.XMLMarhaller(obj);
		}
	}
	
	private String JSONMarhaller(Object obj) throws JAXBException {
		
		final StringWriter w = new StringWriter();
		if (obj instanceof Concept){
			JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {Concept.class}, null);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
	        m.setProperty(MarshallerProperties.MEDIA_TYPE, mediatype);
	        m.marshal((Concept) obj, w);
		} else if (obj instanceof ConceptList){
			JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {ConceptList.class}, null);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
	        m.setProperty(MarshallerProperties.MEDIA_TYPE, mediatype);
	        m.marshal((ConceptList) obj, w);
		} else if (obj instanceof AttributeList){
			JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {AttributeList.class}, null);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
	        m.setProperty(MarshallerProperties.MEDIA_TYPE, mediatype);
	        m.marshal((AttributeList) obj, w);
		}
		return w.toString();
	}
	
	private String XMLMarhaller(Object obj) throws JAXBException {
		
		final StringWriter w = new StringWriter();
		if (obj instanceof Concept){
			Marshaller m = JAXBContext.newInstance(Concept.class).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			m.marshal((Concept) obj, w);
		} else if (obj instanceof ConceptList) {
			Marshaller m = JAXBContext.newInstance(ConceptList.class).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			m.marshal((ConceptList) obj, w);
		} else if (obj instanceof AttributeList){
			Marshaller m = JAXBContext.newInstance(AttributeList.class).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
	        m.marshal((AttributeList) obj, w);
		}
		return w.toString();
	}

}
