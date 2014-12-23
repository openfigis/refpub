package org.fao.fi.refpub.webservice.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.math.NumberUtils;
import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubConcept;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.URI;
import org.fao.fi.refpub.dao.objects.chunks.GenericType;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;
import org.fao.fi.refpub.dao.objects.chunks.MDConcept;
import org.fao.fi.refpub.dao.objects.chunks.MDGrouping;
import org.fao.fi.refpub.dao.objects.chunks.MDGroupingDepth;
import org.fao.fi.refpub.dao.objects.chunks.TableReference;
import org.fao.fi.refpub.dao.utils.Utils;
import org.fao.fi.refpub.persistence.PersistenceServiceImplementation;
import org.fao.fi.refpub.persistence.PersistenceServiceInterface;
import org.fao.fi.refpub.webservice.AttributeList;
import org.fao.fi.refpub.webservice.Concept;
import org.fao.fi.refpub.webservice.ConceptList;
import org.fao.fi.refpub.webservice.SystemError;
import org.fao.fi.refpub.webservice.impl.AttributeListTypeDTO;
import org.fao.fi.refpub.webservice.impl.CodeDTO;
import org.fao.fi.refpub.webservice.impl.CodeListListDTO;
import org.fao.fi.refpub.webservice.impl.ConceptListDTO;
import org.fao.fi.refpub.webservice.impl.ErrorListDTO;
import org.fao.fi.refpub.webservice.objects.Constants;

/*
 * Implementation of the RefPub Restful API CDI bean
 */

//@Stateless
@RequestScoped
@Named("refpub")
@Stateful

public class RefPubImplementation implements RefPubInterface {
	@Produces

	UriInfo URI;
	private static String DEFAULT_COUNT = "100";
	private static String CONFIG_FILE;
	private static Configuration CONFIGURATION;
	private static PersistenceServiceInterface ps = null;
	
	public RefPubImplementation() {
		ps = new PersistenceServiceImplementation();
		loadConfiguration();
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
	public SystemError error(Exception e) {
		return ErrorListDTO.create(e);
	}
	
	@Override
	public void setPropertiesFile(String propertiesFile) { RefPubImplementation.CONFIG_FILE = propertiesFile; }
	
	
	@Override
	public ConceptList getAllConcept(String count, String page) {
		return ConceptListDTO.create(this.getAllConcepts(count, page));
	}


	@Override
	public Concept getConcept(String concept, String count, String page) {
		return null;
	}


	@Override
	public ConceptList getAllObjectByConcept(String concept, String count, String page) {
		List<RefPubObject> res = this.getAllObjectsForConcept(concept, count, page);
		boolean isNested = false;
		for (RefPubObject o : res) {
			if (o.getChildrens() != null || o.getParents() != null) {
				isNested = true;
				break;
			}
		}
		if (!isNested)
			return ConceptListDTO.createObj(res);
		else
			return ConceptListDTO.createNestedObj(res);
	}


	@Override
	public Concept getObject(String concept, String codesystem, String code) {
		return CodeDTO.create(this.getSingleObject(concept, codesystem, code));
	}
	
	@Override
	public Concept getObject(String concept, String code) {
		return CodeDTO.create(this.getSingleObject(concept, code));
	}

	
	
	@Override
	///////////////////////////
	public Concept getAttributeForObject(String concept, String codesystem,
			String code, String attribute) {
		return CodeDTO.create(this.getSingleAttributeForSingleObject(concept, codesystem, code, attribute));
	}


	@Override
	public Concept getAllCodeSystem(String count, String page) {
		return CodeListListDTO.create(this.getAllCodeList(count, page));
	}
	

	@Override
	public ConceptList getObjectByCodeSystem(String concept, String codesystem, String count, String page) {
		return ConceptListDTO.createObj(this.getObjectsByCodeList(concept, codesystem, count, page));
	}
	
	
	@Override
	public Concept getAllCodeSystemByConcept(String concept) {
		return CodeListListDTO.create(this.getCodeSystemByConcept(concept));
	}
	
	
	@Override
	public AttributeList getAllAttributesForConceptAndCodesystem(
			String concept, String codesystem) {
		return AttributeListTypeDTO.create(this.attributeListByConceptCodelist(concept, codesystem));
	}
	
	@Override
	public AttributeList getAllAttributesForConcept(String concept) {
		return AttributeListTypeDTO.create(this.attributeListByConcept(concept));
	}	
	
	@Override
	public ConceptList getGroups(String concept) {
		return ConceptListDTO.createObj(this.getAllGropusByConcept(concept));
	}
		
	@Override
	public ConceptList getGroupMain(String concept, String filter) {
		return ConceptListDTO.createNestedObj(this.getGroupsByFilter(concept, filter));
	}
	@Override
	public ConceptList getGroup(String Concept, String filter, String group) {
		return ConceptListDTO.createNestedObj(this.getSubGroup(Concept, filter, group));
	}
	@Override
	public ConceptList getSubGroups(String Concept, String filter,
			String group, String subGroup) {
		return ConceptListDTO.createObj(this.getFilteredSubGroup(Concept, filter, group, subGroup));
	}
	
	private List<RefPubConcept> getAllConcepts(String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		List<RefPubConcept> concepts = new ArrayList<RefPubConcept>();
		
		List<MDConcept> listOfConcepts = ps.getConcepts(RefPubImplementation.CONFIGURATION.getDb_schema()); //Retrieves the concepts from the persistence layer
		for (MDConcept c : listOfConcepts) { //for each concept try to set-up the codelist to be pushed into RefPubObjects
			RefPubConcept concept = new RefPubConcept();
			concept.setName(c.getRest_concept());
			concept.setTable_name(c.getTable_name());
			concept.setTable_grp_name(c.getTable_group());
			
			List<MDCodelist> codelists = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
																  concept.getName());
			
			int countOfGroups = ps.countGroupsForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), c.getId());
			if (countOfGroups > 0) {
				concept.setHasGroup(true);
			}
			
			Map<String,String> codemap = new HashMap<String, String>();
			for (MDCodelist code : codelists) {
				codemap.put(code.getCode_column(), code.getCode_name());
			}
			concept.setCodelists(codemap);
			
			// TODO Add lookup to MD-REFOBJECT to retrieve the concept translations
			concept.setCurrentURI(this.BuildURI(count, page));
			concepts.add(concept);
		}
		
		return concepts;
	}
	
	private RefPubConcept getSingleConcept(String Concept) {
		RefPubConcept concept = new RefPubConcept();
		
		MDConcept c = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), Concept);
		concept.setName(c.getRest_concept());
		concept.setTable_name(c.getTable_name());
		concept.setTable_grp_name(c.getTable_group());
		concept.setMeta(c.getMeta_id());
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), 
															  concept.getName());
		Map<String,String> codemap = new HashMap<String, String>();
		for (MDCodelist code : codelists) {
			codemap.put(code.getCode_column(), code.getCode_name());
		}
		concept.setCodelists(codemap);
		concept.setCurrentURI(this.BuildURI("all", null));
		return concept;
	}
	
	private List<RefPubObject> getAllObjectsForConceptFlat(String concept, String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		RefPubConcept cp = this.getSingleConcept(concept);
		
		MDConcept mdconcept = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), concept);
		TableReference tbl = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), 
															  mdconcept.getTable_name());
		
		String nameColumn = Utils.guessNameColumn(ps.getTableColumns(RefPubImplementation.CONFIGURATION.getDb_schema(), mdconcept.getTable_name()));
		
		List<RefPubObject> returnList = Utils.buildRefPubObjectList(ps.getObjectsFlat(
						RefPubImplementation.CONFIGURATION.getDb_schema(), 
						tbl.getName(), 
						tbl.getPrimaryKey(),
						nameColumn,
						min, 
						max));
		
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
				  concept);
		String countall = Utils.getAttributeFromList(returnList.get(0).getATTRIBUTES(), "COUNTALL");
		for (RefPubObject obj : returnList) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI(count, page));
			obj.setTotal(Integer.parseInt(countall));
		}
		
		return returnList;
	}
	
	private List<RefPubObject> getAllObjectsForConcept(String concept, String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		RefPubConcept cp = this.getSingleConcept(concept);
		if (cp.getTable_grp_name() == null || cp.getTable_grp_name().equalsIgnoreCase("")) {
			return this.getAllObjectsForConceptFlat(concept, count, page);
		}
		
		MDConcept mdconcept = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), concept);
		TableReference tbl = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), 
															  mdconcept.getTable_name());
		
		List<String> meta = new ArrayList<String>();
		if (cp.getMeta() != null) {
			String[] split = cp.getMeta().split(",");
			for (int i = 0; i < split.length; i++) {
				meta.add(split[i]);
			}
		} else {
			meta.add("");
		}
		
		ArrayList<HashMap<String, Object>> objs = ps.getObjects(RefPubImplementation.CONFIGURATION.getDb_schema(),
												meta,
												mdconcept.getMeta_column(),
												cp.getTable_name(), 
												tbl.getPrimaryKey(),
												min, max);
		if (objs == null) { return new ArrayList<RefPubObject>(); }
		List<MDCodelist> codelists = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
															  concept);
				
		List<RefPubObject> returnList = Utils.buildRefPubObjectList(objs);
		String countall = Utils.getAttributeFromList(returnList.get(0).getATTRIBUTES(), "COUNTALL");
		for (RefPubObject obj : returnList) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI(count, page));
			obj.setTotal(Integer.parseInt(countall));
		}
		
		return returnList;
	}
		
	private RefPubObject getSingleObject(String concept, String codelist, String code) {
		MDConcept mdconcept = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
											concept);
		MDCodelist mdcodelist =  ps.getCodeList(RefPubImplementation.CONFIGURATION.getDb_schema(),
												concept, codelist);

		TableReference tbl = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), 
				  						mdconcept.getTable_name());
		
		RefPubObject refPubObject = Utils.buildRefPubObject(ps.getObject(RefPubImplementation.CONFIGURATION.getDb_schema(),
															mdconcept.getTable_name(), mdcodelist.getCode_column(), 
															code, tbl.getPrimaryKey()));
				
		if (refPubObject == null) {
			return new RefPubObject();
		}
		
		List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(ps.getCodelistForConcept(
																	RefPubImplementation.CONFIGURATION.getDb_schema(), 
																	concept), 
																	refPubObject);
		
		refPubObject.setConcept(concept);
		refPubObject.setCodeList(codemap);

		refPubObject.setParents(this.getParents(refPubObject, mdconcept, tbl));
		refPubObject.setChildrens(this.getChildren(refPubObject, mdconcept, tbl));
		
		refPubObject.setCurrentURI(this.BuildURI(null, null));
		return refPubObject;
	}
	
	private RefPubObject getSingleObject(String concept, String code) {
		MDConcept mdconcept = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
											concept);

		TableReference tbl = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), 
										mdconcept.getTable_name());
		
		RefPubObject obj = Utils.buildRefPubObject(ps.getObjectById(RefPubImplementation.CONFIGURATION.getDb_schema(), 
											mdconcept.getTable_name(), tbl.getPrimaryKey(), code));
		
		if (obj == null) {
			return new RefPubObject();
		}
		
		List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(ps.getCodelistForConcept(
												RefPubImplementation.CONFIGURATION.getDb_schema(), concept), obj);
		obj.setConcept(concept);
		obj.setCodeList(codemap);
		
		obj.setParents(this.getParents(obj, mdconcept, tbl));
		obj.setChildrens(this.getChildren(obj, mdconcept, tbl));
		
		obj.setCurrentURI(this.BuildURI("1", "1")); 
		return obj;
	}
	
	private RefPubObject getSingleAttributeForSingleObject(String concept, String codelist, String code, String attribute) {
		MDConcept mdconcept = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), concept);
		MDCodelist mdcodelist =  ps.getCodeList(RefPubImplementation.CONFIGURATION.getDb_schema(), 
												concept, codelist);

		TableReference tbl = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), 
										mdconcept.getTable_name());
		
		RefPubObject obj = Utils.buildRefPubObject(ps.getAttributeForSingleObject(RefPubImplementation.CONFIGURATION.getDb_schema(),
														  mdconcept.getTable_name(), 
														  mdcodelist.getCode_column(), 
														  code, 
														  tbl.getPrimaryKey(),
														  attribute));
		
		if (obj == null) {
			return new RefPubObject();
		}
		return obj;
	}
	
	private RefPubObject getAllCodeList(String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		RefPubObject returnObj = new RefPubObject();
		
		List<MDCodelist> codelists = ps.getCodeList_list(RefPubImplementation.CONFIGURATION.getDb_schema());
		
		List<CodeListDAO> l = new ArrayList<CodeListDAO>();
		
		for (MDCodelist cl : codelists) {
			CodeListDAO codeListObj = new CodeListDAO();
			codeListObj.setName(cl.getCode_name());
			codeListObj.setValue(cl.getRest_concept());
			l.add(codeListObj);
		}
		returnObj.setCodeList(l);
		returnObj.setCurrentURI(this.BuildURI(count, page));
		return returnObj;
	}
	
	private List<RefPubObject> getObjectsByCodeList(String concept, String codelist, String count, String page) {
		String min = this.calculateQueryPagination("min", count, page);
		String max = this.calculateQueryPagination("max", count, page);
		if (count == null) { count = DEFAULT_COUNT; }
		if (page == null) { page = Integer.toString(Integer.parseInt(min+1)); }
		
		MDConcept con = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), concept);
		MDCodelist cl = ps.getCodeList(RefPubImplementation.CONFIGURATION.getDb_schema(), concept, codelist);
		if (cl == null) {
			return new ArrayList<RefPubObject>();
		}
		TableReference ti = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), 
													   cl.getTable_name());
		
		List<RefPubObject> returnList = Utils.buildRefPubObjectList(ps.getObjectsByCodeList(RefPubImplementation.CONFIGURATION.getDb_schema(), 
																cl.getTable_name(),
																con.getMeta_column(),
																con.getMeta_id(),
																cl.getCode_column(), 
																ti.getPrimaryKey(),
																min, max));
		
		if (returnList == null) { return new ArrayList<RefPubObject>(); }
		
		List<MDCodelist> codelists = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
															  concept);
		
		int counter = 0;
		for (RefPubObject obj : returnList) {
			List<CodeListDAO> codemap = Utils.retrieveCodeListForObject(codelists, obj);
			obj.setConcept(concept);
			obj.setCodeList(codemap);
			obj.setCurrentURI(this.BuildURI(count, page));
			returnList.set(counter, obj);
			counter++;
		}
		
		return returnList;
	}


	private RefPubObject getCodeSystemByConcept(String concept) {
		RefPubObject returnObj = new RefPubObject();
		
		List<MDCodelist> codelists = ps.getCodeList_listByConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
																  concept);
		
		List<CodeListDAO> l = new ArrayList<CodeListDAO>();
		
		for (MDCodelist cl : codelists) {
			CodeListDAO codeListObj = new CodeListDAO();
			codeListObj.setName(cl.getCode_name());
			codeListObj.setValue(cl.getRest_concept());
			l.add(codeListObj);
		}
		returnObj.setCodeList(l);
		returnObj.setCurrentURI(this.BuildURI(null, null));
		return returnObj;
	}
	
	private List<RefPubObject> getAllGropusByConcept(String concept) {
		MDConcept mdConcept = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), concept);
		
		List<MDGrouping> list = ps.getGroups(RefPubImplementation.CONFIGURATION.getDb_schema(),
									Integer.parseInt(mdConcept.getId()));
		
		List<RefPubObject> retList = new ArrayList<RefPubObject>();
		
		for (MDGrouping mdc : list) {
			RefPubObject o = new RefPubObject();
			if (mdc.getShort_description() != null) {
				o.setFILTER(mdc.getShort_description());
			} else {
				o.setFILTER(mdc.getFilter());
			}
			o.setNAME_E(mdc.getName_e());
			o.setNAME_A(mdc.getName_a());
			o.setNAME_C(mdc.getName_c());
			o.setNAME_F(mdc.getName_f());
			o.setNAME_R(mdc.getName_r());
			o.setNAME_S(mdc.getName_s());
			o.setCurrentURI(this.BuildURI(null, null));
			o.setIs_group(true);
			o.setConcept(concept);
			retList.add(o);
		}
		
		return retList;
		
	}
	
	private List<RefPubObject> getGroupsByFilter(String concept, String filter) {
		MDGrouping mdGrouping = ps.getGroupByDescription(RefPubImplementation.CONFIGURATION.getDb_schema(), 
													filter);
		
		if (mdGrouping == null) {
			mdGrouping = ps.getGroupByFilter(RefPubImplementation.CONFIGURATION.getDb_schema(), 
													filter);
		}
		
		if (mdGrouping.getIsFilterGroup() == 0) {
			return this.getAllHierarchy(concept, filter);
		}
		
		List<Integer> hl = new ArrayList<Integer>();
		if (mdGrouping.getHierarchy() != null) {
			for (String h : Arrays.asList(mdGrouping.getHierarchy().split(","))) {
				hl.add(Integer.parseInt(h));
			}
		}
		else {
			return this.getSubGroup(concept, filter, mdGrouping.getMeta());
		}
		
		
		List<RefPubObject> topHierarchy = Utils.buildRefPubObjectList(ps.getGroupsValues(RefPubImplementation.CONFIGURATION.getDb_schema(), 
												hl));
		
		RefPubObject alphabetical = new RefPubObject();
		alphabetical.setNAME("Alphabetical");
		alphabetical.setNAME_E("Alphabetical");
		alphabetical.setNAME_F("Alphabétique");
		alphabetical.setNAME_S("Alfabético");
		alphabetical.setNAME_C("按字母顺序排列");
		alphabetical.setNAME_R("алфавитный");
		alphabetical.setNAME_A("أبجدي");
		alphabetical.setPKID(Utils.ALPHABETICAL_ID);
		
		topHierarchy.add(alphabetical);
		int counter = 0;
		for (RefPubObject obj : topHierarchy) {
			obj.setCurrentURI(this.BuildURI(null, null));
			obj.setIs_group(true);
			obj.setConcept(concept);
			obj.setFILTER(filter);
			topHierarchy.set(counter, obj);
			counter++;
		}
		return topHierarchy;
	}

	private List<RefPubObject> getSubGroup(String concept, String filter, String group) {
		
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
		
		if (Utils.ALPHABETICAL_ID.equalsIgnoreCase(group) && 
			!mdGrouping.getScanning_method().equalsIgnoreCase("FLAT")) {
			return this.getAlphabeticalGroup(concept, filter, group);
		}
		
		/*if ("commodities".equals(concept.toLowerCase())) {
			return this.getSubGroupAlternative(concept, filter, group);
		}*/
		MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(dbSchema, concept);

		
		
		if (mdGrouping.getScanning_method().equalsIgnoreCase("SYSTEM_HIERARCHICAL")) {
			return this.getSubGroupSystemHierarchical(concept, filter, group);
		} else if (mdGrouping.getScanning_method().equalsIgnoreCase("FLAT")) {
			return this.getSubGroupFlatNoMeta(concept, filter, group);
		}
		
		MDGroupingDepth mdGroupingDepth = ps.getGroupDepth(dbSchema, mdGrouping.getId(), group);
				
		TableReference itemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getItem_table());
		TableReference groupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getGroup_table());
		TableReference hItemTable;
		TableReference hGroupTable;
		
		if (mdGrouping.getItem_table().equalsIgnoreCase(mdGrouping.getHierarchy_item_table())) {
			hItemTable = itemTable;
		} else {
			hItemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getHierarchy_item_table());
		}
		if (mdGrouping.getGroup_table().equalsIgnoreCase(mdGrouping.getHierarchy_group_table())) {
			hGroupTable = groupTable;
		} else {
			hGroupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getHierarchy_group_table());
		}
		
		List<String> totalDepth = new ArrayList<String>();
		if (mdGroupingDepth != null) {
			String[] app = mdGroupingDepth.getDepth().split(",");
			for (int i = 0; i < app.length; i++) {
				totalDepth.add(app[i]);
			}
			//totalDepth = Arrays.asList(mdGroupingDepth.getDepth().split(","));
		}
		
		if (totalDepth.size() < 1) {
			return this.getFlatGroupHierarchy(concept, filter, group);
		}
		
		/* Getting root Stuff*/
		List<RefPubObject> fullList = new ArrayList<RefPubObject>();
		fullList = Utils.buildRefPubObjectList(ps.getRootHierarchy(dbSchema, 
														mdGrouping.getHierarchy_item_table(), 
														hItemTable.getPrimaryKey(), 
														hItemTable.getMetaColumn(), 
														totalDepth.get(0)));
		
		/*Getting SubGroups*/
		if (totalDepth.size() < 1) {
			totalDepth.add(mdGrouping.getMeta());
		}
		List<RefPubObject> subGroupsList = Utils.buildRefPubObjectList(ps.getSubGroupHierarchy(
					dbSchema, 
					itemTable.getName(),
					hItemTable.getName(),
					hGroupTable.getName(),
					itemTable.getPrimaryKey(),
					hItemTable.getPrimaryKey(),
					itemTable.getMetaColumn(),
					hItemTable.getMetaColumn(),
					hGroupTable.getMemberColumn(),
					hGroupTable.getGroupColumn(),
					totalDepth));
		
		/*Putting subGroups in place*/
		for (RefPubObject s : subGroupsList) {
			String parentId = Utils.getAttributeFromList(s.getATTRIBUTES(), "PARENT_ID");
			fullList = Utils.addChildrenToList(fullList, s, parentId);
		}
		
		/*Getting Filtered/NonFiltered objects*/
		List<RefPubObject> listObjects = Utils.buildRefPubObjectList(ps.getFilteredObjectsHierarchy(
					dbSchema, 
					itemTable.getName(), 
					hItemTable.getName(), 
					hGroupTable.getName(), 
					groupTable.getName(), 
					itemTable.getPrimaryKey(), 
					itemTable.getMetaColumn(), 
					hItemTable.getPrimaryKey(), 
					hItemTable.getMetaColumn(), 
					hGroupTable.getMemberColumn(), 
					hGroupTable.getGroupColumn(), 
					groupTable.getGroupColumn(), 
					groupTable.getMemberColumn(), 
					hGroupTable.getMetaColumn(), 
					mdGrouping.getFilter_meta(), 
					totalDepth), true);
		
		for (RefPubObject s : listObjects) {
			String parentId = Utils.getAttributeFromList(s.getATTRIBUTES(), "PARENT_ID");
			fullList = Utils.addChildrenToList(fullList, s, parentId);
		}

		return Utils.cleanUpGroupList(Utils.setUpUpGroupList(Utils.setUpURIOnTree(Utils.setUpCodeListOnTree(fullList, mdCodelist, concept), this.BuildURI(null, null)), mdGrouping.getMeta()), mdGrouping.getMeta());
	}
	
	
	private List<RefPubObject> getSubGroupSystemHierarchical(String concept, String filter, String group) {
		
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		
		MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(dbSchema, concept);
		RefPubObject mainHierarchy = Utils.buildRefPubObject(ps.getHierarchy(dbSchema, group));
		
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
				
		TableReference itemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getItem_table());
		TableReference groupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getGroup_table());
		TableReference hItemTable;
		TableReference hGroupTable;
		
		if (mdGrouping.getItem_table().equalsIgnoreCase(mdGrouping.getHierarchy_item_table())) {
			hItemTable = itemTable;
		} else {
			hItemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getHierarchy_item_table());
		}
		if (mdGrouping.getGroup_table().equalsIgnoreCase(mdGrouping.getHierarchy_group_table())) {
			hGroupTable = groupTable;
		} else {
			hGroupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getHierarchy_group_table());
		}
		
		
		List<RefPubObject> fullList = Utils.buildRefPubObjectHierarchyList(ps.getSubGroupHierarchyAlternative(
								dbSchema, 
								itemTable.getName(), 
								groupTable.getName(), 
								itemTable.getPrimaryKey(), 
								itemTable.getMetaColumn(),
								groupTable.getMemberColumn(), 
								groupTable.getGroupColumn(), 
								mainHierarchy.getFILTER()));
		
		List<RefPubObject> leafs = Utils.findAllLeafs(fullList, new ArrayList<RefPubObject>());
		
		if (leafs.size() > 0) {
			List<String> leafsPKID = new ArrayList<String>();
			for (RefPubObject o : leafs) {
				leafsPKID.add(o.getPKID());
			}
			List<RefPubObject> childs = Utils.buildRefPubObjectList(ps.getChildsHierarchyAlternative(
									dbSchema, 
									itemTable.getName(), 
									groupTable.getName(), 
									itemTable.getPrimaryKey(), 
									itemTable.getMetaColumn(), 
									groupTable.getMemberColumn(), 
									groupTable.getGroupColumn(), 
									mdGrouping.getMeta(), 
									leafsPKID));
			for (RefPubObject child : childs) {
				fullList = Utils.attachObjectToRefPubTree(fullList, child);
			}
		}
		return Utils.setUpURIOnTree(Utils.setUpCodeListOnTree(fullList, mdCodelist, concept), this.BuildURI(null, null));
	}
	
	private List<RefPubObject> getSubGroupFlatNoMeta (String concept, String filter, String group) {
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		
		MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(dbSchema, concept);
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
		
		TableReference itemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getItem_table());
		TableReference groupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getGroup_table());
		
		if (!Utils.ALPHABETICAL_ID.equalsIgnoreCase(group)) {
			MDGroupingDepth depth = ps.getGroupDepth(dbSchema, mdGrouping.getId(), group);
			
			List<String> totDepth = new ArrayList<String>();
			for (String d : depth.getDepth().split(",")) {
				totDepth.add(d);
			}
			
			List<RefPubObject> hierarchy = Utils.buildRefPubObjectHierarchyList(ps.getFlatHierarchyType(
														dbSchema, 
														itemTable.getName(), 
														groupTable.getName(), 
														itemTable.getPrimaryKey(), 
														groupTable.getMemberColumn(), 
														groupTable.getGroupColumn(), 
														group, 
														totDepth.get(0)));
			return Utils.setUpURIOnTree(Utils.setUpCodeListOnTree(hierarchy, mdCodelist, concept), this.BuildURI(null, null));
		} else {
			
			List<MDGroupingDepth> depth = ps.getGroupDepthById(dbSchema, mdGrouping.getId());
			List<String> totDepth = new ArrayList<String>();
			for (String d : depth.get(0).getDepth().split(",")) {
				totDepth.add(d);
			}
			List<String> metaList = new ArrayList<String>();
			for (MDGroupingDepth d : depth) {
				metaList.add(d.getHierarchy());
			}
			List<RefPubObject> hierarchy = Utils.buildRefPubObjectHierarchyList(ps.getFlatHierarchyTypeExtended(
					dbSchema, 
					itemTable.getName(), 
					groupTable.getName(), 
					itemTable.getPrimaryKey(), 
					groupTable.getMemberColumn(), 
					groupTable.getGroupColumn(), 
					metaList, 
					totDepth.get(0)));
			return Utils.buildAlphabeticalOnTree(Utils.setUpURIOnTree(Utils.setUpCodeListOnTree(hierarchy, mdCodelist, concept), this.BuildURI(null, null)), "name_e", true);
		}
	}
	
	private List<RefPubObject> getAlphabeticalGroup(String concept, String filter, String group) {
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		
		MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(dbSchema, concept);
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
				
		TableReference itemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getItem_table());
		TableReference groupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getGroup_table());
		
		List<RefPubObject> list = new ArrayList<RefPubObject>();
		if (mdGrouping.getFilter_meta() != null) {
			list = Utils.buildRefPubObjectList(ps.getAplhabeticalByFilter(
					dbSchema, 
					itemTable.getName(), 
					groupTable.getName(), 
					itemTable.getPrimaryKey(), 
					itemTable.getMetaColumn(), 
					groupTable.getMemberColumn(), 
					groupTable.getGroupColumn(), 
					mdGrouping.getFilter_meta()));
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCurrentURI(this.BuildURI(null, null));
			list.get(i).setConcept(concept);
			
			List<CodeListDAO> cll = new ArrayList<CodeListDAO>();
			CodeListDAO cl = new CodeListDAO();
			cl.setIsDefault(1);
			cl.setName(mdCodelist.getCode_name());
			cl.setValue(list.get(i).getPKID());
			cll.add(cl);
			
			list.get(i).setCodeList(cll);
		}
		
		return list;
	}
	
	private List<RefPubObject> getFlatGroupHierarchy(String concept, String filter, String group) {
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		
		MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(dbSchema, concept);
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
				
		TableReference itemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getItem_table());
		TableReference groupTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getGroup_table());
		
		List<RefPubObject> retStrut = new ArrayList<RefPubObject>();
		
		retStrut = Utils.buildRefPubObjectList(
					ps.getFlatHierarchy(
						dbSchema, 
						itemTable.getName(), 
						groupTable.getName(), 
						itemTable.getPrimaryKey(), 
						itemTable.getMetaColumn(), 
						groupTable.getMemberColumn(), 
						groupTable.getGroupColumn(), 
						mdGrouping.getFilter()));
		
		if (retStrut.size() < 1) {
			retStrut = Utils.buildRefPubObjectList(
						ps.getFlatHierarchy(
							dbSchema, 
							itemTable.getName(), 
							groupTable.getName(), 
							itemTable.getPrimaryKey(), 
							itemTable.getMetaColumn(), 
							groupTable.getMemberColumn(), 
							groupTable.getGroupColumn(), 
							mdGrouping.getFilter_meta()));
		}
		
		return Utils.setUpCodeListOnTree(
				Utils.setUpURIOnTree(retStrut, this.BuildURI(null, null)), mdCodelist, concept);
	}
	
	private List<RefPubObject> getAllHierarchy(String concept, String filter) {
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
		
		List<RefPubObject> treeRes = Utils.buildRefPubObjectHierarchyList(ps.getEntireHierarchy(dbSchema, mdGrouping.getFilter()));
		
		//return this.setUpInfoOnTree(treeRes, concept, ps.getDefaultCodelistFromConcept(dbSchema, concept));
		return this.attachObjectsToLeafs(treeRes, concept, filter);
	}
	
	private List<RefPubObject> getFilterMetaGrouping(String concept, String filter, String filterMeta) {
		List<String> fm = new ArrayList<String>();
		fm.add(filterMeta);
		return this.getFilterMetaGrouping(concept, filter, fm);
	}
	
	private List<RefPubObject> getFilterMetaGrouping(String concept, String filter, List<String> filterMeta) {
		String dbSchema = RefPubImplementation.CONFIGURATION.getDb_schema();
		
		MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(dbSchema, concept);
		MDGrouping mdGrouping = ps.getGroupByDescription(dbSchema, filter);
		TableReference itemTable = ps.getTableReferenceByName(dbSchema, mdGrouping.getItem_table());
		
		List<RefPubObject> retList = Utils.buildRefPubObjectList(ps.getMetaGrouping(dbSchema, 
																					itemTable.getName(), 
																					itemTable.getPrimaryKey(), 
																					itemTable.getMetaColumn(), 
																					filterMeta));
		
		for (int counter=0; counter < retList.size(); counter++) {
			retList.get(counter).setCodeList(this.buildCodeListFromMD(mdCodelist, retList.get(counter)));
			retList.get(counter).setCurrentURI(this.BuildURI(null, null));
			retList.get(counter).setConcept(concept);
		}
		
		return retList;
	}
	
	private List<RefPubObject> getFilteredSubGroup(String Concept, String filter, String group, String subGroup) {
		List<RefPubObject> fullGroup = this.getSubGroup(Concept, filter, group);
		return this.getChildList(fullGroup, subGroup);
		
	}
	
	private List<RefPubObject> getChildList(List<RefPubObject> list, String id) {
		if (list != null) {
			for (RefPubObject o : list) {
				if (o.getPKID().equalsIgnoreCase(id)) {
					return o.getChildrens();
				} else {
					List<RefPubObject> res = this.getChildList(o.getChildrens(), id);
					if (res.size() > 0) {
						return res;
					}
				}
			}
		}
		return new ArrayList<RefPubObject>();
	}
	
	private List<RefPubObject> getParents (RefPubObject refPubObject, MDConcept mdconcept, TableReference tbl) {
		if (mdconcept.getTable_group() != null) {
			List<MDCodelist> codelist = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), mdconcept.getRest_concept());
			List<RefPubObject> parents = Utils.buildRefPubObjectList(ps.getParentHierarchy(  RefPubImplementation.CONFIGURATION.getDb_schema(),
					 mdconcept.getTable_name(),
					 mdconcept.getTable_group(), 
					 mdconcept.getTable_group_member(),
					 mdconcept.getMeta_column(),
					 refPubObject.getPKID(),
					 mdconcept.getTable_group_column(),
					 tbl.getPrimaryKey()));
			
			
			
			if (parents.size() > 0) {
				for (int x = 0; x < parents.size(); x++) {
					parents.get(x).setCurrentURI(this.BuildURI("1", "1"));
					parents.get(x).setConcept(refPubObject.getConcept());
					parents.get(x).setGroup_name(this.getValueFromAttributes(parents.get(x).getATTRIBUTES(), "MKP_GROUP_NAME"));
					parents.get(x).setNAME(this.getValueFromAttributes(parents.get(x).getATTRIBUTES(), "SCIENTIFIC_NAME"));
					parents.get(x).setCodeList(Utils.retrieveCodeListForObject(codelist, parents.get(x)));
				}
			} else {
				String columnName = Utils.guessNameColumn(ps.getTableColumns(RefPubImplementation.CONFIGURATION.getDb_schema(), mdconcept.getTable_name()));
				parents = Utils.buildRefPubObjectList(ps.getRootParentHierarchy(  RefPubImplementation.CONFIGURATION.getDb_schema(),
						 mdconcept.getTable_name(),
						 mdconcept.getTable_group(), 
						 mdconcept.getTable_group_member(),
						 mdconcept.getMeta_column(),
						 refPubObject.getPKID(),
						 mdconcept.getTable_group_column(),
						 columnName,
						 tbl.getPrimaryKey()));
				for (int x = 0; x < parents.size(); x++) {
					parents.get(x).setCurrentURI(this.BuildURI("1", "1"));
					parents.get(x).setConcept(refPubObject.getConcept());
					parents.get(x).setGroup_name(this.getValueFromAttributes(parents.get(x).getATTRIBUTES(), "MKP_GROUP_NAME"));
					parents.get(x).setNAME(this.getValueFromAttributes(parents.get(x).getATTRIBUTES(), "SCIENTIFIC_NAME"));
					parents.get(x).setCodeList(Utils.retrieveCodeListForObject(codelist, parents.get(x)));
				}
			}
			
			return parents;
		} else {
			return null;
		}
	}
	
	private List<RefPubObject> getChildren (RefPubObject refPubObject, MDConcept mdconcept, TableReference tbl) {
		if (mdconcept.getTable_group() != null) {
			List<MDCodelist> codelist = ps.getCodelistForConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), mdconcept.getRest_concept());
			
			List<RefPubObject> children = Utils.buildRefPubObjectList(ps.getChildrenHierarchy(  RefPubImplementation.CONFIGURATION.getDb_schema(),
					 mdconcept.getTable_name(),
					 mdconcept.getTable_group(), 
					 mdconcept.getTable_group_member(),
					 mdconcept.getMeta_column(),
					 refPubObject.getPKID(),
					 mdconcept.getTable_group_column(),
					 tbl.getPrimaryKey()));
			
			for (int x = 0; x < children.size(); x++) {
				children.get(x).setCurrentURI(this.BuildURI("1", "1"));
				children.get(x).setConcept(refPubObject.getConcept());
				children.get(x).setGroup_name(this.getValueFromAttributes(children.get(x).getATTRIBUTES(), "MKP_GROUP_NAME"));
				children.get(x).setNAME(this.getValueFromAttributes(children.get(x).getATTRIBUTES(), "SCIENTIFIC_NAME"));
				children.get(x).setCodeList(Utils.retrieveCodeListForObject(codelist, children.get(x)));
				
			}
			
			/*
			 * This self-iteration might be a bug... 
			 */
			
			/*if (children.size() > 0) {
				int counter = 0;
				for (RefPubObject c : children) {
					List<RefPubObject> itr = this.getChildren(c, mdconcept, tbl);
					if (itr.size() > 0) {
						c.setChildrens(this.getChildren(c, mdconcept, tbl));
						c.setIs_group(true);
						children.set(counter, c);
					}
					counter++;
				}
			}*/
			
			return children;
		} else {
			return null;
		}
	}

	private List<GenericType> attributeListByConceptCodelist(String concept, String codelist) {
		MDCodelist cl = ps.getCodeList(RefPubImplementation.CONFIGURATION.getDb_schema(),
									   concept, codelist);
		List<GenericType> list = ps.getTableColumns(RefPubImplementation.CONFIGURATION.getDb_schema(),
													cl.getTable_name());
		
		List<GenericType> retList = new ArrayList<GenericType>(); 
		for (GenericType t : list) {
			if (Arrays.asList(Constants.valid_attributes).contains(t.getValue().toLowerCase())) {
				t.setValue(t.getValue().toLowerCase());
				retList.add(t);
			}
		}
		
		return retList;
	}
	
	private List<GenericType> attributeListByConcept(String concept) {
		MDConcept cmt = ps.getConcept(RefPubImplementation.CONFIGURATION.getDb_schema(),
									   concept);
		List<GenericType> list = ps.getTableColumns(RefPubImplementation.CONFIGURATION.getDb_schema(),
				cmt.getTable_name());

		List<GenericType> retList = new ArrayList<GenericType>(); 
		for (GenericType t : list) {
			if (Arrays.asList(Constants.valid_attributes).contains(t.getValue().toLowerCase())) {
				t.setValue(t.getValue().toLowerCase());
				retList.add(t);
			}
		}
		return retList;
	}
	
	private List<RefPubObject> attachObjectsToLeafs(List<RefPubObject> tree, String concept, String filter) {
		for (int i = 0; i < tree.size(); i++) {
			if (Utils.hasChildObject(tree.get(i))) {
				tree.get(i).setChildrens(this.attachObjectsToLeafs(tree.get(i).getChildrens(), concept, filter));
			} else {
				MDCodelist mdCodelist = ps.getDefaultCodelistFromConcept(RefPubImplementation.CONFIGURATION.getDb_schema(), concept);
				MDGrouping mdGrouping = ps.getGroupByDescription(RefPubImplementation.CONFIGURATION.getDb_schema(), filter);
						
				TableReference itemTable = ps.getTableReferenceByName(RefPubImplementation.CONFIGURATION.getDb_schema(), mdGrouping.getItem_table());
				
				int countSubObjects = ps.getObjectByMetaCount(RefPubImplementation.CONFIGURATION.getDb_schema(), 
															  itemTable.getName(), 
															  itemTable.getPrimaryKey(), 
															  itemTable.getMetaColumn(), 
															  tree.get(i).getPKID());
				List<RefPubObject> newLeafs = new ArrayList<RefPubObject>();
				if (countSubObjects < 100) {
					newLeafs = Utils.buildRefPubObjectList(ps.getObjectByMeta(RefPubImplementation.CONFIGURATION.getDb_schema(), 
																				itemTable.getName(), 
																				itemTable.getPrimaryKey(), 
																				itemTable.getMetaColumn(), 
																				tree.get(i).getPKID()));
					newLeafs = this.setUpInfoOnTree(newLeafs, concept, mdCodelist);
				} else {
					List<String> offsets = this.buildOffsets(countSubObjects);
					for (String offset : offsets) {
						String start = offset.split(",")[0];
						String numRows = "100";
						List<RefPubObject> newLeafsTemp = Utils.buildRefPubObjectList(ps.getObjectByMetaPaginate(RefPubImplementation.CONFIGURATION.getDb_schema(), 
																				itemTable.getName(), 
																				itemTable.getPrimaryKey(), 
																				itemTable.getMetaColumn(), 
																				tree.get(i).getPKID(),
																				start, numRows));
						newLeafsTemp = this.setUpInfoOnTree(newLeafsTemp, concept, mdCodelist);
						for (RefPubObject leaf : newLeafsTemp) {
							newLeafs.add(leaf);
						}
					}
				}
				
				tree.get(i).setChildrens(newLeafs);
			}
		}
		return tree;
	}
	
	private URI BuildURI(String count, String page) {
		URI uri = new URI();
		
		if (URI == null) {
			return null;
		}
		uri.setFullURI(URI.getRequestUri().toString());
		uri.setHost(URI.getRequestUri().getHost());
		uri.setPath(URI.getBaseUri().getPath());
		uri.setPort(Integer.toString(URI.getRequestUri().getPort()));
		if (count != null && page != null) {
			if ("all".equalsIgnoreCase(count)) {
				uri.setAll(true);
				uri.setCount(999999999);
				uri.setPage(1);
			} else {
				uri.setCount(Integer.parseInt(count));
				uri.setPage(Integer.parseInt(page));
			}
		}
		return uri;
	}
	
	private List<CodeListDAO> buildCodeListFromMD(MDCodelist mdCodelist, RefPubObject o) {
		CodeListDAO cl = new CodeListDAO();
		cl.setIsDefault(mdCodelist.getIsDefault());
		cl.setName(mdCodelist.getCode_name());
		cl.setValue(o.getPKID());
		List<CodeListDAO> cll = new ArrayList<CodeListDAO>();
		cll.add(cl);
		return cll;
	}
	
	/*
	 * Calculates the pagination min/max offset for the query
	 * @param minMax is an identifier with value "min" or "max"
	 * @param count is the count of returned records
	 * @param page is the page you look for
	 * @return the min value if the param minMax is "min" / the max value if the param minMax is "max"
	 * @return null in the case minMax is not set to "min" || "max"
	 */
	private String calculateQueryPagination(String minMax, String count, String page) {
		boolean min = false;
		boolean max = false;
		if ("all".equalsIgnoreCase(count)) {
			count = "9999999";
			page = "1";
		}
		if ("min".equalsIgnoreCase(minMax)) {
			min = true;
		} else if ("max".equalsIgnoreCase(minMax)) {
			max = true;
		} else {
			return null;
		}
		
		if (count == null || !NumberUtils.isNumber(count)) {
			count = "100";
		} if (page == null || !NumberUtils.isNumber(page)) {
			page = "1";
		}
		
		String value = "";
		if (min) {
			if (Integer.parseInt(page) == 1) {
				return "0";
			}
			value = Integer.toString((Integer.parseInt(count) * (Integer.parseInt(page) - 1)) + 1);
		} else if (max) {
			value = Integer.toString(Integer.parseInt(count) * (Integer.parseInt(page)));
		} else {
			return null;
		}
		
		return value;
	}
	
	private List<String> buildOffsets(int count) {
		int divider = 100;
		int num = count;
		int div = num/divider;
		int fract = num - (div*divider);
		List<String> offsets = new ArrayList<String>();
		
		int off=0;
		for (int i = 0; i<div; i++) {
			offsets.add(Integer.toString(off+1) + "," + Integer.toString(off+divider));
			off = off + divider;
		}
		if (fract != 0) {
			offsets.add(Integer.toString(off+1) + "," + Integer.toString(off+fract));
		}
		return offsets;
	}

	
	private List<RefPubObject> setUpInfoOnTree(List<RefPubObject> tree, String concept, MDCodelist codelist) {
		for (int i = 0; i < tree.size(); i++) {
			tree.get(i).setCurrentURI(this.BuildURI(null, null));
			tree.get(i).setConcept(concept);
			List<CodeListDAO> cll = new ArrayList<CodeListDAO>();
			CodeListDAO cl = new CodeListDAO();
			cl.setIsDefault(1);
			cl.setName(codelist.getCode_name());
			cl.setValue(tree.get(i).getPKID());
			cll.add(cl);
			tree.get(i).setCodeList(cll);
			if (Utils.hasChildObject(tree.get(i))) {
				tree.get(i).setChildrens(this.setUpInfoOnTree(tree.get(i).getChildrens(), concept, codelist));
			}
		}
		return tree;
	}
	

	private void loadConfiguration() {
		if (RefPubImplementation.CONFIG_FILE == null) {
			RefPubImplementation.CONFIGURATION = new Configuration();
		} else {
			RefPubImplementation.CONFIGURATION = new Configuration(RefPubImplementation.CONFIG_FILE);
		}
	}
	
	private String getValueFromAttributes(List<HashMap<String, String>> attributes, String key) {
		for (HashMap<String, String> attribute : attributes) {
			for (Entry<String, String> e : attribute.entrySet()) {
				if (e.getKey().equalsIgnoreCase(key)) {
					return e.getValue();
				}
			}
		}
		return null;
	}
}

