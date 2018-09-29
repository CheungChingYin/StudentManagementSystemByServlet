package junit.test.dao.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.management.dao.CollegeDao;
import com.management.dao.impl.CollegeDaoImpl;
import com.management.entities.College;

public class CollegeDaoImplTest {

	CollegeDao dao = new CollegeDaoImpl();

	@Test
	public void testQueryAllCollege() {

		List<College> list;
		try {
			list = dao.queryAllCollege();
			for(College c : list){
				System.out.println(c.getId()+" : "+c.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryCollegeById() {
		try {
			College college = dao.queryCollegeById(3);
			System.out.println(college.getId()+" : "+college.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryCollegeByName(){
		try {
			College c = dao.queryCollegeByName("电子信息学院");
			System.out.println(c.getId()+" : "+c.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testAddCollege() {

		College college = new College();
		college.setId(6);
		college.setName("农业学院");
		
		try {
			dao.addCollege(college);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAlertCollege() {
		College college = new College();
		college.setId(6);
		college.setName("科学学院");
		
		try {
			dao.alertCollege(college);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteCollege() {
		try {
			dao.deleteCollege(6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testExistCollege() {
		try {
			System.out.println(dao.existCollege("电子信息学院"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
