package org.openmrs.module.cmhie.servlet;

import org.openmrs.PersonAttribute;
import org.openmrs.User;
import org.openmrs.api.context.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Created by saptpurk on 4/21/2017.
 */
public class CmhieServlet extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        IOException {
		try {
			if (null != request.getParameter("username")) {
				String username = request.getParameter("username");
				String daemonUsername = Context.getAdministrationService().getGlobalProperty("cmhie.daemon.username");
				String daemonPassword = Context.getAdministrationService().getGlobalProperty("cmhie.daemon.zpassword");
				Context.authenticate(daemonUsername, daemonPassword);
				User userByUsername = Context.getUserService().getUserByUsername(username);
				if (null != userByUsername) {
					if (null != request.getParameter("passPhrase")) {
						String passPhrase = request.getParameter("passPhrase");
						String cmhiePhrase = userByUsername.getUserProperty("cmhiePhrase");
						if (passPhrase.equals(cmhiePhrase)) {
							Context.becomeUser(userByUsername.getSystemId());
							Integer personId = userByUsername.getPerson().getPersonId();
							response.sendRedirect("/openmrs/patientDashboard.form?patientId=" + personId);
						}
					} else {
						sendSms(userByUsername);
						Context.logout();
						response.getWriter().write("FOUND");
					}
				} else {
					Context.logout();
					response.getWriter().write("NOTFOUND");
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
	
	public void sendSms(User patientUser) {
		PersonAttribute smsNumber = patientUser.getPerson().getAttribute(8);
		
		String accountSid = Context.getAdministrationService().getGlobalProperty("cmhie.sms.accountSid");
		String authToken = Context.getAdministrationService().getGlobalProperty("cmhie.sms.authToken");
		String smsProviderNumber = Context.getAdministrationService().getGlobalProperty("cmhie.sms.smsProviderNumber");
		
		Twilio.init(accountSid, authToken);
		String phrase = randomWords();
		Message message = Message.creator(new PhoneNumber(smsNumber.getValue()), new PhoneNumber(smsProviderNumber), phrase)
		        .create();
		System.out.println("PHRASE = " + phrase);
		patientUser.setUserProperty("cmhiePhrase", phrase.trim());
		Context.getUserService().saveUser(patientUser);
	}
	
	final static String[] dictionary = { "best", "cool", "happy", "new", "champion", "food", "laptop", "hello", "cocoa",
	        "coffee", "cactus", "blinds", "printer", "chair", "basket", "cards", "calendar", "wires", "table", "papers" };
	
	public static String randomWords() {
		String phrase = new String();
		for (int i = 0; i < 3; i++) {
			Random rand = new Random();
			int x = rand.nextInt((19 - 0) + 1) + 0;
			phrase = phrase + " " + dictionary[x];
		}
		return phrase;
	}
}
