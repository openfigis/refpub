package org.fao.fi.refpub.webservice.impl;

import java.util.List;

import org.fao.fi.refpub.dao.objects.RefPubObject;
import org.fao.fi.refpub.webservice.ParentList;
import org.fao.fi.refpub.webservice.TextTypeValueList;
import org.fao.fi.refpub.webservice.TextTypeValuePair;

public class HierarchyListTypeDTO {

	public static ParentList create(List<RefPubObject> parents) {
		ParentList pl = new ParentList();
		
		for (RefPubObject obj : parents) {
			TextTypeValueList txvl = new TextTypeValueList();
			
			txvl.getNames().add(obj.getGroup_name());
			
			if (obj.getScientific_name() != null && !obj.getScientific_name().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("scientific_name");
				txvp.setValue(obj.getScientific_name());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_a() != null && !obj.getName_a().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_a");
				txvp.setValue(obj.getName_a());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_e() != null && !obj.getName_e().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_e");
				txvp.setValue(obj.getName_e());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_f() != null && !obj.getName_f().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_f");
				txvp.setValue(obj.getName_f());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_r() != null && !obj.getName_r().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_r");
				txvp.setValue(obj.getName_r());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_s() != null && !obj.getName_s().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_s");
				txvp.setValue(obj.getName_s());
				txvl.getValues().add(txvp);
			}
			if (obj.getName_c() != null && !obj.getName_c().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("name_c");
				txvp.setValue(obj.getName_c());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_a() != null && !obj.getLong_name_a().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_c");
				txvp.setValue(obj.getLong_name_a());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_e() != null && !obj.getLong_name_e().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_e");
				txvp.setValue(obj.getLong_name_e());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_f() != null && !obj.getLong_name_f().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_f");
				txvp.setValue(obj.getLong_name_f());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_r() != null && !obj.getLong_name_r().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_r");
				txvp.setValue(obj.getLong_name_r());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_s() != null && !obj.getLong_name_s().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_s");
				txvp.setValue(obj.getLong_name_s());
				txvl.getValues().add(txvp);
			}
			if (obj.getLong_name_c() != null && !obj.getLong_name_c().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_name_c");
				txvp.setValue(obj.getLong_name_c());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_a() != null && !obj.getFull_name_a().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_c");
				txvp.setValue(obj.getFull_name_a());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_e() != null && !obj.getFull_name_e().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_e");
				txvp.setValue(obj.getFull_name_e());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_f() != null && !obj.getFull_name_f().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_f");
				txvp.setValue(obj.getFull_name_f());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_r() != null && !obj.getFull_name_r().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_r");
				txvp.setValue(obj.getFull_name_r());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_s() != null && !obj.getFull_name_s().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_s");
				txvp.setValue(obj.getFull_name_s());
				txvl.getValues().add(txvp);
			}
			if (obj.getFull_name_c() != null && !obj.getFull_name_c().trim().equals("")) {
				TextTypeValuePair txvp = new TextTypeValuePair();
				txvp.setName("long_full_c");
				txvp.setValue(obj.getFull_name_c());
				txvl.getValues().add(txvp);
			}
			
			pl.getValues().add(txvl);
		}
		
		return pl;
	}
}
