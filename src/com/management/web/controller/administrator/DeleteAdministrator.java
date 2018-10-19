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

/**
 * 管理员删除功能
 * 需要传入参数：
 * 	request:
 * 		id(管理员ID)
 * @author CheungChingYin
 *
 */
@WebServlet("/deleteAdministrator")
public class DeleteAdministrator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		String id = request.getParameter("id");
		if(id == null){
			return;
		}
		AdministratorService service = new AdministratorServiceImpl();
		service.deleteAdministrator(Integer.parseInt(id));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
