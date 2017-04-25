package org.openmrs.module.cmhie.servlet;

import org.junit.Test;

/**
 *
 * @author saptpurk
 */
public class CmhieServletTest {
	
	public CmhieServletTest() {
	}
	
	@Test
	public void testRandomWords() {
		String randomWords = CmhieServlet.randomWords();
		System.out.println("phrase ===> " + randomWords);
	}
}
