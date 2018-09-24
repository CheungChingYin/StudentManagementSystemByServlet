package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.AdministratorDao;
import com.management.entities.Administrator;
import com.management.utils.MySQLConnectionUtils;

public class AdministratorDaoImpl implements AdministratorDao {

	@Override
	public boolean existAdministrator(Administrator admin) throws SQLException {

		String adminName = admin.getUser();
		String sql = "SELECT * FROM administrator WHERE user='" + adminName + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	@Override
	public boolean loginAdministrator(String userName, String password) throws SQLException {

		String sql = "SELECT * FROM administrator WHERE user='" + userName + "' AND password='" + password + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	@Override
	public void addAdministrator(Administrator admin) throws SQLException {

		String sql = "INSERT INTO `administrator` (`user`, `password`, `permission`) VALUES (?,?,?)";
		PreparedStatement ps = null;
		Connection con = MySQLConnectionUtils.mySQLConnection();
		ps = con.prepareStatement(sql);

		String user = admin.getUser();
		String password = admin.getPassword();
		Integer permission = admin.getPermission();

		ps.setString(1, user);
		ps.setString(2, password);
		ps.setInt(3, permission);

		ps.execute();

	}

	@Override
	public List<Administrator> queryAllAdministrator() throws SQLException {

		String sql = "SELECT * FROM administrator";
		List<Administrator> list = new LinkedList<Administrator>();
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		while (result.next()) {
			Administrator admin = new Administrator();
			admin.setId(result.getInt(1));
			admin.setUser(result.getString(2));
			admin.setPassword(result.getString(3));
			admin.setPermission(result.getInt(4));
			list.add(admin);
		}
		return list;
	}

	@Override
	public void alertAdministrator(Administrator admin) throws SQLException {

		Integer id = admin.getId();
		String password = admin.getPassword();
		Integer permission = admin.getPermission();

		String sql = "UPDATE `administrator` SET `password` = ?,`permission` = ? WHERE (`id` = ?)";

		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, password);
		ps.setInt(2, permission);
		ps.setInt(3, id);

		ps.execute();

	}

	@Override
	public void deleteAdministrator(Integer id) throws SQLException {

		String sql = "DELETE FROM `administrator` WHERE (`id`='" + id + "')";

		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);

	}

}
