package com.management.web.controller.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.entities.Student;
import com.management.service.StudentService;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.WebUtils;

/**
 * 更新学生资料
 * 需要传入参数:
 * 		request:
 * 			修改学生表单：
 * 				id(学生学号)
 * 				name(学生姓名)
 * 				sex(学生性别)
 * 				birth(学生出生日期)
 * 				schoolDay(学生入学日期)
 * 				major_id(专业ID)
 * 				college_id(学院ID)
 * @author CheungChingYin
 *
 */
@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		StudentService service = new StudentServiceImpl();
		Student stu = WebUtils.request2Bean(request, Student.class);
		service.alertStudent(stu);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
