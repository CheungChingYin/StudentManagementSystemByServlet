package com.management.web.controller.major;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.management.entities.Major;
import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;
import com.management.utils.PageUtils;

@WebServlet("/MajorManagementContent")
public class MajorManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		MajorService service = new MajorServiceImpl();
		List<Major> majorList = service.searchAllMajor();
		Integer listCount = majorList.size();
		if(request.getParameter("page") == null){
			return;
		}
		Integer page = Integer.parseInt(request.getParameter("page")); 
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		Integer pages = PageUtils.pagesHandler(listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		if(page == pages){
			majorList = majorList.subList((page - 1) * 10, listCount);
		}else{
			majorList = majorList.subList((page - 1) * 10, page * 10);
		}
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("majorList", majorList);
		map.put("allMajorCount", listCount);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		
		String res = gson.toJson(map);
		out.print(res);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
