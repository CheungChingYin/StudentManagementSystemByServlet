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
 * 更新专业功能
 *  需要传入参数:
 * 		request:
 * 			更新专业表单：
 * 				id(专业ID)
 * 				name(专业名称)
 * 				college_id(专业相关的学院id)
 * @author CheungChingYin
 *
 */
@WebServlet("/updateMajor")
public class UpdateMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Major major = WebUtils.request2Bean(request, Major.class);
		MajorService service = new MajorServiceImpl();
		service.alertMajor(major);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
