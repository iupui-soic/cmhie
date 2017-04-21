package org.openmrs.module.cmhie.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsUtil;

/**
 * Created by saptpurk on 4/21/2017.
 */
public class CmhieServlet extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	        IOException {
		try {
			if (null != request.getParameter("username")) {
				String username = request.getParameter("username");
				User userByUsername = Context.getUserService().getUserByUsername(username);
				if (null != userByUsername) {
					sendSms(userByUsername);
					response.getWriter().write("FOUND");
				} else {
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
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
	
	public void sendSms(User patientUser) {
		PersonAttribute smsNumber = patientUser.getPerson().getAttribute(8);
		System.out.println("SENDING SMS to " + patientUser.getName() + " === " + smsNumber.getValue());
	}
}
