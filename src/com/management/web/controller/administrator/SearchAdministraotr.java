package com.management.web.controller.administrator;

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
import com.management.entities.Administrator;
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;
import com.management.utils.PageUtils;

/**
 * 管理员搜索功能
 * 需要传入参数
 * 	request:
 * 		page(当前页码)
 * 		search(搜索内容)
 * @author CheungChingYin
 *
 */
@WebServlet("/searchAdministraotr")
public class SearchAdministraotr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		Integer page = Integer.parseUnsignedInt(request.getParameter("page"));
		String search = request.getParameter("search");
		if(search== null || page == null){
			return;
		}
		AdministratorService service = new AdministratorServiceImpl();
		Administrator admin= null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<Administrator> administratorList = new LinkedList<Administrator>();
		Integer listCount = null;
		if(search.matches("\\d+")){//如果搜索内容全为数字，则判断为按管理员ID进行搜索
			admin = service.searchAdministratorById(Integer.parseInt(search));
			administratorList.add(admin);
			listCount = administratorList.size();
		}else{//按姓名搜索
			admin = service.searchAdministratorByName(search);
			administratorList.add(admin);
			listCount = administratorList.size();
		}
		Integer pages = PageUtils.pagesHandler(listCount);//分页功能
		if(page == pages){
			administratorList = administratorList.subList((page - 1) * 10, listCount);
		}else{
			administratorList = administratorList.subList((page - 1) * 10, page * 10);
		}
		
		Integer prePage = PageUtils.prePageHandler(page);
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);
		
		map.put("adminList", administratorList);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		map.put("allAdminCount", listCount);
		map.put("search", search);
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		String result = g.toJson(map);
		out.print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
