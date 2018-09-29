package junit.test.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.management.entities.Student;
import com.management.service.StudentService;
import com.management.service.impl.StudentServiceImpl;
import com.management.utils.CheckDataValidity;
import com.management.utils.DateConvertor;

public class StudentServiceImplTest {

	private StudentService service = new StudentServiceImpl();

	@Test
	public void testAddStudent() {

		Student student = new Student();
		Date birth = DateConvertor.stringDate2SqlDate("1999-03-25");
		Date schoolDay = DateConvertor.stringDate2SqlDate("2018-09-01");
		student.setId("1232716311023");
		student.setName("王五");
		student.setBirth(birth);
		student.setSchoolDay(schoolDay);
		student.setSex(1);
		student.setMajor_id(15);
		student.setCollege_id(2);
		
		service.addStudent(student);

		for (Map.Entry<String, String> entry : student.getErrors().entrySet()) {
			System.out.println("Key:" + entry.getKey() + " ; " + "value" + entry.getValue());
		}

	}

	@Test
	public void testAlertStudent() {
		Student student = new Student();
		Date birth = DateConvertor.stringDate2SqlDate("1999-04-25");
		Date schoolDay = DateConvertor.stringDate2SqlDate("2017-09-01");
		student.setId("123271631102103");
		student.setName("李白");
		student.setBirth(birth);
		student.setSchoolDay(schoolDay);
		student.setSex(1);
		student.setMajor_id(15);
		student.setCollege_id(2);

			service.alertStudent(student);
			for (Map.Entry<String, String> entry : student.getErrors().entrySet()) {
				System.out.println("Key:" + entry.getKey() + " ; " + "value" + entry.getValue());
			}
	}

	@Test
	public void testDeleteStudent() {
		service.deleteStudent("123271631102103");
	}

	@Test
	public void testQueryAllStudent() {
		List<Student> list = service.queryAllStudent();
		for (Student s : list) {
			System.out.println(s.getId() + ";" + s.getName() + ";" + s.getSex() + ";" + s.getBirth() + ";"
					+ s.getSchoolDay() + ";" + s.getMajorName() + ";" + s.getCollegeName());
		}
	}

	@Test
	public void testSearchStudentById() {
		Student s = service.searchStudentById("123271631102101");
		System.out.println(s.getId() + ";" + s.getName() + ";" + s.getSex() + ";" + s.getBirth() + ";"
				+ s.getSchoolDay() + ";" + s.getMajorName() + ";" + s.getCollegeName());
	}

	@Test
	public void testSearchStudentByName() {
		List<Student> list = service.searchStudentByName("李四");
		for (Student s : list) {
			System.out.println(s.getId() + ";" + s.getName() + ";" + s.getSex() + ";" + s.getBirth() + ";"
					+ s.getSchoolDay() + ";" + s.getMajorName() + ";" + s.getCollegeName());
		}
	}

	@Test
	public void testCountStudents() {
		System.out.println(service.countStudents());
	}

}
