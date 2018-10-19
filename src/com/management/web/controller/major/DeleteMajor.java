package com.management.web.controller.major;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;

@WebServlet("/deleteMajor")
public class DeleteMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if(id == null){
			return;
		}
		MajorService service = new MajorServiceImpl();
		service.deleteMajor(Integer.parseInt(id));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
