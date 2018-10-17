package com.management.web.controller.major;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.management.entities.Major;
import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;

@WebServlet("/majorIdIsExist")
public class MajorIdIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("id") == null){
			return;
		}
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		MajorService service = new MajorServiceImpl();
		Major major = null;
		major = service.searchMajorById(id);
		Map<String, String> map = new HashMap<String, String>();
		
		if(major.getName() != null){
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
