package com.management.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Administrator;
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;
import com.management.utils.WebUtils;

/**
 * 管理员登录功能
 * 需要传入参数
 * 	request:
 * 		登录界面表单:
 * 			user(管理员姓名)
 * 			password(管理员密码)
 * 			rememberMe(可选)(记住我选项)
 * 		
 * @author CheungChingYin
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Administrator admin = WebUtils.request2Bean(request, Administrator.class);//获得表单传来的参数
		AdministratorService service = new AdministratorServiceImpl();
		HttpSession session = request.getSession();
		
		String user = admin.getUser();
		String password = admin.getPassword();
		response.setHeader("Content-type","text/html;charset=UTF-8");

		boolean loginResult = service.login(user, password);//检查用户名、密码能否通过登录
		Integer permission = service.searchAdministratorByName(user).getPermission();
		if (loginResult) {//登录通过执行事件
			if (request.getParameter("remeberMe") != null && request.getParameter("remeberMe").equals("on")) {
				//检查是否勾选了记住我，需要先检查获取是否为空，不然会报空指针异常
				session.setAttribute("admin", user);
				session.setAttribute("permission",permission);
				session.setMaxInactiveInterval(7 * 24 * 3600);// Session保存7天
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(7 * 24 * 3600);// cookie的有效期也为7天
				cookie.setPath("/");
				response.addCookie(cookie);//设置Cookie
				response.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+request.getContextPath()+"/Home'</script>");
			} else {
				//没有勾选“记住我”,使用非cookie功能登录
				session.setAttribute("admin", user);
				session.setAttribute("permission",permission);
				response.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+request.getContextPath()+"/Home'</script>");
			}
		} else {//登录失败
			response.getWriter().write("<script language='JavaScript'>alert('您的用户名或密码有误，请重新输入或者注册');window.location.href='"+request.getContextPath()+"/Login'</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
