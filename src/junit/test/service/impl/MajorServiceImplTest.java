package junit.test.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.management.entities.Major;
import com.management.service.MajorService;
import com.management.service.impl.MajorServiceImpl;

public class MajorServiceImplTest {
	
	MajorService s = new MajorServiceImpl();

	@Test
	public void testSearchAllMajor() {
		List<Major> list = s.searchAllMajor();
		for(Major m :list){
			System.out.println(m.getId()+";"+m.getName()+";"+m.getCollegeName());
		}
	}

	@Test
	public void testSearchMajorByCollegeId() {
		
		List<Major> list = s.searchMajorByCollegeId(2);
		for(Major m :list){
			System.out.println(m.getId()+";"+m.getName()+";"+m.getCollegeName());
		}
		
	}

	@Test
	public void testSearchMajorById() {
		Major m = s.searchMajorById(22);
		System.out.println(m.getId()+";"+m.getName()+";"+m.getCollegeName());
		
	}

	@Test
	public void testSearchMajorByName() {
		Major m = s.searchMajorByName("计算机应用技术");
		System.out.println(m.getId()+";"+m.getName()+";"+m.getCollegeName());
	}

	@Test
	public void testRegistMajor() {
		Major m = new Major();
		m.setId(30);
		m.setName("计算机");
		m.setCollege_id(2);
		s.registMajor(m);
	}

	@Test
	public void testAlertMajor() {
		Major m = new Major();
		m.setId(30);
		m.setName("计算");
		m.setCollege_id(2);
		s.alertMajor(m);
	}

	@Test
	public void testDeleteMajor() {
		s.deleteMajor(30);
	}

}
