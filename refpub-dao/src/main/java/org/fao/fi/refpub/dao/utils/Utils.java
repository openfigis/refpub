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
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;

public class Utils {
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
		        	/*Class<?> type = pd.getPropertyType();*/  
		  
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
}