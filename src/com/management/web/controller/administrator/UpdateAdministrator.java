package com.management.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Administrator;
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;
import com.management.utils.WebUtils;

@WebServlet("/updateAdministrator")
public class UpdateAdministrator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		AdministratorService service = new AdministratorServiceImpl();
		Administrator admin = WebUtils.request2Bean(request, Administrator.class);
		service.alertAdministrator(admin);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
