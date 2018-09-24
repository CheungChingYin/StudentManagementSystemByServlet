package junit.test.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.management.dao.StudentDao;
import com.management.dao.impl.StudentDaoImpl;
import com.management.entities.Student;
import com.management.utils.DateConvertor;

public class StudentDaoImplTest {

	@Test
	public void testQueryAllStudents() {

		StudentDao dao = new StudentDaoImpl();
		List<Student> list;
		try {
			list = dao.queryAllStudents();
			for (Student stu : list) {
				System.out.println(stu.getId() + " ; " + stu.getName() + " ; " + stu.getSex() + " ; " + stu.getBirth()
						+ " ; " + stu.getMajor_id() + " ; " + stu.getCollege_id() + " ; ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testQueryStudentById() {

		StudentDao dao = new StudentDaoImpl();
		try {
			Student stu = dao.queryStudentById("123271631102102");
			System.out.println(stu.getId() + " ; " + stu.getName() + " ; " + stu.getSex() + " ; " + stu.getBirth()
					+ " ; " + stu.getMajor_id() + " ; " + stu.getCollege_id() + " ; ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testAddStudent() {
		Student stu = new Student();
		String birth = "2000-06-30";
		Date sqlDate = DateConvertor.stringDate2SqlDate(birth);
		
		stu.setId("123271631102103");
		stu.setName("王五");
		stu.setSex(1);
		stu.setBirth(sqlDate);
		stu.setMajor_id(21);
		stu.setCollege_id(3);
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			dao.addStudent(stu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAlertStudent() {
		Student stu = new Student();
		String birth = "2000-06-30";
		Date sqlDate = DateConvertor.stringDate2SqlDate(birth);
		
		stu.setId("123271631102103");
		stu.setName("王五");
		stu.setSex(1);
		stu.setBirth(sqlDate);
		stu.setMajor_id(21);
		stu.setCollege_id(3);
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			dao.alertStudent(stu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteStudent() {
		
		StudentDao dao = new StudentDaoImpl();
		try {
			dao.deleteStudent("123271631102103");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
