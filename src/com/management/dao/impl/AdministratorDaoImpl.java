package com.management.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.management.dao.AdministratorDao;
import com.management.entities.Administrator;
import com.management.utils.MySQLConnectionUtils;

public class AdministratorDaoImpl implements AdministratorDao {

	@Override
	public boolean existAdministrator(Administrator admin) throws SQLException, IOException {

		String adminName = admin.getUser();
		String sql = "SELECT * FROM administrator WHERE user='" + adminName + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	@Override
	public boolean loginAdministrator(String userName, String password) throws IOException, SQLException {

		String sql = "SELECT * FROM administrator WHERE user='" + userName + "' AND password='" + password + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	@Override
	public void registerAdministrator(Administrator admin) throws IOException, SQLException {

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
	public ResultSet queryAllAdministrator() throws IOException {
		
		String sql = "SELECT * FROM administrator";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result;
	}

	@Override
	public void alertAdministrator(Administrator admin) throws IOException, SQLException {
		
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
	public void deleteAdministrator(Administrator admin) throws SQLException {
		
		Integer id = admin.getId();
		String sql = "DELETE FROM `administrator` WHERE (`id`='" + id + "')";
		
		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);
		
	}

}
