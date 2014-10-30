package org.fao.fi.refpub.dao.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.fao.fi.refpub.dao.objects.CodeListDAO;
import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.dao.objects.chunks.MDCodelist;

public class Utils {
	public static List<CodeListDAO> retrieveCodeListForObject(List<MDCodelist> codelists, RefPubObject obj) {
		List<CodeListDAO> codemap = new ArrayList<CodeListDAO>();
		for (MDCodelist cl : codelists) {
			CodeListDAO clobj = new CodeListDAO();
			clobj.setName(cl.getCode_name());
			clobj.setValue(getBeanProperty(obj, cl.getCode_column()));
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
		        	return (String) value; 
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
}
