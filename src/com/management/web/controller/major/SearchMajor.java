package com.management.web.controller.major;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
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

@WebServlet("/SearchMajor")
public class SearchMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Integer page = Integer.parseInt(request.getParameter("page"));
		String search = request.getParameter("search");
		if(search== null || page == null){
			return;
		}
		
		MajorService service = new MajorServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Major major = null;
		List<Major> majorList = new LinkedList<Major>();
		Integer listCount = null;
		if(search.matches("\\d+")){
			major = service.searchMajorById(Integer.parseInt(search));
			majorList.add(major);
			listCount = majorList.size();
		}else{
			major = service.searchMajorByName(search);
			majorList.add(major);
			listCount = majorList.size();
		}
		Integer pages = PageUtils.pagesHandler(listCount);
		if(page == pages){
			majorList = majorList.subList((page - 1) * 10, listCount);
		}else{
			majorList = majorList.subList((page - 1) * 10, page * 10);
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		map.put("majorList", majorList);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("allMajorCount", listCount);
		map.put("search", search);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		String result = g.toJson(map);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
