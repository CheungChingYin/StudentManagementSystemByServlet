package junit.test.utils;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Map;

import org.junit.Test;

import com.management.entities.Administrator;
import com.management.entities.Student;
import com.management.utils.CheckDataValidity;
import com.management.utils.DateConvertor;

public class CheckDataValidityTest {

	@Test
	public void testAdministratorValidate() {

		Administrator admin = new Administrator();
		admin.setUser("张三");
		admin.setPassword("abc");
		admin.setPermission(1);
		System.out.println(CheckDataValidity.administratorValidate(admin));
		for (Map.Entry<String, String> entry : admin.getError().entrySet()) {
			System.out.println("key:" + entry.getKey() + " ; " + "value:" + entry.getValue());
		}

	}

	@Test
	public void testCheckStudentValidate() {
		Student s = new Student();
		Date birth = DateConvertor.stringDate2SqlDate("1997-08-05");
		Date schoolDay = DateConvertor.stringDate2SqlDate("2016-09-01");
		s.setId("123271631102418");
		s.setName("Ben张三");
		s.setSex(2);
		s.setBirth(birth);
		s.setSchoolDay(schoolDay);
		s.setMajor_id(2);
		s.setCollege_id(1);
		System.out.println(CheckDataValidity.checkStudentValidate(s));
		for(Map.Entry<String, String> entry : s.getErrors().entrySet()){
			System.out.println("key:" + entry.getKey() + " ; " + "value:" + entry.getValue());
		}
	}

}
