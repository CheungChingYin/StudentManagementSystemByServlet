package com.management.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.management.dao.StudentDao;
import com.management.entities.Student;
import com.management.utils.MySQLConnectionUtils;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> queryAllStudents() throws SQLException {

		String sql = "SELECT * FROM student";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		List<Student> list = new LinkedList<Student>();
		while (result.next()) {
			Student stu = new Student();
			stu.setId(result.getString(1));
			stu.setName(result.getString(2));
			stu.setSex(result.getInt(3));
			stu.setBirth(result.getDate(4));
			stu.setMajor_id(result.getInt(5));
			stu.setCollege_id(result.getInt(6));
			list.add(stu);
		}
		return list;
	}

	@Override
	public Student queryStudentById(String id) throws SQLException {

		String sql = "SELECT * FROM `student` WHERE id = '" + id + "'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		Student stu = new Student();
		while (result.next()) {
			stu.setId(result.getString(1));
			stu.setName(result.getString(2));
			stu.setSex(result.getInt(3));
			stu.setBirth(result.getDate(4));
			stu.setMajor_id(result.getInt(5));
			stu.setCollege_id(result.getInt(6));
		}
		return stu;
	}

	@Override
	public void addStudent(Student student) throws SQLException {

		String id = student.getId();
		String name = student.getName();
		Integer sex = student.getSex();
		Date birth = student.getBirth();
		Integer major_id = student.getMajor_id();
		Integer college_id = student.getCollege_id();

		String sql = "INSERT INTO `student` (`id`, `name`, `sex`,`birth`,`major_id`,`college_id`) VALUES (?,?,?,?,?,?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, id);
		ps.setString(2, name);
		ps.setInt(3, sex);
		ps.setDate(4, birth);
		ps.setInt(5, major_id);
		ps.setInt(6, college_id);

		ps.execute();

	}

	@Override
	public void alertStudent(Student student) throws SQLException {

		String id = student.getId();
		String name = student.getName();
		Integer sex = student.getSex();
		Date birth = student.getBirth();
		Integer major_id = student.getMajor_id();
		Integer college_id = student.getCollege_id();

		String sql = "UPDATE `student` SET `name`=?, `sex`=?, `birth`=?, `major_id`=?, `college_id`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ps.setInt(2, sex);
		ps.setDate(3, birth);
		ps.setInt(4, major_id);
		ps.setInt(5, college_id);
		ps.setString(6, id);

		ps.execute();
	}

	@Override
	public void deleteStudent(String id) throws SQLException {

		String sql = "DELETE FROM `student` WHERE (`id`='" + id + "')";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);
	}

}
