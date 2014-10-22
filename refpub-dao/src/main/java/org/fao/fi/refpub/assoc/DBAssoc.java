package org.fao.fi.refpub.assoc;

import java.util.HashMap;

public class DBAssoc {
	public final static HashMap<String, HashMap<String, String>> GROUP_TABLE_ASSOC = new HashMap<String, HashMap<String, String>>();
	static
	{
		GROUP_TABLE_ASSOC.put("FIC_ITEM",      new HashMap<String,String>());
		GROUP_TABLE_ASSOC.get("FIC_ITEM").put("GROUP_TABLE", "FIC_ITEM_GRP_BASE");
		GROUP_TABLE_ASSOC.get("FIC_ITEM").put("ID", "FIC_ITEM");
		
		GROUP_TABLE_ASSOC.put("AREA",          new HashMap<String,String>());
		GROUP_TABLE_ASSOC.get("AREA").put("GROUP_TABLE", "AREA_YR_AGG_GRP_BASE");
		GROUP_TABLE_ASSOC.get("AREA").put("ID", "AREA");
		
		GROUP_TABLE_ASSOC.put("REF_GEAR_TYPE", new HashMap<String,String>());
		GROUP_TABLE_ASSOC.get("REF_GEAR_TYPE").put("GROUP_TABLE", "REF_GEAR_TYPE_GRP");
		GROUP_TABLE_ASSOC.get("REF_GEAR_TYPE").put("ID", "CD_GEAR_TYPE");
	}

}
