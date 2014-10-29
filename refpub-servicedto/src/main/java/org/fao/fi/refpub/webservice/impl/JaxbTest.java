package org.fao.fi.refpub.webservice.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.SchemaFactory;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class JaxbTest {
	/*private static Marshaller marshaller = null;
	private static final String ERROR = "There was a problem creating a JAXBContext object for serializing the object to XML.";

	private static final String SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

	String UGLY_STRING = " xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"";
	
	
	public String marshalToString(TestRefPub testRefPubObject) {
		try {
			this.setMarshaller();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringWriter sw = new StringWriter();
		try {
			marshaller.marshal(testRefPubObject, sw);
		} catch (JAXBException e) {
			throw new RuntimeException("There was a problem creating a the figis xml from a java object", e);
		}

		String documentString = sw.toString();
		documentString = documentString.replaceAll(UGLY_STRING, "");
		return documentString;
	}
	
	private void setMarshaller() throws Exception {
		try {

			// For some reason the jsr173 jar does not have anymore the
			// W3C_XML_SCHEMA_NS_URI. Therefore
			// SchemaFactory sf =
			// SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			SchemaFactory sf = SchemaFactory.newInstance(SCHEMA_LANGUAGE);

			JAXBContext context = JAXBContext.newInstance(TestRefPub.class);
			marshaller = context.createMarshaller();
			//marshaller.setEventHandler(new FimesValidationEventHandler());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			//marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
			//		"http://localhost:8080/refpub-web/refpub.xsd");

			// is this one needed, since JAXB_SCHEMA_LOCATION is also set?
			//Schema schema = sf.newSchema(new URL("http://localhost:8080/refpub-web/refpub.xsd"));
			//marshaller.setSchema(schema);

			// avoid jaxb from escaping characters (nesessary to handle properly
			// CDATA)
			marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
				@Override
				public void escape(char[] ch, int start, int length, boolean isAttVal, Writer out) throws IOException {
					out.write(ch, start, length);
				}
			});

			// this property works fine with jdk1.6.0_16
			//marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new FimesNamespacePrefixMapper());

		} catch (JAXBException e) {
			throw new Exception(ERROR, e);
		}
	}*/

}
