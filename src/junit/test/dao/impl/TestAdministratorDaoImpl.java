package junit.test.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.management.dao.AdministratorDao;
import com.management.dao.impl.AdministratorDaoImpl;
import com.management.entities.Administrator;
import com.management.exception.AdministratorExistException;

public class TestAdministratorDaoImpl {
	
	@Test
	public void testQueryAllAdministrator(){
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		try {
			List<Administrator> list = dao.queryAllAdministrator();
			for(Administrator admin : list){
				System.out.println(admin.getId()+" : "+admin.getUser()+" : "+admin.getPassword()+" : "+admin.getPermission());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryAdministratorById(){
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		try {
			Administrator admin = dao.queryAdministratorById(2);
			System.out.println(admin.getId()+" : "+admin.getUser()+" : "+admin.getPassword()+" : "+admin.getPermission());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryAdministratorByName(){
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		try {
			Administrator admin = dao.queryAdministratorByName("张三");
			System.out.println(admin.getId()+" : "+admin.getUser()+" : "+admin.getPassword()+" : "+admin.getPermission());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testExistAdministrator() throws SQLException, AdministratorExistException{
		Administrator admin = new Administrator();
		admin.setId(1);
		admin.setUser("abc1");
		admin.setPassword("admin");
		admin.setPermission(1);
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		boolean result = dao.existAdministrator(admin);
		System.out.println(result);
	}
	
	@Test
	public void testloginAdministrator() throws SQLException{
		String userName = "admin";
		String password = "admin1";
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		boolean redult = dao.loginAdministrator(userName, password);
		System.out.println(redult);
	}
	
	@Test
	public void testRegisterAdministrator() throws SQLException{
		Administrator admin = new Administrator();
		AdministratorDao dao = new AdministratorDaoImpl();
		admin.setUser("ABC");
		admin.setPassword("Test");
		admin.setPermission(1);
		dao.addAdministrator(admin);
	}
	
	@Test
	public void testAlertAdministrator() throws SQLException{

		Administrator admin = new Administrator();
		AdministratorDao dao = new AdministratorDaoImpl();
		
		admin.setUser("test");
		admin.setId(3);
		admin.setPassword("123456");
		admin.setPermission(1);
		dao.alertAdministrator(admin);
	}
	
	@Test
	public void testDeleteAdministrator() throws SQLException{
		
		AdministratorDao dao = new AdministratorDaoImpl();
		
		dao.deleteAdministrator(6);
	}
}
