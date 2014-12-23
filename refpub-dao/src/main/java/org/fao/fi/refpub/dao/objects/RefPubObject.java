package org.fao.fi.refpub.dao.objects;

import java.util.HashMap;
import java.util.List;

public class RefPubObject {
	
	private String PKID;
	private String META;
	
	private String NAME;
    
	private String NAME_E;
	private String NAME_F;
	private String NAME_S;
	private String NAME_R;
	private String NAME_A;
	private String NAME_C;
    
	private String OFFICIAL_NAME_E;
	private String OFFICIAL_NAME_F;
	private String OFFICIAL_NAME_S;
	private String OFFICIAL_NAME_R;
	private String OFFICIAL_NAME_A;
	private String OFFICIAL_NAME_C;
    
	private String FULL_NAME_E;
	private String FULL_NAME_F;
	private String FULL_NAME_S;
	private String FULL_NAME_R;
	private String FULL_NAME_A;
	private String FULL_NAME_C;
    
	private String LONG_NAME_E;
	private String LONG_NAME_F;
	private String LONG_NAME_S;
	private String LONG_NAME_R;
	private String LONG_NAME_A;
	private String LONG_NAME_C;
    
	private String SHORT_DESC_E;
	private String SHORT_DESC_F;
    private String SHORT_DESC_S;
    private String SHORT_DESC_R;
    private String SHORT_DESC_A;
    private String SHORT_DESC_C;
    
    private String SCIENTIFIC_NAME;
    private String VESSELNAME;
    
    private List<HashMap<String, String>> ATTRIBUTES;
    private String FILTER;
    
    private List<RefPubObject> parents;
	private List<RefPubObject> childrens;
	private List<CodeListDAO> codeList;
	private URI currentURI;
	private int total;
	private String concept;
	private boolean is_group;
	private String group_name;
	private boolean hidden;
	
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public String getMETA() {
		return META;
	}
	public void setMETA(String mETA) {
		META = mETA;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getNAME_E() {
		return NAME_E;
	}
	public void setNAME_E(String nAME_E) {
		NAME_E = nAME_E;
	}
	public String getNAME_F() {
		return NAME_F;
	}
	public void setNAME_F(String nAME_F) {
		NAME_F = nAME_F;
	}
	public String getNAME_S() {
		return NAME_S;
	}
	public void setNAME_S(String nAME_S) {
		NAME_S = nAME_S;
	}
	public String getNAME_R() {
		return NAME_R;
	}
	public void setNAME_R(String nAME_R) {
		NAME_R = nAME_R;
	}
	public String getNAME_A() {
		return NAME_A;
	}
	public void setNAME_A(String nAME_A) {
		NAME_A = nAME_A;
	}
	public String getNAME_C() {
		return NAME_C;
	}
	public void setNAME_C(String nAME_C) {
		NAME_C = nAME_C;
	}
	public String getOFFICIAL_NAME_E() {
		return OFFICIAL_NAME_E;
	}
	public void setOFFICIAL_NAME_E(String oFFICIAL_NAME_E) {
		OFFICIAL_NAME_E = oFFICIAL_NAME_E;
	}
	public String getOFFICIAL_NAME_F() {
		return OFFICIAL_NAME_F;
	}
	public void setOFFICIAL_NAME_F(String oFFICIAL_NAME_F) {
		OFFICIAL_NAME_F = oFFICIAL_NAME_F;
	}
	public String getOFFICIAL_NAME_S() {
		return OFFICIAL_NAME_S;
	}
	public void setOFFICIAL_NAME_S(String oFFICIAL_NAME_S) {
		OFFICIAL_NAME_S = oFFICIAL_NAME_S;
	}
	public String getOFFICIAL_NAME_R() {
		return OFFICIAL_NAME_R;
	}
	public void setOFFICIAL_NAME_R(String oFFICIAL_NAME_R) {
		OFFICIAL_NAME_R = oFFICIAL_NAME_R;
	}
	public String getOFFICIAL_NAME_A() {
		return OFFICIAL_NAME_A;
	}
	public void setOFFICIAL_NAME_A(String oFFICIAL_NAME_A) {
		OFFICIAL_NAME_A = oFFICIAL_NAME_A;
	}
	public String getOFFICIAL_NAME_C() {
		return OFFICIAL_NAME_C;
	}
	public void setOFFICIAL_NAME_C(String oFFICIAL_NAME_C) {
		OFFICIAL_NAME_C = oFFICIAL_NAME_C;
	}
	public String getFULL_NAME_E() {
		return FULL_NAME_E;
	}
	public void setFULL_NAME_E(String fULL_NAME_E) {
		FULL_NAME_E = fULL_NAME_E;
	}
	public String getFULL_NAME_F() {
		return FULL_NAME_F;
	}
	public void setFULL_NAME_F(String fULL_NAME_F) {
		FULL_NAME_F = fULL_NAME_F;
	}
	public String getFULL_NAME_S() {
		return FULL_NAME_S;
	}
	public void setFULL_NAME_S(String fULL_NAME_S) {
		FULL_NAME_S = fULL_NAME_S;
	}
	public String getFULL_NAME_R() {
		return FULL_NAME_R;
	}
	public void setFULL_NAME_R(String fULL_NAME_R) {
		FULL_NAME_R = fULL_NAME_R;
	}
	public String getFULL_NAME_A() {
		return FULL_NAME_A;
	}
	public void setFULL_NAME_A(String fULL_NAME_A) {
		FULL_NAME_A = fULL_NAME_A;
	}
	public String getFULL_NAME_C() {
		return FULL_NAME_C;
	}
	public void setFULL_NAME_C(String fULL_NAME_C) {
		FULL_NAME_C = fULL_NAME_C;
	}
	public String getLONG_NAME_E() {
		return LONG_NAME_E;
	}
	public void setLONG_NAME_E(String lONG_NAME_E) {
		LONG_NAME_E = lONG_NAME_E;
	}
	public String getLONG_NAME_F() {
		return LONG_NAME_F;
	}
	public void setLONG_NAME_F(String lONG_NAME_F) {
		LONG_NAME_F = lONG_NAME_F;
	}
	public String getLONG_NAME_S() {
		return LONG_NAME_S;
	}
	public void setLONG_NAME_S(String lONG_NAME_S) {
		LONG_NAME_S = lONG_NAME_S;
	}
	public String getLONG_NAME_R() {
		return LONG_NAME_R;
	}
	public void setLONG_NAME_R(String lONG_NAME_R) {
		LONG_NAME_R = lONG_NAME_R;
	}
	public String getLONG_NAME_A() {
		return LONG_NAME_A;
	}
	public void setLONG_NAME_A(String lONG_NAME_A) {
		LONG_NAME_A = lONG_NAME_A;
	}
	public String getLONG_NAME_C() {
		return LONG_NAME_C;
	}
	public void setLONG_NAME_C(String lONG_NAME_C) {
		LONG_NAME_C = lONG_NAME_C;
	}
	public String getSHORT_DESC_E() {
		return SHORT_DESC_E;
	}
	public void setSHORT_DESC_E(String sHORT_DESC_E) {
		SHORT_DESC_E = sHORT_DESC_E;
	}
	public String getSHORT_DESC_F() {
		return SHORT_DESC_F;
	}
	public void setSHORT_DESC_F(String sHORT_DESC_F) {
		SHORT_DESC_F = sHORT_DESC_F;
	}
	public String getSHORT_DESC_S() {
		return SHORT_DESC_S;
	}
	public void setSHORT_DESC_S(String sHORT_DESC_S) {
		SHORT_DESC_S = sHORT_DESC_S;
	}
	public String getSHORT_DESC_R() {
		return SHORT_DESC_R;
	}
	public void setSHORT_DESC_R(String sHORT_DESC_R) {
		SHORT_DESC_R = sHORT_DESC_R;
	}
	public String getSHORT_DESC_A() {
		return SHORT_DESC_A;
	}
	public void setSHORT_DESC_A(String sHORT_DESC_A) {
		SHORT_DESC_A = sHORT_DESC_A;
	}
	public String getSHORT_DESC_C() {
		return SHORT_DESC_C;
	}
	public void setSHORT_DESC_C(String sHORT_DESC_C) {
		SHORT_DESC_C = sHORT_DESC_C;
	}
	public String getSCIENTIFIC_NAME() {
		return SCIENTIFIC_NAME;
	}
	public void setSCIENTIFIC_NAME(String sCIENTIFIC_NAME) {
		SCIENTIFIC_NAME = sCIENTIFIC_NAME;
	}
	public String getVESSELNAME() {
		return VESSELNAME;
	}
	public void setVESSELNAME(String vESSELNAME) {
		VESSELNAME = vESSELNAME;
	}
	public List<HashMap<String, String>> getATTRIBUTES() {
		return ATTRIBUTES;
	}
	public void setATTRIBUTES(List<HashMap<String, String>> aTTRIBUTES) {
		ATTRIBUTES = aTTRIBUTES;
	}
	public String getFILTER() {
		return FILTER;
	}
	public void setFILTER(String fILTER) {
		FILTER = fILTER;
	}
	public List<RefPubObject> getParents() {
		return parents;
	}
	public void setParents(List<RefPubObject> parents) {
		this.parents = parents;
	}
	public List<RefPubObject> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<RefPubObject> childrens) {
		this.childrens = childrens;
	}
	public List<CodeListDAO> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<CodeListDAO> codeList) {
		this.codeList = codeList;
	}
	public URI getCurrentURI() {
		return currentURI;
	}
	public void setCurrentURI(URI currentURI) {
		this.currentURI = currentURI;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public boolean isIs_group() {
		return is_group;
	}
	public void setIs_group(boolean is_group) {
		this.is_group = is_group;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}