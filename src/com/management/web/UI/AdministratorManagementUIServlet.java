package com.management.web.UI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 跳转到管理员管理页面
 * @author CheungChingYin
 *
 */
@WebServlet("/AdministratorManagement")
public class AdministratorManagementUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		Integer permission =(Integer) session.getAttribute("permission");
		if(permission == 1){//只有管理员权限为1的时候才能进入管理员管理页面
			request.getRequestDispatcher("/WEB-INF/jsp/AdministratorManagement.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
