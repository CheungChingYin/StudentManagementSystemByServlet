package com.management.web.controller.major;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Major;
import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;
import com.management.utils.WebUtils;

/**
 * 专业添加功能
 * 需要传入参数:
 * 		request:
 * 			专业添加表单
 * 				id(专业ID)
 * 				name(专业名称)
 * 				college_id(专业所属学院ID)
 * @author CheungChingYin
 *
 */
@WebServlet("/AddMajor")
public class AddMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		Major major = WebUtils.request2Bean(request, Major.class);
		MajorService service = new MajorServiceImpl();
		service.registMajor(major);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
