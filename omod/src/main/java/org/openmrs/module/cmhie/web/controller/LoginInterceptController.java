/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0 + Health disclaimer. If a copy of the MPL was not distributed with
 * this file, You can obtain one at http://license.openmrs.org
 */
package org.openmrs.module.cmhie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This controller intercepts the /login.htm URL and handles that request. This takes 
 * precedence over annotation-based controller that is used in the Reference application. 
 * The controller checks if a casticket parameter is passed. If this is passed then 
 * casticket is validation by using a HttpURLConnection and if the response contains "yes" 
 * and username, then it logins as that username.
 * See details of CAS authentication - https://kb.iu.edu/d/atfc
 * @author sunbiz
 */
@Controller
public class LoginInterceptController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginIntercept(HttpServletRequest req, HttpServletResponse res) {
		return "redirect:/moduleResources/cmhie/Patient_login.html";
	}
}
