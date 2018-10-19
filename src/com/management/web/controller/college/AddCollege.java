package com.management.web.controller.college;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.College;
import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;
import com.management.utils.WebUtils;

/**
 * 学院添加功能
 * 需要传入参数：
 * 	学院添加表单：
 * 		id(学院ID)
 * 		name(学院名称)
 * @author CheungChingYin
 *
 */
@WebServlet("/addCollege")
public class AddCollege extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		CollegeService service = new CollegeServiceImpl();
		College college = WebUtils.request2Bean(request, College.class);
		service.RegistCollege(college);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
