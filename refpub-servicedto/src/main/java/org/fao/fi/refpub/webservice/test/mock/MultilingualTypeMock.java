package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.MultilingualTypeDTO;
import org.fao.fi.refpub.webservice.TextTypeDTO;


public class MultilingualTypeMock {

	public static MultilingualTypeDTO create() {

		MultilingualTypeDTO n = new MultilingualTypeDTO();
		TextTypeDTO en = new TextTypeDTO();
		en.setLang("en");
		en.setValue("In the English Language");

		TextTypeDTO fr = new TextTypeDTO();
		fr.setLang("fr");
		fr.setValue("En langue francaise");

		n.getTexts().add(en);
		n.getTexts().add(fr);

		return n;
	}

}
