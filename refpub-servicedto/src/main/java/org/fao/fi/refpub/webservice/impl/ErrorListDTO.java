package org.fao.fi.refpub.webservice.impl;

import org.fao.fi.refpub.webservice.SystemError;

public class ErrorListDTO {
	public static SystemError create(Exception e) {
		SystemError se = new SystemError();
		se.getErrors().add(e.getMessage());
		
		StackTraceElement[] stack = e.getStackTrace();
		String theTrace = "";
		for(StackTraceElement line : stack) {
		   theTrace += line.toString() + "\n";
		}
		
		se.getStacktraces().add(theTrace);
		
		return se;
	}
}
