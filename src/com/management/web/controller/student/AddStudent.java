package com.management.web.controller.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.Student;
import com.management.service.StudentService;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.WebUtils;

@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		StudentService service = new StudentServiceImpl();
		Student student = WebUtils.request2Bean(request, Student.class);
		service.addStudent(student);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String error = gson.toJson(student.getErrors());
		out.print(error);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
