package com.management.web.controller.major;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.entities.Major;
import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;
import com.management.utils.WebUtils;

@WebServlet("/AddMajor")
public class AddMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Major major = WebUtils.request2Bean(request, Major.class);
		MajorService service = new MajorServiceImpl();
		service.registMajor(major);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
