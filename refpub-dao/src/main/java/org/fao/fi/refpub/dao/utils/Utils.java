package org.fao.fi.refpub.dao.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.URI;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;

public class Utils {
	
	public static String ALPHABETICAL_ID = "999999";
	
	public static List<CodeListDAO> retrieveCodeListForObject(List<MDCodelist> codelists, RefPubObject obj) {
		List<CodeListDAO> codemap = new ArrayList<CodeListDAO>();
		for (MDCodelist cl : codelists) {
			CodeListDAO clobj = new CodeListDAO();
			clobj.setName(cl.getCode_name());
			String value = getBeanProperty(obj, cl.getCode_column());
			if (value == null) {
				value = getBeanAttrProperty(obj, cl.getCode_column());
			}
			clobj.setValue(value);
			clobj.setIsDefault(cl.getIsDefault());
			codemap.add(clobj);
		}
		return codemap;
	}
	
	public static String getBeanProperty(RefPubObject bean, String column) {  
	    BeanInfo info;
		try {
			info = Introspector.getBeanInfo(bean.getClass(), Object.class);
			PropertyDescriptor[] props = info.getPropertyDescriptors();  
		    for (PropertyDescriptor pd : props) {  
		        if (pd.getName().equalsIgnoreCase(column)) {  
		        	Method getter = pd.getReadMethod();  		  
		        	Object value = getter.invoke(bean);
		        	return Utils.getStringFromObject(value); 
		        }
		    }  
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
	    return null;
	}
	
	private static String getBeanAttrProperty(RefPubObject bean, String column) {
		for (HashMap<String, String> attributes : bean.getATTRIBUTES()) {
			for (Entry<String, String> kv : attributes.entrySet()) {
				if (kv.getKey().toLowerCase().equals(column.toLowerCase())) {
					return kv.getValue();
				}
			}
		}
		return null;
	}
	
	public static String getStringFromObject(Object object) {
		if (object instanceof Timestamp) {
			return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(object);
		}
		if(object instanceof Integer) {
			return Integer.toString((int) object);
		}
		if (object instanceof Float) {
			return Float.toString((float) object);
		}
		if (object instanceof BigDecimal) {
			return String.valueOf(((BigDecimal) object).toBigInteger().toString());
		}
		return (String) object;
	}
	
	/*RefPubObject Builders*/
	public static RefPubObject buildRefPubObject (HashMap<String, Object> row) {
		RefPubObject obj = new RefPubObject();
		List<HashMap<String, String>> additionalAttributes = new ArrayList<HashMap<String, String>>();
		
		for (Entry<String, Object> e : row.entrySet()) {
			if (StandardFigisDBColumns.FIGIS_COLUMNS.contains(e.getKey())) {
				String stringValue = Utils.getStringFromObject(row.get(e.getKey()));
				
				BeanInfo info;
				try {
					info = Introspector.getBeanInfo(obj.getClass(), Object.class);
					PropertyDescriptor[] props = info.getPropertyDescriptors();  
					for (PropertyDescriptor pd : props) {  
						if (pd.getName().equalsIgnoreCase(e.getKey())) {  
							Method setter = pd.getWriteMethod();
					        setter.invoke(obj, stringValue);
					    }
					}  
				} catch (IntrospectionException ex1) {
					ex1.printStackTrace();
				} catch (IllegalAccessException ex2) {
					ex2.printStackTrace();
				} catch (IllegalArgumentException ex3) {
					ex3.printStackTrace();
				} catch (InvocationTargetException ex4) {
					ex4.printStackTrace();
				} 
			} else {
				HashMap<String, String> additionalAttribute = new HashMap<String, String>();
				additionalAttribute.put(e.getKey(), Utils.getStringFromObject(e.getValue()));
				additionalAttributes.add(additionalAttribute);
			}
		}
		obj.setATTRIBUTES(additionalAttributes);
		
		return obj;
	}
	
	public static RefPubObject buildRefPubObject (ArrayList<HashMap<String, Object>> results) {
		if (results.size() < 1) {
			return null;
		}
		return Utils.buildRefPubObject(results.get(0));
	}
	
	public static List<RefPubObject> buildRefPubObjectList (ArrayList<HashMap<String, Object>> results) {
		List<RefPubObject> refPubObjects = new ArrayList<RefPubObject>();
		
		
		for(HashMap<String,Object> row : results) {
			RefPubObject obj = Utils.buildRefPubObject(row);
			refPubObjects.add(obj);
		}
		
		return refPubObjects;
	}
	
	public static List<RefPubObject> buildRefPubObjectList (ArrayList<HashMap<String, Object>> results, boolean cleanDups) {
		List<RefPubObject> refPubObjects = Utils.buildRefPubObjectList(results);
		
		if (cleanDups) {
			List<Integer> duplicatedIdx = new ArrayList<Integer>();
			int counter1=0;
			for (RefPubObject o : refPubObjects) {
				for (int counter2 = counter1 + 1; counter2 < refPubObjects.size(); counter2++ ) {
					RefPubObject v = refPubObjects.get(counter2);
					if (o.getPKID().trim().equalsIgnoreCase(v.getPKID().trim())) {
						if (Utils.getAttributeFromList(o.getATTRIBUTES(), "PARENT_ID") != null) {
							int id1 = Integer.parseInt(Utils.getAttributeFromList(o.getATTRIBUTES(), "PARENT_ID"));
							if (Utils.getAttributeFromList(v.getATTRIBUTES(), "PARENT_ID") != null) {
								int id2 = Integer.parseInt(Utils.getAttributeFromList(v.getATTRIBUTES(), "PARENT_ID"));
								if (id2 > id1) {
									duplicatedIdx.add(counter1);
								}
								else if (id1 > id2) {
									duplicatedIdx.add(counter2);
								}
								else {
									duplicatedIdx.add(counter1);
								}
							}
						}
					}
				}
				counter1++;
			}
			Collections.sort(duplicatedIdx, new Comparator<Integer>() {
				   public int compare(Integer a, Integer b) {
				      return b.compareTo(a);
				   }
				});
			
			List<RefPubObject> cleanedObject = new ArrayList<RefPubObject>();
			int counter = 0;
			for (RefPubObject o : refPubObjects) {
				if (!duplicatedIdx.contains(counter)) {
					cleanedObject.add(o);
				}
				counter++;
			}
			return cleanedObject;
		}
		
		return refPubObjects;
	}
	
	public static List<RefPubObject> buildRefPubObjectHierarchyList(ArrayList<HashMap<String, Object>> results) {
		List<RefPubObject> refPubObjects = Utils.buildRefPubObjectList(results);
		
		List<RefPubObject> returnList = Utils.buildHierarchy(refPubObjects, new ArrayList<RefPubObject>());
		
		
		//removing first level objects from source
		for (RefPubObject o : returnList) {
			refPubObjects.remove(o);
		}
		
		return returnList;
	}
	
	private static List<RefPubObject> buildHierarchy(List<RefPubObject> source, List<RefPubObject> destination, int depth) {
		for (int i=2; i<=depth; i++) {
			for (RefPubObject o : source) {
				int level = Integer.parseInt(Utils.getAttributeFromList(o.getATTRIBUTES(), "LEVEL"));
				if (level==i) {
					String[] path = Utils.getAttributeFromList(o.getATTRIBUTES(), "PTH").split("/");
					String attachId = path[i-1];
					destination = Utils.attachObjectToRefPubTree(destination, o, attachId);
				}
			}
		}
		return destination;
	}
	
	private static List<RefPubObject> buildHierarchy(List<RefPubObject> source, List<RefPubObject> destination) {
		int maxDepth = 1;
		for (RefPubObject o : source) {
			int oDepth = Integer.parseInt(Utils.getAttributeFromList(o.getATTRIBUTES(), "LEVEL"));
			if (oDepth > maxDepth) {
				maxDepth = oDepth;
			}
			if (oDepth == 1) {
				destination.add(o);
			}
		}
		for (RefPubObject o : destination) {
			source.remove(o);
		}
		return Utils.buildHierarchy(source, destination, maxDepth);
	}
	
	public static List<RefPubObject> attachObjectToRefPubTree(List<RefPubObject> tree, RefPubObject item, String attachToId) {
		for (RefPubObject o : tree) {
			if (o.getPKID().equalsIgnoreCase(attachToId)) {
				if (o.getChildrens() != null && o.getChildrens().size() > 0) {
					List<RefPubObject> childs = o.getChildrens();
					childs.add(item);
					o.setChildrens(childs);
				} else {
					List<RefPubObject> childs = new ArrayList<RefPubObject>();
					childs.add(item);
					o.setChildrens(childs);
				}
			} else {
				if (o.getChildrens() != null && o.getChildrens().size() > 0) {
					Utils.attachObjectToRefPubTree(o.getChildrens(), item, attachToId);
				}
			}
		}
		return tree;
	}
	
	public static List<RefPubObject> attachObjectToRefPubTree(List<RefPubObject> tree, RefPubObject item) {
		int counter = 0;
		for (RefPubObject o : tree) {
			String parentId = Utils.getAttributeFromList(item.getATTRIBUTES(), "PARENT_ID");
			if (parentId.equalsIgnoreCase(o.getPKID())) {
				if (o.getChildrens() == null || o.getChildrens().size() < 1) {
					List<RefPubObject> childs = new ArrayList<RefPubObject>();
					childs.add(item);
					tree.get(counter).setChildrens(childs);
				} else {
					List<RefPubObject> childs = o.getChildrens();
					childs.add(item);
					tree.get(counter).setChildrens(childs);
				}
				return tree;
			} else {
				if (o.getChildrens() != null && o.getChildrens().size() > 0) {
					tree.get(counter).setChildrens(Utils.attachObjectToRefPubTree(o.getChildrens(), item));
				}
			}
			counter++;
		}
		return tree;
	}
	
	public static List<RefPubObject> findAllLeafs(List<RefPubObject> original, List<RefPubObject> result) {
		for (RefPubObject o : original) {
			if (o.getChildrens() == null || o.getChildrens().size() < 1) {
				result.add(o);
			} else {
				Utils.findAllLeafs(o.getChildrens(), result);
			}
		}
		return result;
	}
	
	public static String getAttributeFromList(List<HashMap<String,String>> attributes, String key) {
		for (HashMap<String, String> a : attributes) {
			if (a.containsKey(key)) {
				return a.get(key);
			}
		}
		return null;
	}
	
	public static List<RefPubObject> addChildrenToList(List<RefPubObject> orig, RefPubObject obj, String node) {
		
		int counter = 0;
		for (RefPubObject o : orig) {
			if (o.getPKID().equalsIgnoreCase(node)) {
				if (orig.get(counter).getChildrens() == null) {
					orig.get(counter).setChildrens(new ArrayList<RefPubObject>());
				}
				orig.get(counter).getChildrens().add(obj);
			} else if (o.getChildrens() != null && o.getChildrens().size() > 0) {
				orig.get(counter).setChildrens(Utils.addChildrenToList(orig.get(counter).getChildrens(), obj, node));
			}
			counter++;
		}
		return orig;		
	}
	
	public static List<RefPubObject> cleanUpGroupList(List<RefPubObject> orig, String meta) {
		return cleanUpHierarchy(cleanUpDeadChilds(orig, meta), 1);
	}
	
	public static List<RefPubObject> cleanUpGroupList(List<RefPubObject> orig) {
		return cleanUpHierarchy(orig, 1);
	}
	
	private static List<RefPubObject> cleanUpHierarchy(List<RefPubObject> tree, int depth) {
		int outerCounter = 0;
		for (RefPubObject node : tree) {
			if (!Utils.hasChildObject(node)) {
				int innerCounter = 0;
				for (RefPubObject comp : tree) {
					if (innerCounter != outerCounter) {
						if (Utils.hasChildObject(comp)) {
							if (Utils.hasObjectDeepCopyInTree(node, comp.getChildrens())) {
								System.out.println ("{" + Integer.toString(depth) + "} " + node.getPKID());
							}
							Utils.cleanUpHierarchy(comp.getChildrens(), depth++);
						}
					}
					innerCounter++;
				}
			} else {
				Utils.cleanUpHierarchy(node.getChildrens(), depth++);
			}
			outerCounter++;
		}
		
		return tree;
	}
	
	private static boolean hasObjectDeepCopyInTree(RefPubObject obj, List<RefPubObject> tree) {
		for (RefPubObject node : tree) {
			if (node.getPKID().equalsIgnoreCase(obj.getPKID())) {
				return true;
			}
			if (Utils.hasChildObject(node)) {
				return Utils.hasObjectDeepCopyInTree(obj, node.getChildrens());
			}
		}
		return false;
	}
	
	/*private static List<RefPubObject> cleanUpHierarchy(List<RefPubObject> orig) {
		List<Integer> indexToRemove = new ArrayList<Integer>();
		int counter1 = 0;
		for (RefPubObject o : orig) {
			if (!Utils.hasChildObject(o)) {
				int counter2 = 0;
				for (RefPubObject x : orig) {
					if (counter1 != counter2) {
						if (Utils.hasChildObject(x)) {
							if (Utils.findInTreeById(x.getChildrens(), o.getPKID()) != null) {
								indexToRemove.add(counter1);
							}
						}
					}
					counter2++;
				}
			} else {
				orig.get(counter1).setChildrens(Utils.cleanUpChildren(orig.get(counter1)));
				orig.get(counter1).setChildrens(Utils.cleanUpHierarchy(orig.get(counter1).getChildrens()));
			}
			counter1++;
		}
		List<RefPubObject> returnObject = new ArrayList<RefPubObject>();
		
		int counter=0;
		for (RefPubObject obj : orig) {
			boolean toRem = false;
			for (Integer idx : indexToRemove) {
				if (idx == counter) {
					toRem = true;
				}
			}
			if (!toRem) {
				returnObject.add(obj);
			}
			counter++;
		}
		return returnObject;
	}*/
	
	private static List<RefPubObject> cleanUpChildren(RefPubObject orig) {
		if (Utils.hasChildObject(orig)) {
			int counter = 0;
			List<RefPubObject> ret = new ArrayList<RefPubObject>();
			for (RefPubObject o : orig.getChildrens()) {
				ret.add(o);
			}
			for (RefPubObject o : orig.getChildrens()) {
				if (o.getPKID().equalsIgnoreCase(orig.getPKID())) {
					ret.remove(counter);
				} else {
					counter++;
				}
			}
			return ret;
		}
		return null;
	}
	
	private static RefPubObject findInTreeById(List<RefPubObject> tree, String id) {
		for (RefPubObject o : tree) {
			if (o.getPKID().equalsIgnoreCase(id)) {
				return o;
			} else {
				if (Utils.hasChildObject(o)) {
					RefPubObject temp = Utils.findInTreeById(o.getChildrens(), id);
					if (temp != null) {
						return temp;
					}
				}
			}
		}
		
		return null;
	}
	
	public static List<RefPubObject> buildAlphabeticalOnTree(List<RefPubObject> tree) {
		List<RefPubObject> leafs = new ArrayList<RefPubObject>();
		leafs = Utils.findAllLeafs(tree, leafs);
		return Utils.sortFlatList(leafs, "id");
	}
	
	public static List<RefPubObject> buildAlphabeticalOnTree(List<RefPubObject> tree, String sortBy) {
		List<RefPubObject> leafs = new ArrayList<RefPubObject>();
		leafs = Utils.findAllLeafs(tree, leafs);
		return Utils.sortFlatList(leafs, sortBy);
	}
	
	public static List<RefPubObject> buildAlphabeticalOnTree(List<RefPubObject> tree, String sortBy, boolean removeDuplicates) {
		List<RefPubObject> leafs = new ArrayList<RefPubObject>();
		leafs = Utils.findAllLeafs(tree, leafs);
		if (removeDuplicates) {
			leafs = Utils.removeDuplicatesFromFlatList(leafs);
		}
		return Utils.sortFlatList(leafs, sortBy);
	}
	
	public static List<RefPubObject> sortFlatList(List<RefPubObject> list, String sortBy) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		int counter = 0;
		for (RefPubObject l : list) {
			if (sortBy.equalsIgnoreCase("pkid") || sortBy.equalsIgnoreCase("id")) {
				map.put(counter, l.getPKID());
			} else if (sortBy.equalsIgnoreCase("name_e")) {
				map.put(counter, l.getNAME_E());
			} else if (sortBy.equalsIgnoreCase("name_f")) {
				map.put(counter, l.getNAME_F());
			} else if (sortBy.equalsIgnoreCase("name_s")) {
				map.put(counter, l.getNAME_S());
			} else if (sortBy.equalsIgnoreCase("name_r")) {
				map.put(counter, l.getNAME_R());
			} else if (sortBy.equalsIgnoreCase("name_A")) {
				map.put(counter, l.getNAME_A());
			} else if (sortBy.equalsIgnoreCase("name_c")) {
				map.put(counter, l.getNAME_C());
			} else if (sortBy.equalsIgnoreCase("long_name_e")) {
				map.put(counter, l.getLONG_NAME_E());
			} else if (sortBy.equalsIgnoreCase("long_name_f")) {
				map.put(counter, l.getLONG_NAME_F());
			} else if (sortBy.equalsIgnoreCase("long_name_s")) {
				map.put(counter, l.getLONG_NAME_S());
			} else if (sortBy.equalsIgnoreCase("long_name_r")) {
				map.put(counter, l.getLONG_NAME_R());
			} else if (sortBy.equalsIgnoreCase("long_name_A")) {
				map.put(counter, l.getLONG_NAME_A());
			} else if (sortBy.equalsIgnoreCase("long_name_c")) {
				map.put(counter, l.getLONG_NAME_C());
			}			
			counter++;
		}
		Map<Integer, String> sortedList = Utils.sortByValue(map);
		List<RefPubObject> returnList = new ArrayList<RefPubObject>();
		for (Integer key : sortedList.keySet()) {
	        returnList.add(list.get(key));
	    }
		return returnList;
	}
	
	private static List<RefPubObject> removeDuplicatesFromFlatList(List<RefPubObject> tree) {
		List<Integer> dupsIdx = new ArrayList<Integer>();
		for (int i = 0; i < tree.size(); i++) {
			for (int j = i+1; j < tree.size(); j++) {
				if (tree.get(i).getPKID().equalsIgnoreCase(tree.get(j).getPKID())) {
					dupsIdx.add(j);
				}
			}
		}
		Collections.sort(dupsIdx, new Comparator<Integer>() {
			   public int compare(Integer a, Integer b) {
			      return b.compareTo(a);
			   }
			});
		List<RefPubObject> returnObject = new ArrayList<RefPubObject>();
		
		int counter=0;
		for (RefPubObject obj : tree) {
			boolean toRem = false;
			for (Integer idx : dupsIdx) {
				if (idx == counter) {
					toRem = true;
				}
			}
			if (!toRem) {
				returnObject.add(obj);
			}
			counter++;
		}
		
		return returnObject;
		
	}
	
	private static Map<Integer, String> sortByValue(Map map) {
	     List list = new LinkedList(map.entrySet());
	     Collections.sort(list, new Comparator() {
	          public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	              .compareTo(((Map.Entry) (o2)).getValue());
	          }
	     });

	    Map result = new LinkedHashMap();
	    for (Iterator it = list.iterator(); it.hasNext();) {
	        Map.Entry entry = (Map.Entry)it.next();
	        result.put(entry.getKey(), entry.getValue());
	    }
	    return result;
	} 
	
	private static List<RefPubObject> cleanUpDeadChilds(List<RefPubObject> orig, String meta) {
		List<Integer> indexToRemove = new ArrayList<Integer>();
		Integer counter = 0;
		for (RefPubObject obj : orig) {
			if (!Utils.hasChildObject(obj)) {
				if (obj.isIs_group())
					indexToRemove.add(counter);
			} else {
				orig.get(counter).setChildrens(Utils.cleanUpDeadChilds(orig.get(counter).getChildrens(), meta));
			}
			counter++;
		}
		Collections.sort(indexToRemove, new Comparator<Integer>() {
		   public int compare(Integer a, Integer b) {
		      return b.compareTo(a);
		   }
		});
		List<RefPubObject> returnObject = new ArrayList<RefPubObject>();
		
		counter=0;
		for (RefPubObject obj : orig) {
			boolean toRem = false;
			for (Integer idx : indexToRemove) {
				if (idx == counter) {
					toRem = true;
				}
			}
			if (!toRem) {
				returnObject.add(obj);
			}
			counter++;
		}
		
		return returnObject;
	}
	
	public static List<RefPubObject> setUpCodeListOnTree(List<RefPubObject> tree, MDCodelist codelist, String concept) {
		
		int counter = 0;
		for (RefPubObject o : tree) {
			CodeListDAO clobj = new CodeListDAO();
			clobj.setName(codelist.getCode_name());
			clobj.setIsDefault(1);
			clobj.setValue(o.getPKID());
			List<CodeListDAO> ncl = new ArrayList<CodeListDAO>();
			ncl.add(clobj);
			tree.get(counter).setConcept(concept);
			tree.get(counter).setCodeList(ncl);
			if (o.getChildrens() != null && o.getChildrens().size() > 0) {
				tree.get(counter).setChildrens(Utils.setUpCodeListOnTree(o.getChildrens(), codelist, concept));
			}
			counter++;
		}
		return tree;
	}
	
	public static List<RefPubObject> setUpURIOnTree(List<RefPubObject> tree, URI uri) {
		
		int counter = 0;
		for (RefPubObject o : tree) {
			tree.get(counter).setCurrentURI(uri);
			if (o.getChildrens() != null && o.getChildrens().size() > 0) {
				tree.get(counter).setChildrens(Utils.setUpURIOnTree(o.getChildrens(), uri));
			}
			counter++;
		}
		return tree;
	}
	
	public static boolean hasChildObject(RefPubObject obj) {
		if (obj.getChildrens() != null && obj.getChildrens().size() > 0) {
			for (RefPubObject x : obj.getChildrens()) {
				if (!x.isIs_group()) {
					return true;
				} else {
					if (Utils.hasChildObject(x)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static List<RefPubObject> setUpUpGroupList(List<RefPubObject> orig, String meta) {
		int counter = 0;
		for (RefPubObject obj : orig) {
			if (obj.getMETA().equalsIgnoreCase(meta)) {
				orig.get(counter).setIs_group(false);
			} else {
				orig.get(counter).setIs_group(true);
			}
			if (obj.getChildrens() != null && obj.getChildrens().size() > 0) {
				orig.get(counter).setChildrens(Utils.setUpUpGroupList(orig.get(counter).getChildrens(), meta));
			}
			counter++;
		}
		return orig;
	}
	
	public static void debug_PrintTree(List<RefPubObject> tree) {
		Utils.debug_PrintTree(tree, 1);
	}
	
	private static void debug_PrintTree(List<RefPubObject> tree, int depth) {
		for (RefPubObject node : tree) {
			String dash = "--";
			String id = "(" + node.getPKID() + ")";
			if (node.getFULL_NAME_E() != null && !node.getFULL_NAME_E().trim().equalsIgnoreCase("")) {
				System.out.println(StringUtils.repeat(dash, depth) + " " + id + " " + node.getFULL_NAME_E());
			} else if (node.getNAME_E() != null && !node.getNAME_E().trim().equalsIgnoreCase("")) {
				System.out.println(StringUtils.repeat(dash, depth) + " " + id + " " + node.getNAME_E());
			}
			if (Utils.hasChildObject(node)) {
				Utils.debug_PrintTree(node.getChildrens(), depth + 1);
			}
		}
	}
}