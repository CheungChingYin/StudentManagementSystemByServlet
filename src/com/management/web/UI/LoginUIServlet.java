package com.management.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "跳转到登录界面", urlPatterns = { "/Login" })
public class LoginUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {// 判断是否存在第一次登录时存放的cookie
					if (session.getAttribute("admin") != null) {// 判断服务器里面的存放的cookie是否存在
						// 直接重定向到主界面
						response.sendRedirect(request.getContextPath() + "/Home");
						return;
					}
				}
			}
		}
		// 如果检测不到cookie就转发到登录界面
		request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
