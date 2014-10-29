package org.fao.fi.refpub.webservice.objects.customization.jaxb;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/*
 * This class extends all the JaxB generated class to let the JaxB Jackson provider
 * to generate the Json without empty/null values
 * 
 * This class can be extended with other annotations/functionalities in the future.
 */

@JsonInclude(Include.NON_EMPTY)
@XmlTransient
public class JaxbSuperClass {}
