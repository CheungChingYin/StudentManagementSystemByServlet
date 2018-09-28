package com.management.service;

import java.util.List;

import com.management.entities.Student;

public interface StudentService {
	
	public void addStudent(Student student);
	
	public void alertStudent(Student student);
	
	public void deleteStudent(String id);
	
	public List<Student> queryAllStudent();
	
	public Student searchStudentById(String id);
	
	public List<Student> searchStudentByName(String name);
	
	public Integer countStudents();
	

}
