package com.management.web.controller.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.service.StudentService;
import com.management.service.impl.StudentServiceImpl;

/**
 * 学生删除功能
 * 需要传入参数
 * 	reuqest:
 * 		id(学生学号)
 * @author CheungChingYin
 *
 */
@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		String stuId = request.getParameter("id");
		StudentService service = new StudentServiceImpl();
		if(stuId != null){
			service.deleteStudent(stuId);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
