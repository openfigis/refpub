package org.fao.fi.refpub.dao.utils;

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
			String column = cl.getCode_column();
			if (column.equalsIgnoreCase("ALPHA3CODE")) {
				clobj.setName(cl.getCode_name());
				clobj.setValue(obj.getAlpha3code());
				clobj.setIsDefault(cl.getIsDefault());
			}
			if (column.equalsIgnoreCase("AREA")) {
				clobj.setName(cl.getCode_name());
				clobj.setValue(obj.getArea());
				clobj.setIsDefault(cl.getIsDefault());
			}
			if (column.equalsIgnoreCase("FIC_ITEM")) {
				clobj.setName(cl.getCode_name());
				clobj.setValue(obj.getFic_item());
				clobj.setIsDefault(cl.getIsDefault());
			}
			if (column.equalsIgnoreCase("FIC_SYS_ITEM")) {
				clobj.setName(cl.getCode_name());
				clobj.setValue(obj.getFic_sys_item());
				clobj.setIsDefault(cl.getIsDefault());
			}
			if (column.equalsIgnoreCase("ISO_2_CODE")) {
				clobj.setName(cl.getCode_name());
				clobj.setValue(obj.getIso_2_code());
				clobj.setIsDefault(cl.getIsDefault());
			}
			if (column.equalsIgnoreCase("ISO_3_CODE")) {
				clobj.setName(cl.getCode_name());
				clobj.setValue(obj.getIso_3_code());
				clobj.setIsDefault(cl.getIsDefault());
			}
			codemap.add(clobj);
		}
		return codemap;
	}
}
