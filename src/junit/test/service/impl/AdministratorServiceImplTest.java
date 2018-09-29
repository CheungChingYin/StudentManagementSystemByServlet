package junit.test.service.impl;

import java.util.List;
import java.util.Map;

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
		admin.setPassword("a123456789");
		admin.setPermission(0);
		
		service.regiest(admin);
		for(Map.Entry<String, String> e:admin.getError().entrySet()){
			System.out.println(e.getValue()+";"+e.getKey());
		}
	}

	@Test
	public void testAlertAdministrator() {
		
		Administrator admin = new Administrator();
		admin.setId(12);
		admin.setUser("王八");
		admin.setPassword("a12453453");
		admin.setPermission(0);
		
		service.alertAdministrator(admin);
		for(Map.Entry<String, String> e:admin.getError().entrySet()){
			System.out.println(e.getValue()+";"+e.getKey());
		}
	}

	@Test
	public void testDeleteAdministrator() {
		service.deleteAdministrator(12);
	}

	@Test
	public void testCountAllAdministrator() {
		System.out.println(service.countAllAdministrator());
	}

	@Test
	public void testLogin() {
		System.out.println(service.login("王八", "a1253453"));
	}
	
	@Test
	public void testGetAllAdministrator(){
		List<Administrator> list= service.getAllAdministrator();
		for(Administrator admin : list){
			System.out.println(admin.getId()+" ; "+admin.getUser()+" ; "+admin.getPassword()+" ; "+admin.getPermission());
		}
	}

}
