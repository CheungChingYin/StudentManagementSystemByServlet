package com.management.dao;

import java.sql.SQLException;
import java.util.List;

import com.management.entities.Student;

public interface StudentDao {
	
	public List<Student> queryAllStudents() throws SQLException;
	
	public Student queryStudentById(String id) throws SQLException;
	
	public void addStudent(Student student) throws SQLException;
	
	public void alertStudent(Student student) throws SQLException;
	
	public void deleteStudent(String id) throws SQLException;
	
}
