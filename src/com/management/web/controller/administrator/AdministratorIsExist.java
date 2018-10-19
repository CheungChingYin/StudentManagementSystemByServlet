package com.management.web.controller.administrator;

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
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;

@WebServlet("/AdministratorNameIsExist")
public class AdministratorIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("user");
		if(name == null){
			return;
		}
		AdministratorService service = new AdministratorServiceImpl();
		boolean flag = service.administratorNameIsExist(name);
		Map<String, String> map = new HashMap<String, String>();
		if(flag){
			map.put("error", "名字已被占用!");
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
