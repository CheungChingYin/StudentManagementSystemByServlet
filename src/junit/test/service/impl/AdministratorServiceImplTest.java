package junit.test.service.impl;

import org.junit.Test;

import com.management.entities.Administrator;
import com.management.service.AdministratorService;
import com.management.service.impl.AdministratorServiceImpl;

public class AdministratorServiceImplTest {
	
	AdministratorService service = new AdministratorServiceImpl();

	@Test
	public void testRegiest() {
		
		Administrator admin = new Administrator();
		admin.setUser("abcd");
		admin.setPassword("abcd");
		admin.setPermission(0);
		
		service.regiest(admin);
	}

	@Test
	public void testAlertAdministrator() {
		
		Administrator admin = new Administrator();
		admin.setId(11);
		admin.setUser("ABC");
		admin.setPassword("abc1.");
		admin.setPermission(0);
		
		service.alertAdministrator(admin);
	}

	@Test
	public void testDeleteAdministrator() {
		service.deleteAdministrator(10);
	}

	@Test
	public void testCountAllAdministrator() {
		System.out.println(service.countAllAdministrator());
	}

	@Test
	public void testLogin() {
		System.out.println(service.login("abcd", "abcd1"));
	}

}
