package org.fao.fi.refpub.dao.utils;

import java.util.ArrayList;
import java.util.List;

public class StandardFigisDBColumns {
	
	public static List<String> FIGIS_COLUMNS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
	    	add("PKID");
	        add("META");
	        
	        add("NAME_E");
	        add("NAME_F");
	        add("NAME_S");
	        add("NAME_R");
	        add("NAME_A");
	        add("NAME_C");
	        
	        add("OFFICIAL_NAME_E");
	        add("OFFICIAL_NAME_F");
	        add("OFFICIAL_NAME_S");
	        add("OFFICIAL_NAME_R");
	        add("OFFICIAL_NAME_A");
	        add("OFFICIAL_NAME_C");
	        
	        add("FULL_NAME_E");
	        add("FULL_NAME_F");
	        add("FULL_NAME_S");
	        add("FULL_NAME_R");
	        add("FULL_NAME_A");
	        add("FULL_NAME_C");
	        
	        add("LONG_NAME_E");
	        add("LONG_NAME_F");
	        add("LONG_NAME_S");
	        add("LONG_NAME_R");
	        add("LONG_NAME_A");
	        add("LONG_NAME_C");
	        
	        add("SHORT_DESC_E");
	        add("SHORT_DESC_F");
	        add("SHORT_DESC_S");
	        add("SHORT_DESC_R");
	        add("SHORT_DESC_A");
	        add("SHORT_DESC_C");
	    }
	}; 

}
