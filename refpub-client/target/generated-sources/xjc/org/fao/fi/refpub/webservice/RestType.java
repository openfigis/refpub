//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 09:51:58 AM CET 
//


package org.fao.fi.refpub.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fao.org/fi/refpub/webservice}atomLink">
 *       &lt;sequence>
 *         &lt;element name="attribute" type="{http://www.fao.org/fi/refpub/webservice}atomLink"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RestType", propOrder = {
    "attribute"
})
@XmlSeeAlso({
    CodeList.class,
    Code.class,
    TreasureType.class,
    CodeSystemList.class
})
public class RestType
    extends AtomLink
{

    @XmlElement(required = true)
    protected AtomLink attribute;

    /**
     * Gets the value of the attribute property.
     * 
     * @return
     *     possible object is
     *     {@link AtomLink }
     *     
     */
    public AtomLink getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link AtomLink }
     *     
     */
    public void setAttribute(AtomLink value) {
        this.attribute = value;
    }

}