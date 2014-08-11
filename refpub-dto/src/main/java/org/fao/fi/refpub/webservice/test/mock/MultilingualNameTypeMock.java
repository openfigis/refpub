package org.fao.fi.refpub.webservice.test.mock;

import org.fao.fi.refpub.webservice.MultilingualNameType;
import org.fao.fi.refpub.webservice.TextType;

public class MultilingualNameTypeMock {

	public static MultilingualNameType create() {

		MultilingualNameType n = new MultilingualNameType();
		TextType en = new TextType();
		en.setLang("en");
		en.setTitle("In the English Language");

		TextType fr = new TextType();
		fr.setLang("fr");
		fr.setTitle("En langue francaise");

		n.getTexts().add(en);
		n.getTexts().add(fr);

		return n;
	}

}
