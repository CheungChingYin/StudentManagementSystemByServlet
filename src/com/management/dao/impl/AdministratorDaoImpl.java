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
import com.management.exception.AdministratorExistException;
import com.management.utils.MySQLConnectionUtils;

public class AdministratorDaoImpl implements AdministratorDao {

	/**
	 * 查询管理员姓名是否存在
	 */
	@Override
	public boolean existAdministrator(Administrator admin) throws AdministratorExistException, SQLException {

		String adminName = admin.getUser();
		String sql = "SELECT * FROM administrator WHERE user='" + adminName + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	/**
	 * 管理员登录查询
	 */
	@Override
	public boolean loginAdministrator(String userName, String password) throws SQLException {

		String sql = "SELECT * FROM administrator WHERE user='" + userName + "' AND password='" + password + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		return result.next();
	}

	/**
	 * 添加管理信息
	 */
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

		ps.close();
		con.close();

	}

	/**
	 * 查询所有管理员信息
	 */
	@Override
	public List<Administrator> queryAllAdministrator() throws SQLException {

		String sql = "SELECT * FROM administrator";
		List<Administrator> list = new LinkedList<Administrator>();
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		while (result.next()) {
			Administrator admin = new Administrator();
			admin.setId(result.getInt(1));
			admin.setUser(result.getString(2));
			admin.setPermission(result.getInt(4));
			list.add(admin);
		}
		return list;
	}

	/**
	 * 通过管理员ID查询管理员信息
	 */
	@Override
	public Administrator queryAdministratorById(Integer id) throws SQLException {

		String sql = "SELECT * FROM administrator WHERE id='" + id + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		Administrator admin = new Administrator();
		while (result.next()) {
			admin.setId(result.getInt(1));
			admin.setUser(result.getString(2));
			admin.setPermission(result.getInt(4));
		}
		return admin;
	}

	/**
	 * 修改管理员信息
	 */
	@Override
	public void alertAdministrator(Administrator admin) throws SQLException {

		Integer id = admin.getId();
		String user = admin.getUser();
		Integer permission = admin.getPermission();

		String sql = "UPDATE `administrator` SET `permission` = ? ,`user` = ?WHERE (`id` = ?)";

		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, permission);
		ps.setString(2, user);
		ps.setInt(3, id);

		ps.execute();

		ps.close();
		con.close();

	}

	/**
	 * 删除管理员信息
	 */
	@Override
	public void deleteAdministrator(Integer id) throws SQLException {

		String sql = "DELETE FROM `administrator` WHERE (`id`='" + id + "')";

		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);

		statement.close();
		con.close();

	}

	/**
	 * 通过管理员名字查询管理员信息
	 */
	@Override
	public Administrator queryAdministratorByName(String name) throws SQLException {
		String sql = "SELECT * FROM administrator WHERE user='" + name + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		Administrator admin = new Administrator();
		while (result.next()) {
			admin.setId(result.getInt(1));
			admin.setUser(result.getString(2));
			admin.setPermission(result.getInt(4));
		}
		return admin;
	}

	/**
	 * 修改管理员密码
	 */
	@Override
	public void alertAdministratorPassword(Integer id, String password) throws SQLException {

		String sql = "UPDATE `administrator` SET `password` = ? WHERE (`id` = ?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, password);
		ps.setInt(2, id);
		ps.execute();

		ps.close();
		con.close();
	}

}
