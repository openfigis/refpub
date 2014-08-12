package org.fao.fi.refpub.webservice.test.mock;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fao.fi.refpub.webservice.Code;
import org.fao.fi.refpub.webservice.CodeSystem;
import org.fao.fi.refpub.webservice.MultilingualType;
import org.fao.fi.refpub.webservice.TextType;

import au.com.bytecode.opencsv.CSVReader;

public class CodeSystemMock {

	public static String csvFileName = "src/main/resources/CL_SPECIES_1_3.csv";
	public final static String CODE = "asfis";
	private static final CodeSystem cl = new CodeSystem();
	static {
		try {
			Reader r = new InputStreamReader(CodeSystemMock.class.getClassLoader().getResourceAsStream(
					"CL_SPECIES_1_3.csv"));
			CSVReader reader = new CSVReader(r, '\t');
			reader.readNext();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Code code = new Code();
				String codeValue = nextLine[0];
				if (!StringUtils.isBlank(codeValue)) {
					code.setCode(codeValue);
					code.setResourceUrl(ResourceUrlMock.create("concept/asfis/code") + codeValue);

					MultilingualType l = new MultilingualType();
					code.setName(l);

					add2Codes(code.getName().getTexts(), "en", nextLine[4]);
					add2Codes(code.getName().getTexts(), "fr", nextLine[5]);
					add2Codes(code.getName().getTexts(), "es", nextLine[6]);
					add2Codes(code.getName().getTexts(), "la", nextLine[7]);
					add2Codes(code.getName().getTexts(), "fr", nextLine[8]);
					cl.getCodes().add(code);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static CodeSystem create() {
		cl.setCode(CODE);
		cl.setMultilingualName(MultilingualTypeMock.create());
		cl.setResourceUrl(ResourceUrlMock.create("codesystem") + CODE);
		return cl;
	}

	private static void add2Codes(List<TextType> names, String lang, String v) {
		if (!StringUtils.isBlank(v)) {
			TextType t = new TextType();
			t.setLang(lang);
			t.setText(v);
			names.add(t);
		}
	}
}
