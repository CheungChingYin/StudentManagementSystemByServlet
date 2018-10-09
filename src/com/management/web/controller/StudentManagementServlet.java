package com.management.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Student;
import com.management.service.StudentService;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.ConstantUtils;
import com.management.utils.PageUtils;

@WebServlet("/StudentManagement")
public class StudentManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		StudentService service = new StudentServiceImpl();

		// 处理页码
		Integer listCount = service.countStudents();
		Integer page = Integer.parseUnsignedInt(request.getParameter("page"));
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(prePage, listCount);
		Integer pages = PageUtils.pagesHandler(listCount);
		List<Student> list = null;
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		if(page == pages){
			list = service.queryAllStudent().subList((page - 1) * 10, listCount);
		}else{
			list = service.queryAllStudent().subList((page - 1) * 10, page * 10);
		}

		request.setAttribute("studentList", list);
		request.setAttribute("allStudentCount", service.countStudents());
		request.setAttribute("prePage", prePage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/WEB-INF/jsp/StudentManagement.jsp").forward(request, response);
		;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
