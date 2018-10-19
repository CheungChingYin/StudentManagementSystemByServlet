package com.management.web.controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;
import com.management.utils.PasswordEncryptionUtils;

/**
 * 管理员修改密码
 * 需要传入参数
 * 	request:
 * 		管理员修改表单：
 * 			id(管理员ID)
 * 			user(管理员姓名)
 * 			password(管理员密码)
 * @author CheungChingYin
 *
 */
@WebServlet("/updateAdministratorPassword")
public class UpdateAdministratorPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Integer id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		AdministratorService service  = new AdministratorServiceImpl();
		if(id == null || password == null){
			return;
		}
		password = PasswordEncryptionUtils.plainText2MD5Encrypt(password);//加密密码
		service.updateAdministratorPassword(id, password);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
