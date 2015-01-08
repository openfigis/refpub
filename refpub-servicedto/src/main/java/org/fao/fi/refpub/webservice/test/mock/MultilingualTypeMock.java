package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.MultilingualType;
import org.fao.fi.refpub.webservice.TextLang;


public class MultilingualTypeMock {

	public static MultilingualType create() {

		MultilingualType n = new MultilingualType();
		TextLang en = new TextLang();
		en.setLang("en");
		en.setValue("In the English Language");

		TextLang fr = new TextLang();
		fr.setLang("fr");
		fr.setValue("En langue francaise");

		/*n.getValues().add(en);
		n.getValues().add(fr);*/

		return n;
	}

}
