package com.management.web.controller.administrator;

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
import com.google.gson.GsonBuilder;
import com.management.entities.Administrator;
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;
import com.management.utils.PageUtils;

/**
 * 全部管理员信息获取
 * 需要传入参数：
 * 	request：
 * 		page(当前页码)
 * @author CheungChingYin
 *
 */
@WebServlet("/AdministratorManagementContent")
public class AdministratorManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		
		AdministratorService service = new AdministratorServiceImpl();
		
		// 处理页码
		Integer listCount = service.countAllAdministrator();//获取管理员总数
		if(request.getParameter("page") == null){
			return;
		}
		Integer page = Integer.parseUnsignedInt(request.getParameter("page"));//获得传来的当前页
		Integer prePage = PageUtils.prePageHandler(page);//计算出上一页页码
		Integer nextPage = PageUtils.nextPageHandler(page, listCount);//计算出下一页页码
		Integer pages = PageUtils.pagesHandler(listCount);//计算出总共分为多少页
		List<Integer> pageNum = PageUtils.pageHandler(page, listCount);//使用列表装载将要表示的页数
		List<Administrator> list = null;
		
		//判断当前页是否等于尾页，进行伪分页
		if(page == pages){
			list = service.getAllAdministrator().subList((page - 1) * 10, listCount);
		}else{
			list = service.getAllAdministrator().subList((page - 1) * 10, page * 10);
		}
		
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminList", list);
		map.put("allAdminCount", listCount);
		map.put("prePage", prePage);
		map.put("nextPage", nextPage);
		map.put("pageNum", pageNum);
		map.put("page", page);
		
		String content = gson.toJson(map);
		out.print(content);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
