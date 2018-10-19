package com.management.web.controller.college;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.College;
import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;

@WebServlet("/getCollege")
public class GetCollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		CollegeService service = new CollegeServiceImpl();
		List<College> list = service.searchAllCollege();
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String college = gson.toJson(list);
		out.print(college);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
