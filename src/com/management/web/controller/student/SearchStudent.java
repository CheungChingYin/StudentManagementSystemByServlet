package com.management.web.controller.student;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.management.entities.Student;
import com.management.service.StudentService;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.PageUtils;

@WebServlet("/SearchStudent")
public class SearchStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StudentService service = new StudentServiceImpl();
		List<Student> studentList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String search = request.getParameter("search");
		Integer page = Integer.parseUnsignedInt(request.getParameter("page"));
		Integer listCount = null;
		if (search == null || page==null) {
			return;
		}
		
		if (search.matches("\\d+")) {
			studentList = new LinkedList<Student>();
			Student s = service.searchStudentById(search);
			studentList.add(s);
			listCount = studentList.size();
		} else {
			studentList = service.searchStudentByName(search);
			listCount = studentList.size();
			Integer pages = PageUtils.pagesHandler(listCount);
			if(page == pages){
				studentList = service.searchStudentByName(search).subList((page - 1) * 10, listCount);
			}else{
				studentList = service.searchStudentByName(search).subList((page - 1) * 10, page * 10);
			}
			
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		

		map.put("studentList", studentList);
		map.put("allStudentCount", listCount);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("search",search);

		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String result = g.toJson(map);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
