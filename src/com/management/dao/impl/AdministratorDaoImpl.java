package com.management.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.management.dao.AdministratorDao;
import com.management.entities.Administrator;
import com.management.utils.MySQLConnectionUtils;

public class AdministratorDaoImpl implements AdministratorDao {

	@Override
	public boolean existAdministrator(Administrator admin) throws SQLException, IOException {
		String adminName = admin.getUser();
		String sql = "SELECT * FROM administrator WHERE user='" + adminName + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		if (result.next()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean loginAdministrator(String userName, String password) {
		return false;
	}

	@Override
	public void registerAdministrator(Administrator admin) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet queryAllAdministrator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean alertAdministrator(Administrator admin) {
		// TODO Auto-generated method stub
		return false;
	}

}
