package com.management.web.controller.major;

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
import com.management.entities.Major;
import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;

/**
 * 根据学院获得相对应的专业
 * 需要传入参数:
 * 		request:
 * 			collegeId(学院ID)
 * @author CheungChingYin
 *
 */
@WebServlet("/getMajor")
public class GetMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		Integer collegeId = Integer.parseInt(request.getParameter("collegeId"));
		MajorService service = new MajorServiceImpl();
		List<Major> list = service.searchMajorByCollegeId(collegeId);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String major = gson.toJson(list);
		out.print(major);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
