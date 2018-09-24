package junit.test.utils;

import org.junit.Test;

import com.management.utils.PasswordEncryptionUtils;

public class TestPasswordEncryptionUtils {

	@Test
	public void testPlainText2MD5Encrypt(){
		String password = "Testadmin";
		String result = PasswordEncryptionUtils.plainText2MD5Encrypt(password);
		System.out.println(result);
	}
}
