package junit.test.dao.impl;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.management.dao.AdministratorDao;
import com.management.dao.impl.AdministratorDaoImpl;
import com.management.entities.Administrator;

public class testAdministratorDaoImpl {
	
	@Ignore
	public void testExistAdministrator() throws SQLException, IOException{
		Administrator admin = new Administrator();
		admin.setId(1);
		admin.setUser("abc");
		admin.setPassword("admin");
		admin.setPermission(1);
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		boolean result = dao.existAdministrator(admin);
		System.out.println(result);
	}
	
	@Ignore
	public void testloginAdministrator() throws IOException, SQLException{
		String userName = "admin";
		String password = "123456";
		AdministratorDaoImpl dao = new AdministratorDaoImpl();
		boolean redult = dao.loginAdministrator(userName, password);
		System.out.println(redult);
	}
	
	@Ignore
	public void testRegisterAdministrator() throws IOException, SQLException{
		Administrator admin = new Administrator();
		AdministratorDao dao = new AdministratorDaoImpl();
		admin.setUser("ABC");
		admin.setPassword("Test");
		admin.setPermission(1);
		dao.registerAdministrator(admin);
	}
	
	@Ignore
	public void testAlertAdministrator() throws IOException, SQLException{

		Administrator admin = new Administrator();
		AdministratorDao dao = new AdministratorDaoImpl();
		
		admin.setId(2);
		admin.setPassword("123456");
		admin.setPermission(1);
		dao.alertAdministrator(admin);
	}
	
	@Test
	public void testDeleteAdministrator() throws SQLException{
		
		Administrator admin = new Administrator();
		AdministratorDao dao = new AdministratorDaoImpl();
		
		admin.setId(7);
		dao.deleteAdministrator(admin);
	}
}
