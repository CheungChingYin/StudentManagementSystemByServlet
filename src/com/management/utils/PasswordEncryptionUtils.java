package com.management.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * 密码加密
 * @author CheungChingYin
 *
 */
public class PasswordEncryptionUtils {

	/**
	 * 通过MD5进行密码加密
	 * @param password
	 * @return
	 */
	public static String plainText2MD5Encrypt(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(password.getBytes());
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
