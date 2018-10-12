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

		String sql = "SELECT" 
				+ " student.id," 
				+ "student.`name`," 
				+ "student.sex,"
				+ "student.birth," 
				+ "student.schoolday,"
				+ "major.`name`," 
				+ "college.`name`" 
				+ " FROM " 
				+ "student,major,college "
				+ "WHERE student.major_id = major.id " 
				+ "AND " 
				+ "student.college_id =college.id ";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		List<Student> list = new LinkedList<Student>();
		while (result.next()) {
			Student stu = new Student();
			stu.setId(result.getString(1));
			stu.setName(result.getString(2));
			stu.setSex(result.getInt(3));
			stu.setBirth(result.getDate(4));
			stu.setSchoolDay(result.getDate(5));
			stu.setMajorName(result.getString(6));
			stu.setCollegeName(result.getString(7));
			list.add(stu);
		}
		return list;
	}

	@Override
	public Student queryStudentById(String id) throws SQLException {

		String sql = "SELECT student.id,student.`name`,student.sex,student.birth,student.schoolday,major.`name`,college.`name` FROM student,major,college WHERE student.major_id = major.id AND student.college_id =college.id AND student.id='"+id+"'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		Student stu = new Student();
		while (result.next()) {
			stu.setId(result.getString(1));
			stu.setName(result.getString(2));
			stu.setSex(result.getInt(3));
			stu.setBirth(result.getDate(4));
			stu.setSchoolDay(result.getDate(5));
			stu.setMajorName(result.getString(6));
			stu.setCollegeName(result.getString(7));
		}
		return stu;
	}
	
	@Override
	public List<Student> queryStudentByName(String name) throws SQLException {
		
		String sql ="SELECT student.id,student.`name`,student.sex,student.birth,student.schoolday,major.`name`,college.`name` FROM student,major,college WHERE student.major_id = major.id AND student.college_id =college.id AND student.name='"+name+"'";
		ResultSet result = MySQLConnectionUtils.mySQLResult(sql);
		List<Student> list = new LinkedList<Student>();
		while(result.next()){
			Student stu = new Student();
			stu.setId(result.getString(1));
			stu.setName(result.getString(2));
			stu.setSex(result.getInt(3));
			stu.setBirth(result.getDate(4));
			stu.setSchoolDay(result.getDate(5));
			stu.setMajorName(result.getString(6));
			stu.setCollegeName(result.getString(7));
			list.add(stu);
		}
		return list;
	}

	@Override
	public void addStudent(Student student) throws SQLException {

		String id = student.getId();
		String name = student.getName();
		Integer sex = student.getSex();
		Date birth = student.getBirth();
		Date schoolDate = student.getSchoolDay();
		Integer major_id = student.getMajor_id();
		Integer college_id = student.getCollege_id();

		String sql = "INSERT INTO `student` (`id`, `name`, `sex`,`birth`,`schoolday`,`major_id`,`college_id`) VALUES (?,?,?,?,?,?,?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, id);
		ps.setString(2, name);
		ps.setInt(3, sex);
		ps.setDate(4, birth);
		ps.setDate(5, schoolDate);
		ps.setInt(6, major_id);
		ps.setInt(7, college_id);

		ps.execute();
		
		ps.close();
		con.close();
		

	}

	@Override
	public void alertStudent(Student student) throws SQLException {

		String id = student.getId();
		String name = student.getName();
		Integer sex = student.getSex();
		Date birth = student.getBirth();
		Date schoolDate = student.getSchoolDay();
		Integer major_id = student.getMajor_id();
		Integer college_id = student.getCollege_id();

		String sql = "UPDATE `student` SET `name`=?, `sex`=?, `birth`=?,`schoolday`=?, `major_id`=?, `college_id`=? WHERE (`id`=?)";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ps.setInt(2, sex);
		ps.setDate(3, birth);
		ps.setDate(4, schoolDate);
		ps.setInt(5, major_id);
		ps.setInt(6, college_id);
		ps.setString(7, id);

		ps.execute();
		
		ps.close();
		con.close();
	}

	@Override
	public void deleteStudent(String id) throws SQLException {

		String sql = "DELETE FROM `student` WHERE (`id`='" + id + "')";
		Connection con = MySQLConnectionUtils.mySQLConnection();
		Statement statement = con.createStatement();
		statement.execute(sql);
		
		statement.close();
		con.close();
	}

	@Override
	public boolean studentIsExist(String id) throws SQLException {

		String sql = "SELECT * FROM `student` WHERE id = '" + id + "'";
		ResultSet res = MySQLConnectionUtils.mySQLResult(sql);
		return res.next();
	}

}
