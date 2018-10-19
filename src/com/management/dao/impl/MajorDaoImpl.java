package com.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.MajorDao;
import com.management.entities.Major;
import com.management.utils.MySQLConnectionUtils;

public class MajorDaoImpl implements MajorDao {

	/**
	 * 查询所有专业信息
	 */
	@Override
	public List<Major> queryAllMajor() throws SQLException {

		String sql = "SELECT major.id,major.`name`,college.`name` FROM major,college WHERE major.college_id=college.id";

		List<Major> list = new LinkedList<Major>();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			Major major = new Major();
			major.setId(res.getInt(1));
			major.setName(res.getString(2));
			major.setCollegeName(res.getString(3));
			;
			list.add(major);
		}
		return list;
	}

	/**
	 * 通过专业ID查询专业信息
	 */
	@Override
	public Major queryMajorById(Integer id) throws SQLException {

		String sql = "SELECT major.id,major.`name`,college.`name` FROM major,college WHERE major.college_id=college.id AND major.`id`='"
				+ id + "'";

		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		Major major = new Major();
		while (res.next()) {
			major.setId(res.getInt(1));
			major.setName(res.getString(2));
			major.setCollegeName(res.getString(3));
		}
		return major;
	}

	/**
	 * 通过学院ID查询相关的专业信息
	 */
	@Override
	public List<Major> queryMajorByCollege(Integer id) throws SQLException {

		String sql = "SELECT major.id,major.`name`,college.`name` FROM major,college WHERE major.college_id=college.id AND major.`college_id`='"
				+ id + "'";

		List<Major> list = new LinkedList<Major>();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			Major major = new Major();
			major.setId(res.getInt(1));
			major.setName(res.getString(2));
			major.setCollegeName(res.getString(3));
			list.add(major);
		}
		return list;
	}

	/**
	 * 通过专业名称查询专业信息
	 */
	@Override
	public Major queryMajorByName(String name) throws SQLException {

		String sql = "SELECT major.id,major.`name`,college.`name` FROM major,college WHERE major.college_id=college.id AND major.`name`='"
				+ name + "'";

		Major major = new Major();
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		while (res.next()) {
			major.setId(res.getInt(1));
			major.setName(res.getString(2));
			major.setCollegeName(res.getString(3));
		}
		return major;
	}

	/**
	 * 添加专业
	 */
	@Override
	public void addMajor(Major major) throws SQLException {

		String sql = "INSERT INTO `major` (`id`, `name`, `college_id`) VALUES (?,?,?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		Integer id = major.getId();
		String name = major.getName();
		Integer collegeId = major.getCollege_id();

		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, collegeId);
		ps.execute();
		
		ps.close();
		con.close();
	}

	/**
	 * 修改专业
	 */
	@Override
	public void alertMajor(Major major) throws SQLException {

		String sql = "UPDATE `major` SET `name`=?, `college_id`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		Integer id = major.getId();
		String name = major.getName();
		Integer collegeId = major.getCollege_id();

		ps.setString(1, name);
		ps.setInt(2, collegeId);
		ps.setInt(3, id);

		ps.execute();
		
		ps.close();
		con.close();

	}

	/**
	 * 删除专业
	 */
	@Override
	public void deleteMajor(Integer id) throws SQLException {

		String sql = "DELETE FROM `major` WHERE (`id`='" + id + "')";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);
		
		statement.close();
		con.close();
	}

	/**
	 * 查询专业名字是否存在
	 */
	@Override
	public boolean existMajor(String majorName) throws SQLException {

		String sql = "SELECT * FROM major WHERE name='" + majorName + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		return res.next();
	}

}
