package com.management.web.UI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 跳转到主页面
 * @author CheungChingYin
 *
 */
@WebServlet(description="Home转发页面" ,value="/Home")
public class HomeUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("admin") != null){
			request.getRequestDispatcher("/WEB-INF/jsp/Ajax_Main.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath() + "/Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
