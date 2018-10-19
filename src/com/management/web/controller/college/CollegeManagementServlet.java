package com.management.web.controller.college;

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
import com.management.entities.College;
import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;
import com.management.utils.PageUtils;

/**
 * 获取并通过JSON返回全部学院信息
 * 需要传入参数:
 * 	request:
 * 		page(当前页码)
 * @author CheungChingYin
 *
 */
@WebServlet("/CollegeManagementContent")
public class CollegeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if(request.getParameter("page") == null){
			return;
		}
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		CollegeService service = new CollegeServiceImpl();
		List<College> collegeList = service.searchAllCollege();
		Integer listCount = collegeList.size();
		
		//分页功能
		Integer page = Integer.parseInt(request.getParameter("page")); 
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		Integer pages = PageUtils.pagesHandler(listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		if(page == pages){
			collegeList = collegeList.subList((page - 1) * 10, listCount);
		}else{
			collegeList = collegeList.subList((page - 1) * 10, page * 10);
		}
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("collegeList", collegeList);
		map.put("allCollegeCount", listCount);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		
		String res = gson.toJson(map);
		out.print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
