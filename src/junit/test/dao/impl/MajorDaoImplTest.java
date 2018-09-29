package junit.test.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.management.dao.MajorDao;
import com.management.dao.impl.MajorDaoImpl;
import com.management.entities.Major;

public class MajorDaoImplTest {
	
	MajorDao dao = new MajorDaoImpl();

	@Test
	public void testQueryAllMajor() {
		
		try {
			List<Major> list = dao.queryAllMajor();
			for(Major m : list){
				System.out.println(m.getId()+" : "+m.getName()+" : "+m.getCollegeName()+" : ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testQueryMajorById() throws SQLException {
		Major m = dao.queryMajorById(2);
		System.out.println(m.getId()+" : "+m.getName()+" : "+m.getCollegeName()+" : ");
	}
	
	@Test
	public void testQueryMajorByCollege(){
		try {
			List<Major> list = dao.queryMajorByCollege(3);
			for(Major m : list){
				System.out.println(m.getId()+";"+m.getName()+";"+m.getCollegeName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryMajorByName(){
		Major m = null;
		try {
			m = dao.queryMajorByName("电子商务");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(m.getId()+";"+m.getName()+";"+m.getCollegeName());
		
	}

	@Test
	public void testAddMajor() {
		
		Major major = new Major();
		major.setId(30);
		major.setName("汽车维修");
		major.setCollege_id(5);
		
		try {
			dao.addMajor(major);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAlertMajor() {
		
		Major major = new Major();
		major.setId(30);
		major.setName("汽车检测");
		major.setCollege_id(5);
		
		try {
			dao.alertMajor(major);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteMajor() {
		try {
			dao.deleteMajor(30);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testExistMajor() {
		try {
			System.out.println(dao.existMajor("财务管理"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
