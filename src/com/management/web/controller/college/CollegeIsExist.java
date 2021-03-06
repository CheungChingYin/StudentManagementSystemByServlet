package com.management.web.controller.college;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.College;
import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;

/**
 * 检测学院ID是否重复(学院ID不可重复)
 * 配合前端的nice-validator插件remote()方法进行表单验证
 * 需要传入参数:
 * 	request:
 * 		id(学院ID)
 * @author CheungChingYin
 *
 */
@WebServlet("/collegeIsExist")
public class CollegeIsExist extends HttpServlet {
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
		Map<String, String> map = new HashMap<String, String>();
		College college = service.searchCollegeById(Integer.parseInt(id));
		if(college.getName() != null){//判断学院ID是否存在
			map.put("error", "ID已被占用!");
		}else{
			map.put("ok","名字能够使用!");
		}
		
		Gson gson = new Gson();
		String res = gson.toJson(map);
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
