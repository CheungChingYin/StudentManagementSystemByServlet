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

/**
 * 检测管理员的名字是否存在（管理员名字不可重复）
 * 配合前端的nice-validator插件remote()方法进行表单验证
 * 需要传入参数：user（管理员姓名）
 * @author CheungChingYin
 *
 */
@WebServlet("/AdministratorNameIsExist")
public class AdministratorIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {//判断是否有管理员登录，如没有则表示为非法登录，则调回登录页面
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
		out.print(res);//把结果通过JSON形式来输出
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
