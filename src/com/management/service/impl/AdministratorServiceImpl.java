package com.management.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.management.dao.AdministratorDao;
import com.management.dao.impl.AdministratorDaoImpl;
import com.management.entities.Administrator;
import com.management.exception.AdministratorExistException;
import com.management.service.AdministratorService;
import com.management.utils.CheckDataValidity;
import com.management.utils.PasswordEncryptionUtils;

public class AdministratorServiceImpl implements AdministratorService {

	AdministratorDao dao = new AdministratorDaoImpl();

	/*
	 * 管理员注册服务 (non-Javadoc)
	 * 
	 * @see com.management.service.AdministratorService#regiest(com.management.
	 * entities.Administrator)
	 */
	@Override
	public void regiest(Administrator admin) {

		if (admin == null) {
			return;
		}
		try {
			if (dao.existAdministrator(admin)) {// 判断用户名是否存在
				admin.getError().put("user", "用户名已存在");
				throw new AdministratorExistException();
			} else {
				if (CheckDataValidity.administratorValidate(admin)) {
					// 判断输入的信息合法性
					admin.setPassword(PasswordEncryptionUtils.plainText2MD5Encrypt(admin.getPassword()));
					// 密码加密提交
					dao.addAdministrator(admin);
				}
			}
		} catch (AdministratorExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 修改管理员用户 (non-Javadoc)
	 * 
	 * @see com.management.service.AdministratorService#alertAdministrator(com.
	 * management.entities.Administrator)
	 */
	@Override
	public void alertAdministrator(Administrator admin) {

		if (admin == null) {// 判断带来的管理员对象是否为空
			return;
		}
		Integer id = admin.getId();
		String password = PasswordEncryptionUtils.plainText2MD5Encrypt(admin.getPassword());
		
		try {
			Administrator source_admin = dao.queryAdministratorById(id);
			if (dao.existAdministrator(admin)) {
				// 判断用户名是否存在
				if (source_admin.getUser().equals(admin.getUser())) {
					// 如果数据库的源数据用户名和新修改的用户名一致也执行修改
					if (CheckDataValidity.administratorValidate(admin)) {
						admin.setPassword(password);// 存放加密后的密码
						dao.alertAdministrator(admin);
						return;
					}
				}
				admin.getError().put("user", "用户名已存在");
				throw new AdministratorExistException();
				//如果修改后的用户名并不是和原来修改前的用户名一致，则抛出自定义异常，说用户已经存在
			}
			if (CheckDataValidity.administratorValidate(admin)) {
				admin.setPassword(password);// 存放加密后的密码
				dao.alertAdministrator(admin);
			}
		} catch (AdministratorExistException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAdministrator(Integer id) {

		try {
			dao.deleteAdministrator(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer countAllAdministrator() {

		int count = 0;

		try {
			List<Administrator> list = dao.queryAllAdministrator();
			count = list.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean login(String user, String password) {
		String encryptionPassword = PasswordEncryptionUtils.plainText2MD5Encrypt(password);
		try {
			boolean result = dao.loginAdministrator(user, encryptionPassword);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Administrator> getAllAdministrator() {

		List<Administrator> list = null;
		try {
			list = dao.queryAllAdministrator();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
