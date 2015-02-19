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
 * <p>Java class for TreasureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreasureType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.fao.org/fi/refpub/webservice}RestType">
 *       &lt;sequence>
 *         &lt;element name="multilingualName" type="{http://www.fao.org/fi/refpub/webservice}MultilingualType"/>
 *         &lt;element name="multilingualFullName" type="{http://www.fao.org/fi/refpub/webservice}MultilingualType"/>
 *         &lt;element name="multilingualLongName" type="{http://www.fao.org/fi/refpub/webservice}MultilingualType"/>
 *         &lt;element name="multilingualOfficialName" type="{http://www.fao.org/fi/refpub/webservice}MultilingualType"/>
 *         &lt;element name="multilingualShortDescription" type="{http://www.fao.org/fi/refpub/webservice}MultilingualType"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scientific_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vessel_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codeList" type="{http://www.fao.org/fi/refpub/webservice}CodeListListType"/>
 *         &lt;element name="groups" type="{http://www.fao.org/fi/refpub/webservice}groupsType"/>
 *         &lt;element name="attributes" type="{http://www.fao.org/fi/refpub/webservice}attributesType"/>
 *         &lt;element name="attr" type="{http://www.fao.org/fi/refpub/webservice}attrList"/>
 *         &lt;element name="hierarchy" type="{http://www.fao.org/fi/refpub/webservice}HierarchyList"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreasureType", propOrder = {
    "multilingualName",
    "multilingualFullName",
    "multilingualLongName",
    "multilingualOfficialName",
    "multilingualShortDescription",
    "name",
    "scientificName",
    "vesselName",
    "codeList",
    "groups",
    "attributes",
    "attr",
    "hierarchy"
})
@XmlSeeAlso({
    CodeSystem.class,
    Concept.class
})
public class TreasureType
    extends RestType
{

    @XmlElement(required = true)
    protected MultilingualType multilingualName;
    @XmlElement(required = true)
    protected MultilingualType multilingualFullName;
    @XmlElement(required = true)
    protected MultilingualType multilingualLongName;
    @XmlElement(required = true)
    protected MultilingualType multilingualOfficialName;
    @XmlElement(required = true)
    protected MultilingualType multilingualShortDescription;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "scientific_name", required = true)
    protected String scientificName;
    @XmlElement(name = "vessel_name", required = true)
    protected String vesselName;
    @XmlElement(required = true)
    protected CodeListListType codeList;
    @XmlElement(required = true)
    protected GroupsType groups;
    @XmlElement(required = true)
    protected AttributesType attributes;
    @XmlElement(required = true)
    protected AttrList attr;
    @XmlElement(required = true)
    protected HierarchyList hierarchy;

    /**
     * Gets the value of the multilingualName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualType }
     *     
     */
    public MultilingualType getMultilingualName() {
        return multilingualName;
    }

    /**
     * Sets the value of the multilingualName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualType }
     *     
     */
    public void setMultilingualName(MultilingualType value) {
        this.multilingualName = value;
    }

    /**
     * Gets the value of the multilingualFullName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualType }
     *     
     */
    public MultilingualType getMultilingualFullName() {
        return multilingualFullName;
    }

    /**
     * Sets the value of the multilingualFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualType }
     *     
     */
    public void setMultilingualFullName(MultilingualType value) {
        this.multilingualFullName = value;
    }

    /**
     * Gets the value of the multilingualLongName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualType }
     *     
     */
    public MultilingualType getMultilingualLongName() {
        return multilingualLongName;
    }

    /**
     * Sets the value of the multilingualLongName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualType }
     *     
     */
    public void setMultilingualLongName(MultilingualType value) {
        this.multilingualLongName = value;
    }

    /**
     * Gets the value of the multilingualOfficialName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualType }
     *     
     */
    public MultilingualType getMultilingualOfficialName() {
        return multilingualOfficialName;
    }

    /**
     * Sets the value of the multilingualOfficialName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualType }
     *     
     */
    public void setMultilingualOfficialName(MultilingualType value) {
        this.multilingualOfficialName = value;
    }

    /**
     * Gets the value of the multilingualShortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualType }
     *     
     */
    public MultilingualType getMultilingualShortDescription() {
        return multilingualShortDescription;
    }

    /**
     * Sets the value of the multilingualShortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualType }
     *     
     */
    public void setMultilingualShortDescription(MultilingualType value) {
        this.multilingualShortDescription = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the scientificName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Sets the value of the scientificName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScientificName(String value) {
        this.scientificName = value;
    }

    /**
     * Gets the value of the vesselName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVesselName() {
        return vesselName;
    }

    /**
     * Sets the value of the vesselName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVesselName(String value) {
        this.vesselName = value;
    }

    /**
     * Gets the value of the codeList property.
     * 
     * @return
     *     possible object is
     *     {@link CodeListListType }
     *     
     */
    public CodeListListType getCodeList() {
        return codeList;
    }

    /**
     * Sets the value of the codeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeListListType }
     *     
     */
    public void setCodeList(CodeListListType value) {
        this.codeList = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link GroupsType }
     *     
     */
    public GroupsType getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupsType }
     *     
     */
    public void setGroups(GroupsType value) {
        this.groups = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link AttributesType }
     *     
     */
    public AttributesType getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributesType }
     *     
     */
    public void setAttributes(AttributesType value) {
        this.attributes = value;
    }

    /**
     * Gets the value of the attr property.
     * 
     * @return
     *     possible object is
     *     {@link AttrList }
     *     
     */
    public AttrList getAttr() {
        return attr;
    }

    /**
     * Sets the value of the attr property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrList }
     *     
     */
    public void setAttr(AttrList value) {
        this.attr = value;
    }

    /**
     * Gets the value of the hierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link HierarchyList }
     *     
     */
    public HierarchyList getHierarchy() {
        return hierarchy;
    }

    /**
     * Sets the value of the hierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HierarchyList }
     *     
     */
    public void setHierarchy(HierarchyList value) {
        this.hierarchy = value;
    }

}
