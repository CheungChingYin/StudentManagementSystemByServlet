package junit.test.service.impl;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.management.entities.College;
import com.management.service.CollegeService;
import com.management.service.impl.CollegeServiceImpl;

public class CollegeServiceImplTest {
	
	private CollegeService service = new CollegeServiceImpl();

	@Test
	public void testSearchAllCollege() {
		List<College> list = service.searchAllCollege();
		for(College c : list){
			System.out.println(c.getId()+";"+c.getName());
		}
	}

	@Test
	public void testSearchCollegeById() {
		College c = service.searchCollegeById(3);
		System.out.println(c.getId()+";"+c.getName());
	}

	@Test
	public void testSearchCollegeByName() {
		College c = service.searchCollegeByName("电子信息学院");
		System.out.println(c.getId()+";"+c.getName());
	}

	@Test
	public void testRegistCollege() {
		
		College college = new College();
		college.setId(6);
		college.setName("@");
		service.RegistCollege(college);
		for(Map.Entry<String, String> entry : college.getErrors().entrySet()){
			System.out.println(entry.getKey()+";"+entry.getValue());
		}
	}

	@Test
	public void testAlertCollege() {
		College college = new College();
		college.setId(6);
		college.setName("清真学院");
		service.AlertCollege(college);;
		for(Map.Entry<String, String> entry : college.getErrors().entrySet()){
			System.out.println(entry.getKey()+";"+entry.getValue());
		}
		
	}

	@Test
	public void testDeleteCollege() {
		service.deleteCollege(6);
	}

}
