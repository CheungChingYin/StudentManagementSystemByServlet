package com.management.web.controller.college;

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
import com.management.entities.College;
import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;
import com.management.utils.PageUtils;

/**
 * 学院搜索功能
 * 需要传入参数:
 * 	request:
 * 		page(当前页)
 * 		search(搜索内容)
 * @author CheungChingYin
 *
 */
@WebServlet("/searchCollege")
public class SearchCollege extends HttpServlet {
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
		if(page == null || search == null){
			return;
		}
		CollegeService service = new CollegeServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		College college = null;
		List<College> collegeList = new LinkedList<College>();
		Integer listCount = null;
		if(search.matches("\\d+")){//判断搜索内容为纯数字，则按学院ID来搜索
			college = service.searchCollegeById(Integer.parseInt(search));
			collegeList.add(college);
		}else{//其他则按照学院名称来搜索
			college = service.searchCollegeByName(search);
			collegeList.add(college);
		}
		//分页功能
		listCount = collegeList.size();
		Integer pages = PageUtils.pagesHandler(listCount);
		if(page == pages){
			collegeList = collegeList.subList((page - 1) * 10, listCount);
		}else{
			collegeList = collegeList.subList((page - 1) * 10, page * 10);
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		map.put("collegeList", collegeList);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("allCollegeCount", listCount);
		map.put("search", search);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		String result = g.toJson(map);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
