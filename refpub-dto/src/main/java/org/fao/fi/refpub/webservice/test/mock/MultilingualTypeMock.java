package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.MultilingualType;
import org.fao.fi.refpub.webservice.TextType;

public class MultilingualTypeMock {

	public static MultilingualType create() {

		MultilingualType n = new MultilingualType();
		TextType en = new TextType();
		en.setLang("en");
		en.setText("In the English Language");

		TextType fr = new TextType();
		fr.setLang("fr");
		fr.setText("En langue francaise");

		n.getTexts().add(en);
		n.getTexts().add(fr);

		return n;
	}

}
