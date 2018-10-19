package com.management.web.controller.college;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;

/**
 * 删除学院功能
 * 需要传入参数:
 * 	request:
 * 		id(学院ID)
 * @author CheungChingYin
 *
 */
@WebServlet("/deleteCollege")
public class DeleteCollege extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if(id == null){
			return;
		}
		CollegeService service = new CollegeServiceImpl();
		service.deleteCollege(Integer.parseInt(id));//执行删除功能
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
