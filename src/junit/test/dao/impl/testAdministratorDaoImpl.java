package junit.test.dao.impl;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.management.dao.impl.AdministratorDaoImpl;
import com.management.entities.Administrator;

public class testAdministratorDaoImpl {
	
	@Test
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

}
