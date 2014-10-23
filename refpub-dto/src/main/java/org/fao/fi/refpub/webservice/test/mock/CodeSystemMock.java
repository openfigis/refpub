package org.fao.fi.refpub.webservice.test.mock;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fao.fi.refpub.webservice.CodeDTO;
import org.fao.fi.refpub.webservice.CodeSystemDTO;
import org.fao.fi.refpub.webservice.MultilingualTypeDTO;
import org.fao.fi.refpub.webservice.TextTypeDTO;

import au.com.bytecode.opencsv.CSVReader;

public class CodeSystemMock {

	public static String csvFileName = "src/main/resources/CL_SPECIES_1_3.csv";
	public final static String CODE = "asfis";

	private static final CodeSystemDTO cl = new CodeSystemDTO();
	static {
		try {
			Reader r = new InputStreamReader(CodeSystemMock.class.getClassLoader().getResourceAsStream(
					"CL_SPECIES_1_3.csv"));
			CSVReader reader = new CSVReader(r, '\t');
			reader.readNext();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				CodeDTO code = new CodeDTO();
				String codeValue = nextLine[2];
				if (!StringUtils.isBlank(codeValue)) {
					code.setCode(codeValue);
					code.setResourceUrl(ResourceUrlMock.create("concept/species/codesystem/asfis/code") + codeValue);
					MultilingualTypeDTO l = new MultilingualTypeDTO();
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

	public static CodeSystemDTO create() {
		cl.setCodelist(CODE);
		cl.setMultilingualName(MultilingualTypeMock.create());
		cl.setResourceUrl(ResourceUrlMock.create("concept/species/codesystem") + CODE);
		return cl;
	}

	private static void add2Codes(List<TextTypeDTO> names, String lang, String v) {
		if (!StringUtils.isBlank(v)) {
			TextTypeDTO t = new TextTypeDTO();
			t.setLang(lang);
			t.setValue(v);
			names.add(t);
		}
	}
}
