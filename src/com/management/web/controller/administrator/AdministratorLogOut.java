package com.management.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员登出功能
 * 需要存在的参数：
 * 	session：
 *		admin(管理员姓名)
 *		permission(管理员权限)
 * 
 * @author CheungChingYin
 *
 */
@WebServlet("/AdministratorLogOut")
public class AdministratorLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {//防止非法入侵
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		session.removeAttribute("admin");//移除session域的管理员名字
		session.removeAttribute("permission");//移除session域的管理员权限
		String path = request.getContextPath();
		response.sendRedirect(path+"/Login");//重定向回登录页面
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
